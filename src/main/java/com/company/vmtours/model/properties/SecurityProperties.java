package com.company.vmtours.model.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.HashMap;
import java.util.Map;

@Data
@ConfigurationProperties(prefix = "app.security")
public class SecurityProperties {

    private Map<String, String> users = new HashMap<>();
    private Map<String, String> admins = new HashMap<>();

}
