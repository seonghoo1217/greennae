package dongyang.ac.kr.greennae;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class GreennaeApplication {

	public static void main(String[] args) {
		SpringApplication.run(GreennaeApplication.class, args);
	}

}
