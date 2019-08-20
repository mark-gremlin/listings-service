package com.gremlin.rental.listings.listingsservice;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ListingsServiceApplicationTests {

	@BeforeClass
	public static void setup() {
		System.setProperty("BUCKET_NAME", "mark.demo");
	}

	@Test
	public void contextLoads() {
	}

}
