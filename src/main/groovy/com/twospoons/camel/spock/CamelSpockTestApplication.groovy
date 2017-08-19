package com.twospoons.camel.spock

import groovy.util.logging.Slf4j
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@Slf4j
@SpringBootApplication
class CamelSpockTestApplication {

    public static void main(String... args){
        SpringApplication.run(CamelSpockTestApplication, args)
    }
}
