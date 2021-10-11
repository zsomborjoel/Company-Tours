package com.company.vmtours;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan("com.company.vmtours.model.properties")
public class VmToursApplication {

	public static void main(String[] args) {
		SpringApplication.run(VmToursApplication.class, args);
	}

}
