package com.hamza.customerservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class ConfigTestRestController {
    @Value("{global.params.p1}")
    private String p1;
    @Value("{global.params.p2}")
    private String p2;

    @Autowired
    private CustomerConfigParams customerConfigParams;

    @GetMapping("/testConfig1")
    public Map<String, String> gonfigTest() {
        return  Map.of("p1",p1,"p2",p2);
    }

    @GetMapping("/testConfig2")
    public CustomerConfigParams configTest2(){
        return customerConfigParams;
    }
}
