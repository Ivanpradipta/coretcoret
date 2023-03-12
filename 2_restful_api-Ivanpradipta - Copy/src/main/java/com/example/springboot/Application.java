package com.example.springboot;

import java.util.Arrays;

import com.example.springboot.repository.CustomerRepository;
import com.example.springboot.repository.ProductRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(Application.class, args);

		System.out.println("Let's inspect the beans provided by Spring Boot:");

        String[] beanNames = ctx.getBeanDefinitionNames();
        Arrays.sort(beanNames);
		for (String beanName : beanNames) {
			System.out.println(beanName);
		}
    }

    @Bean
    CustomerRepository customerRepository() {
        return new CustomerRepository();
    }

    @Bean
    ProductRepository productRepository() {
        return new ProductRepository();
    }
}
