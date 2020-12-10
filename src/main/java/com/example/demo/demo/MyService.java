package com.example.demo.demo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Validator;

@Service
public class MyService {

    @Autowired
    private Validator validator;
}