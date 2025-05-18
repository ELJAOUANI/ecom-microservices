package com.hamza.customerservice;

import com.hamza.customerservice.config.CustomerConfigParams;
import com.hamza.customerservice.entities.Customer;
import com.hamza.customerservice.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableConfigurationProperties(CustomerConfigParams.class)
public class CustomerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceApplication.class, args);
    }



    @Bean
    CommandLineRunner commandLineRunner(CustomerRepository customerRepository) {
        return args -> {
            customerRepository.save(Customer.builder()
                            .name("Hamza")
                            .email("hamza@gmail.com")
                    .build());
                    customerRepository.save(Customer.builder()
                            .name("Hamza")
                            .email("hamza@gmail.com")
                            .build());
                            customerRepository.save(Customer.builder()

                                    .name("Hamza")
                                    .email("hamza@gmail.com")



                                    .build());

                            customerRepository.findAll().forEach(c->{
                                System.out.println("============");
                                System.out.println(c.getName());
                                System.out.println(c.getEmail());
                            });
        };
    }

}
