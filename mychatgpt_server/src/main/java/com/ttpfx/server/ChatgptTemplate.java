package com.ttpfx.server;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;
@Data
@Configuration
@ConfigurationProperties(prefix = "core")
public class ChatgptTemplate {

    private Map<String, String> template = new HashMap<>();


}
