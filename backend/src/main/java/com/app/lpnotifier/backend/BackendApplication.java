package com.app.lpnotifier.backend;

import com.app.lpnotifier.backend.services.impl.NotifierService;
import com.app.lpnotifier.backend.services.notificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@EnableScheduling
public class BackendApplication {
	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}
	@Autowired
	private NotifierService notifierService;
	@Autowired
	private notificationService notificationService;
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/api/**") // Ruta de la API
						.allowedOrigins("https://lpnotifier-frontend.vercel.app") // Origen permitido
						.allowedMethods("GET", "POST", "PUT", "DELETE") // Métodos permitidos
						.allowedHeaders("*") // Encabezados permitidos
						.allowCredentials(true); // Permitir envío de cookies
			}
		};
	}
}