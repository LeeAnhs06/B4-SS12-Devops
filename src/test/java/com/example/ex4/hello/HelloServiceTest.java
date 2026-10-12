package com.example.ex4.hello;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class HelloServiceTest {

    private final HelloService helloService = new HelloService();

    @Test
    void greetWithName() {
        assertThat(helloService.greet("Spring")).isEqualTo("Hello, Spring!");
    }

    @Test
    void greetWithBlankDefaultsToWorld() {
        assertThat(helloService.greet("  ")).isEqualTo("Hello, World!");
        assertThat(helloService.greet(null)).isEqualTo("Hello, World!");
    }
}
