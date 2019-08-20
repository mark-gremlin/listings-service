package com.gremlin.rental.listings.listingsservice.service;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.transfer.TransferManager;
import com.amazonaws.services.s3.transfer.Upload;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gremlin.rental.listings.listingsservice.model.Listing;
import com.gremlin.rental.listings.listingsservice.util.XferMgrProgress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PreDestroy;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class ListingsUploader {

    private final TransferManager transferManager;
    private final ObjectMapper objectMapper;
    @Value("${bucket.name}")
    private String bucketName;

    @Autowired
    public ListingsUploader(TransferManager transferManager, ObjectMapper objectMapper) {
        this.transferManager = transferManager;
        this.objectMapper = objectMapper;
    }

    public String uploadListing(Listing listing) throws IOException {
        File file = File.createTempFile("listing", ".json");
        file.deleteOnExit();
        try {
            objectMapper.writeValue(file, listing);
        } catch (IOException ioe) {
            throw ioe;
        }

        try {
            Upload xfer = transferManager.upload(bucketName, String.format("listings/%s", UUID.randomUUID().toString()), file);
            XferMgrProgress.waitForCompletion(xfer);
        } catch (AmazonServiceException ase) {
            throw ase;
        }

        return String.format("Listing %s uploaded!", listing.getAddress());

    }

    @PreDestroy
    public void teardown() {
        transferManager.shutdownNow();
    }
}
