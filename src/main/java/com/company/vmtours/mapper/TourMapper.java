package com.company.vmtours.mapper;

import com.company.vmtours.model.TourDao;
import com.company.vmtours.model.response.pocketguide.Tour;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class TourMapper implements Function<Tour, TourDao> {

    @Override
    public TourDao apply(Tour pocketGuideTour) {
        TourDao tourDao = new TourDao();
        tourDao.setId(pocketGuideTour.getId());
        tourDao.setName(pocketGuideTour.getName());
        tourDao.setLongDesc(pocketGuideTour.getLongDesc());
        return tourDao;
    }

}
