package com.company.vmtours.model.response.pocketguide;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tour {

    private int id;
    private String name;
    private String longDesc;
    private String shortDesc;
    private int cityId;
    private boolean html;
    private List<String> languages;
    private List<String> media;
    private double birdsEyeLat;
    private double birdsEyeLon;
    private int birdsEyeZoomLevel;
    private boolean buyForOlUse;
    private int startPathRef;
    private double startLat;
    private double startLon;
    private String lang;

}
