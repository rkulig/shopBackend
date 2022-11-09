package com.rkulig.shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ShopApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShopApplication.class, args);
	}

//	// dodanie naglowka Allow Origins -> CORS  // obsluga cors zrealizowana po stronie frontendu
//	@Bean
//	public WebMvcConfigurer cors(){
//		return new WebMvcConfigurer() {
//			@Override
//			public void addCorsMappings(CorsRegistry registry) {
//				registry.addMapping("/**")
//						.allowedOrigins("http://localhost:4200");
//			}
//		};
//	}

}
