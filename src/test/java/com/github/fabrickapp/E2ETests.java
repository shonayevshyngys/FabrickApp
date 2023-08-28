package com.github.fabrickapp;

import com.github.fabrickapp.client.FabrickClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource("classpath:application.properties")
public class E2ETests {

    @Autowired
    FabrickClient client;

    @Test
    public void FabrickApiE2ETest()
    {
        client.getBalance();
        Assertions.assertEquals(1,1);
    }
}
