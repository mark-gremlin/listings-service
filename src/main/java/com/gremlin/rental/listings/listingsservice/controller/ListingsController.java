package com.gremlin.rental.listings.listingsservice.controller;

import com.gremlin.rental.listings.listingsservice.model.Listing;
import com.gremlin.rental.listings.listingsservice.service.ListingsUploader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
public class ListingsController {

    private Logger LOG = LoggerFactory.getLogger(getClass().getName());

    private final ListingsUploader listingsUploader;

    @Autowired
    public ListingsController(ListingsUploader listingsUploader) {
        this.listingsUploader = listingsUploader;
    }

    @GetMapping("/")
    public @ResponseBody ResponseEntity<String> hello() {
        return new ResponseEntity<String>("Welcome to the listings service!", HttpStatus.OK);
    }

    @PostMapping("/post")
    public @ResponseBody ResponseEntity<String> postListing(@RequestBody Listing listing) throws InterruptedException, IOException {
        LOG.info("Uploading listing...");
        return new ResponseEntity<>(listingsUploader.uploadListing(listing), HttpStatus.CREATED);

    }

}
