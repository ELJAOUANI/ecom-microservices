package com.hamza.billingservice.web;

import com.hamza.billingservice.Feign.CustomerRestClient;
import com.hamza.billingservice.Feign.ProductRestClient;
import com.hamza.billingservice.entities.Bill;
import com.hamza.billingservice.repository.BillRepository;
import com.hamza.billingservice.repository.ProductItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
@RestController
public class BillRestController {
    @Autowired
    private BillRepository billRepository;
    @Autowired
    private ProductRestClient productRestClient;
    @Autowired
    private CustomerRestClient customerRestClient;
    @Autowired
    private ProductItemRepository productItemRepository;
    @GetMapping(path = "bills/{id}")
    public Bill getBill(@PathVariable Long id) {
        Bill bill = billRepository.findById(id).get();
        bill.setCustomer(customerRestClient.findCustomerById(bill.getCustomerId()));
        bill.getProductItems().forEach(productItem -> {
            productItem.setProduct(productRestClient.getProductById(productItem.getProductId()));
        });
        return bill;
    }
}
