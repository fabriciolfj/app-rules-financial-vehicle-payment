package com.github.fabriciolfj.configuration;

import com.github.fabriciolfj.adapters.bureau.BureauClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.service.registry.ImportHttpServices;

@Configuration(proxyBeanMethods = false)
@ImportHttpServices(BureauClient.class)
public class BureauClientConfig {
}
