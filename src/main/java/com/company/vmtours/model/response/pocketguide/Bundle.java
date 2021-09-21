package com.company.vmtours.model.response.pocketguide;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bundle {

    private String name;
    private String id;
    private boolean hidden;
    private List<String> tags;
    private int purchaseItemId;
    private String shortDesc;
    private String longDesc;
    private String detail;
    private int priceInCents;
    private int cityId;
    private boolean html;
    private boolean recommended;
    private List<String> languages;
    private List<String> images;
    private List<Integer> tourIds;
    private String lang;

}
