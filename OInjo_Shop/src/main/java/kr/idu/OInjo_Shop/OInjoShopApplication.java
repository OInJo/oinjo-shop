package kr.idu.OInjo_Shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class OInjoShopApplication {

	public static void main(String[] args) {
		SpringApplication.run(OInjoShopApplication.class, args);
	}

}
