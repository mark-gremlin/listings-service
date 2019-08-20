package com.gremlin.rental.listings.listingsservice.config;
import com.amazonaws.services.s3.transfer.TransferManager;
import com.amazonaws.services.s3.transfer.TransferManagerBuilder;
import com.gremlin.rental.listings.listingsservice.util.XferMgrProgress;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AWSConfig {

    @Bean
    public TransferManager transferManager() {
        return TransferManagerBuilder.standard().build();
    }

    @Bean
    public XferMgrProgress xferMgrProgress() {
        return new XferMgrProgress();
    }
}
