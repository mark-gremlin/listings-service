package com.gremlin.rental.listings.listingsservice.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Amenities {
    private boolean cooling;
    private boolean heating;
    private List<String> pets = new ArrayList<>();
    private boolean laundryInUnit;
}
