package com.hamza.billingservice;

import com.hamza.billingservice.Feign.CustomerRestClient;
import com.hamza.billingservice.Feign.ProductRestClient;
import com.hamza.billingservice.entities.Bill;
import com.hamza.billingservice.entities.ProductItem;
import com.hamza.billingservice.model.Customer;
import com.hamza.billingservice.model.Product;
import com.hamza.billingservice.repository.BillRepository;
import com.hamza.billingservice.repository.ProductItemRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.util.*;

@SpringBootApplication
@EnableFeignClients
public class BillingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BillingServiceApplication.class, args);
	}


	@Bean
	CommandLineRunner commandLineRunner(BillRepository billRepository,
										ProductItemRepository productItemRepository ,
										CustomerRestClient customerRestClient,
										ProductRestClient productRestClient) {
return args -> {
	Collection<Customer> customers = customerRestClient.getAllCustomers().getContent();
	Collection<Product> products = productRestClient.getAllProduct().getContent();
	customers.forEach(customer -> {
		Bill bill = Bill.builder()
				.billingDate(new Date())
				.customerId(customer.getId())
				.build();
		billRepository.save(bill);
		products.forEach(product -> {
			ProductItem productItem = ProductItem.builder()
					.bill(bill)
					.productId(product.getId())
					.quantity(1+new Random().nextInt(10))
					.unitPrice(product.getPrice())
					.build();
			productItemRepository.save(productItem);
		});
	});
};
	}
}
