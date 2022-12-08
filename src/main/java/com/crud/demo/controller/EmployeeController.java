package com.crud.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/code")
public class EmployeeController {
    /* TODO document why this method is empty */
    public String getString(){
        return "hello";
    }
}
