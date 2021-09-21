package com.company.vmtours.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tour")
public class TourDao {

    @Id
    @Column(name = "id", unique = true)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "long_desc", length = 5000)
    private String longDesc;

    @Transient private String shortDesc;
    @Transient private int cityId;
    @Transient private boolean html;
    @Transient private List<String> languages;
    @Transient private List<String> media;
    @Transient private double birdsEyeLat;
    @Transient private double birdsEyeLon;
    @Transient private int birdsEyeZoomLevel;
    @Transient private boolean buyForOlUse;
    @Transient private int startPathRef;
    @Transient private double startLat;
    @Transient private double startLon;
    @Transient private String lang;

}
