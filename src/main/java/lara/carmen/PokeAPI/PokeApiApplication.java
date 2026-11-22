package lara.carmen.PokeAPI;

import lara.carmen.PokeAPI.security.JwtFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PokeApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(PokeApiApplication.class, args);
	}

	@Bean
	public FilterRegistrationBean<JwtFilter> filterRegistrationBean(JwtFilter jwtFilter) {
		FilterRegistrationBean<JwtFilter> registrationBean = new FilterRegistrationBean<>();
		registrationBean.setFilter(jwtFilter);
		registrationBean.addUrlPatterns("/api/*");
		return registrationBean;

	}

}
