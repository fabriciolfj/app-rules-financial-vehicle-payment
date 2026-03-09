package com.github.fabriciolfj.configuration;

import com.github.fabriciolfj.entrypoints.controllers.dtos.response.ProposalResponse;
import io.lettuce.core.ClientOptions;
import io.lettuce.core.SocketOptions;
import io.lettuce.core.api.StatefulConnection;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettucePoolingClientConfiguration;
import org.springframework.data.redis.serializer.JacksonJsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.json.JsonMapper;

import java.time.Duration;

import static com.github.fabriciolfj.utils.ConstantsCache.CACHE_PROPOSAL;

@Configuration
@EnableCaching
public class RedisConfig {

    @Bean
    public ObjectMapper cacheObjectMapper() {
        return JsonMapper.builder()
                .build();
    }

    @Bean
    public RedisConnectionFactory redisConnectionFactory(
            @Value("${spring.data.redis.host}") String host,
            @Value("${spring.data.redis.port}") int port,
            @Value("${spring.data.redis.pool.max-active:16}") int maxActive,
            @Value("${spring.data.redis.pool.max-idle:8}") int maxIdle,
            @Value("${spring.data.redis.pool.min-idle:2}") int minIdle
    ) {
        RedisStandaloneConfiguration serverConfig = new RedisStandaloneConfiguration(host, port);

        GenericObjectPoolConfig<StatefulConnection<?, ?>> poolConfig = new GenericObjectPoolConfig<>();
        poolConfig.setMaxTotal(maxActive);
        poolConfig.setMaxIdle(maxIdle);
        poolConfig.setMinIdle(minIdle);

        ClientOptions clientOptions = ClientOptions.builder()
                .pingBeforeActivateConnection(true)
                .autoReconnect(true)
                .socketOptions(SocketOptions.builder()
                        .connectTimeout(Duration.ofSeconds(5))
                        .build())
                .build();

        LettuceClientConfiguration clientConfig = LettucePoolingClientConfiguration.builder()
                .poolConfig(poolConfig)
                .clientOptions(clientOptions)
                .clientOptions(ClientOptions.builder()
                        .socketOptions(SocketOptions.builder()
                                .connectTimeout(Duration.ofSeconds(5))
                                .build())
                        .build())
                .commandTimeout(Duration.ofSeconds(3))
                .build();

        LettuceConnectionFactory factory = new LettuceConnectionFactory(serverConfig, clientConfig);
        factory.afterPropertiesSet();
        return factory;
    }

    @Bean("proposalCacheManager")
    public RedisCacheManager proposalCacheManager(
            ObjectMapper objectMapper,
            RedisConnectionFactory redisConnectionFactory,
            @Value("${cache.proposal.ttl-minutes:30}") long ttlMinutes) {
        final JacksonJsonRedisSerializer<ProposalResponse> valueSerializer =
                new JacksonJsonRedisSerializer<>(objectMapper, ProposalResponse.class);

        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofMinutes(ttlMinutes))
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()))
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(valueSerializer))
                .prefixCacheNameWith("cache:")
                .disableCachingNullValues();

        return RedisCacheManager.builder(redisConnectionFactory)
                .cacheDefaults(config)
                .withCacheConfiguration(CACHE_PROPOSAL, config)
                .build();
    }
}
