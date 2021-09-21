package com.company.vmtours.service;

import com.company.vmtours.client.PocketGuideClient;
import com.company.vmtours.mapper.TourMapper;
import com.company.vmtours.model.TourDao;
import com.company.vmtours.model.response.pocketguide.Tour;
import com.company.vmtours.repository.TourRepository;
import com.company.vmtours.testhelper.TourTestHelper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TourServiceTest {

    @InjectMocks
    private TourService tourService;

    @Mock
    private PocketGuideClient pocketGuideClient;

    @Mock
    private TourRepository tourRepository;

    @Mock
    private TourMapper tourMapper;

    private final TourTestHelper tourTestHelper = new TourTestHelper();

    @Test
    public void shouldRefreshAllTours() {
        when(pocketGuideClient.getPocketGuideResponse())
                .thenReturn(tourTestHelper.getPocketGuideResponseFilled());
        when(tourMapper.apply(new Tour()))
                .thenReturn(new TourDao());
        tourService.refreshTours(tourTestHelper.getFilter(null));
    }

    @Test
    public void shouldRefreshFilteredTours() {
        when(pocketGuideClient.getPocketGuideResponse())
                .thenReturn(tourTestHelper.getPocketGuideResponseFilled());
        when(tourMapper.apply(new Tour()))
                .thenReturn(tourTestHelper.getTour("test"));
        tourService.refreshTours(tourTestHelper.getFilter("filter"));
    }

    @Test
    public void shouldGetAllToursWithoutFilter() {
        when(tourRepository.findAll())
                .thenReturn(Collections.singletonList(new TourDao()));
        assertNotNull(tourService.getTours(null));
    }

    @Test
    public void shouldGetFilteredToursWithFilter() {
        when(tourRepository.findTourByName(any()))
                .thenReturn((List<TourDao>) tourTestHelper.getTours());
        Assert.assertEquals("test1",
                tourService.getTours(
                        tourTestHelper.getFilter("test1"))
                        .iterator()
                        .next()
                        .getName()
                );
    }

}
