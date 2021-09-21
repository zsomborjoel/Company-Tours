package com.company.vmtours.model.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "pocket.guide.system")
public class PocketGuideProperties {

    private String url;

}
