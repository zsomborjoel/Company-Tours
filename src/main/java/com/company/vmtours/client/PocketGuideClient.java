package com.company.vmtours.client;

import com.company.vmtours.model.properties.PocketGuideProperties;
import com.company.vmtours.model.response.pocketguide.PocketGuideResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
public class PocketGuideClient {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private PocketGuideProperties properties;

    public PocketGuideResponse getPocketGuideResponse() throws RestClientException {
        return restTemplate.getForEntity(properties.getUrl(), PocketGuideResponse.class).getBody();
    }

}
