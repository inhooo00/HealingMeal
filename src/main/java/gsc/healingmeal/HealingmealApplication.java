package gsc.healingmeal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class HealingmealApplication {

	public static void main(String[] args) {
		SpringApplication.run(HealingmealApplication.class, args);
	}

}
