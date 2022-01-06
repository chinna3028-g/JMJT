package com.jmjt.initializer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.jmjt.model.Basic;
import com.jmjt.service.BasicService;

@Component
public class BasicInitializer implements CommandLineRunner {

    @Autowired
    private BasicService basicService;

    @Override
    public void run(String... args) throws Exception {
        Basic basic = new Basic();
        basic.setName("jmjt");
        basicService.save(basic);
    }
}
