package myspringboot.myspringbootweb;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MySpringBootWebApplication {
	private static Logger logger = LoggerFactory.getLogger(MySpringBootWebApplication.class);

	public static void main(String[] args) {

		logger.info("Starting");
		SpringApplication.run(MySpringBootWebApplication.class, args);
	}

}
