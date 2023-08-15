package com.test;

import com.test.common.ApplicationStarter;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication2 extends ApplicationStarter {
    public static void main(String[] args) {
        new DemoApplication2().start(args);
    }
}