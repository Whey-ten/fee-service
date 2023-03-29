package fmfi.sbdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan // tell Spring to scan for @ConfigurationProperties classes
public class SbDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SbDemoApplication.class, args);
	}

}
