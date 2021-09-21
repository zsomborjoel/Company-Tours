package com.company.vmtours.service;

import com.company.vmtours.client.PocketGuideClient;
import com.company.vmtours.mapper.TourMapper;
import com.company.vmtours.model.TourDao;
import com.company.vmtours.model.filter.Filter;
import com.company.vmtours.model.response.pocketguide.Tour;
import com.company.vmtours.repository.TourRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class TourService {

    @Autowired
    private PocketGuideClient pocketGuideClient;

    @Autowired
    private TourRepository tourRepository;

    @Autowired
    private TourMapper tourMapper;

    public void refreshTours(Filter filter) {
        List<Tour> pocketGuideTours = pocketGuideClient.getPocketGuideResponse().getTours();
        log.info("PocketGuide request was successful");

        List<TourDao> tours = getTours(pocketGuideTours);
        if (filter.getFilter() == null) {
            tourRepository.saveAll(tours);
        } else {
            tours.stream()
                    .filter(t -> t.getName().equalsIgnoreCase(filter.getFilter()))
                    .forEach(t -> tourRepository.save(t));
        }
    }

    public Iterable<TourDao> getTours(Filter filter) {
        if (filter == null) {
            return tourRepository.findAll();
        } else {
            return tourRepository.findTourByName(filter.getFilter().toLowerCase());
        }
    }

    private List<TourDao> getTours(List<Tour> pocketGuideTours) {
        return pocketGuideTours.stream()
                .map(pgt -> tourMapper.apply(pgt))
                .collect(Collectors.toList());
    }

}
