package kr.idu.OInjo_Shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.filter.HiddenHttpMethodFilter;

@SpringBootApplication
@EnableJpaAuditing
@EnableScheduling
public class OInjoShopApplication {

	public static void main(String[] args) {
		SpringApplication.run(OInjoShopApplication.class, args);
	}


	@Bean   //메소드를 호출하여 Bean 객체를 생성
	public HiddenHttpMethodFilter hiddenHttpMethodFilter() {    //put, delete 처리
		//filter로 어떤 Mapping인지를 처리 해줌
		return new HiddenHttpMethodFilter();
	}

}
