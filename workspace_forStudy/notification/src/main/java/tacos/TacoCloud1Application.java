package tacos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class TacoCloud1Application {

	public static void main(String[] args) {
		SpringApplication.run(TacoCloud1Application.class, args);
	}

}
