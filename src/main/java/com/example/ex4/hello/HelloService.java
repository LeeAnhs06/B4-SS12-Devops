package com.example.ex4.hello;

import org.springframework.stereotype.Service;

@Service
public class HelloService {

    public String greet(String name) {
        if (name == null || name.isBlank()) {
            return "Hello, World!";
        }
        return "Hello, " + name.trim() + "!";
    }
}
