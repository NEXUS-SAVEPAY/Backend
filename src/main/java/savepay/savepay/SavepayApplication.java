package savepay.savepay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing // createdAt, updatedAt 사용 위해서 활성화
public class SavepayApplication {

	public static void main(String[] args) {
		SpringApplication.run(SavepayApplication.class, args);
	}

}
