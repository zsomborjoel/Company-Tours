package com.company.vmtours.testhelper;

import com.company.vmtours.model.TourDao;
import com.company.vmtours.model.filter.Filter;
import com.company.vmtours.model.response.pocketguide.PocketGuideResponse;
import com.company.vmtours.model.response.pocketguide.Tour;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Arrays;
import java.util.Collections;

public class TourTestHelper {

    public String getFilterAsJson(String filterValue) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(new Filter());
    }

    public Filter getFilter(String filterValue) {
        return new Filter(filterValue);
    }

    public PocketGuideResponse getPocketGuideResponseFilled() {
        PocketGuideResponse pocketGuideResponse = new PocketGuideResponse();
        pocketGuideResponse.setTours(
                Collections.singletonList(new Tour()));
        return pocketGuideResponse;
    }

    public Iterable<TourDao> getTours() {
        return Arrays.asList(
                getTour("test1"),
                getTour("test2"),
                getTour("test3"));
    }

    public TourDao getTour(String name) {
        TourDao tourDao = new TourDao();
        tourDao.setName(name);
        return tourDao;
    }

}
