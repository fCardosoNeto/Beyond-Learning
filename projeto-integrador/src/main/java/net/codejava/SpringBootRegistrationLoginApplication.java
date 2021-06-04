package net.codejava;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

@EntityScan(
		basePackageClasses = { SpringBootRegistrationLoginApplication.class, Jsr310JpaConverters.class }
	)
@SpringBootApplication
public class SpringBootRegistrationLoginApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootRegistrationLoginApplication.class, args);
	}

}
