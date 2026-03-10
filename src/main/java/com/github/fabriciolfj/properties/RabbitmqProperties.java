package com.github.fabriciolfj.properties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Configuration
@ConfigurationProperties(prefix = "rabbitmq")
public class RabbitmqProperties {

    private int concurrencyMin;
    private int concurrencyMax;
}
