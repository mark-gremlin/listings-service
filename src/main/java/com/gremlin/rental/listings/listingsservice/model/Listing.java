package com.gremlin.rental.listings.listingsservice.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class Listing {
    private int beds;
    private int baths;
    private int sqft;
    private double rent;
    private double deposit;
    private double fees;
    private String address;
    private String description;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date dateAvailable;
    private Amenities amenities;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date posted;
}
