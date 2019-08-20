package com.gremlin.rental.listings.listingsservice.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.gremlin.rental.listings.listingsservice.controller")
public class WebConfig {
}
