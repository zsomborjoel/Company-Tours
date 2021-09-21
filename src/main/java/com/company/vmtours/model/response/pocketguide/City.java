package com.company.vmtours.model.response.pocketguide;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class City {

    private int id;
    private String name;
    private double lat;
    private double lon;
    private String country;
    private List<String> languages;
    private boolean html;
    private String viatorUrl;
    private String mapId;
    private String roamingBundle;
    private int radius;
    private boolean htmlDetails;
    private String lang;
}
