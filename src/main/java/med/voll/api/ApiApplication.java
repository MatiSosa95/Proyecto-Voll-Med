package med.voll.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
	}

	//las migraciones permite gestionar la db con codigo sql.
	//Flyway tiene una anoatcion especifica para entender y eso es por versiones

}
