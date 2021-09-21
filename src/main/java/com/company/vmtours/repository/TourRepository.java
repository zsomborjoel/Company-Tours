package com.company.vmtours.repository;

import com.company.vmtours.model.TourDao;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TourRepository extends CrudRepository<TourDao, Integer> {

    @Query("select t from TourDao t where lower(t.name) = :name")
    List<TourDao> findTourByName(String name);

}
