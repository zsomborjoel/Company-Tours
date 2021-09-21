package com.company.vmtours.client;

import com.company.vmtours.model.properties.PocketGuideProperties;
import com.company.vmtours.model.response.pocketguide.PocketGuideResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PocketGuideClientTest {

    @InjectMocks
    private PocketGuideClient pocketGuideClient;

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private PocketGuideProperties properties;

    @Test
    public void shouldReturnPocketGuideResponse() {
        PocketGuideResponse pocketGuideResponse = new PocketGuideResponse();
        pocketGuideResponse.setTours(new ArrayList<>());

        when(properties.getUrl()).thenReturn("http:");
        when(restTemplate.getForEntity("http:", PocketGuideResponse.class))
                .thenReturn(new ResponseEntity<>(pocketGuideResponse, HttpStatus.OK));
        assertNotNull(pocketGuideClient.getPocketGuideResponse());
    }

}
