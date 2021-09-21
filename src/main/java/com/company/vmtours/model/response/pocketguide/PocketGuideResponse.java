package com.company.vmtours.model.response.pocketguide;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PocketGuideResponse {

    @JsonIgnore
    private List<City> cities;

    @JsonIgnore
    private List<Bundle> bundles;

    private List<Tour> tours;

}
