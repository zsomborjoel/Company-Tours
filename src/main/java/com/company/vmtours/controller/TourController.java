package com.company.vmtours.controller;

import com.company.vmtours.service.TourService;
import com.company.vmtours.model.filter.Filter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;

@Slf4j
@Controller
@RequestMapping("/")
public class TourController {

    @Autowired
    private TourService tourService;

    @PostMapping("/tours/refresh")
    public ResponseEntity<?> refreshTours(@RequestBody Filter filter) {
        try {
            tourService.refreshTours(filter);
            return ResponseEntity.ok().body("Refresh was successful");
        } catch (RestClientException e) {
            return ResponseEntity.internalServerError().body(e);
        }
    }

    @GetMapping("/tours")
    public ResponseEntity<?> getTours(@RequestParam(value = "filter", required = false) Filter filter) {
        try {
            return ResponseEntity.ok().body(tourService.getTours(filter));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e);
        }
    }

}