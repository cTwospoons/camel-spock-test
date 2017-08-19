package com.twospoons.camel.spock.router

import com.twospoons.camel.spock.service.SimpleInputService
import com.twospoons.camel.spock.service.SimpleOutputService
import org.apache.camel.builder.RouteBuilder
import org.springframework.beans.factory.annotation.Autowired

import static org.apache.camel.LoggingLevel.*
import org.springframework.stereotype.Component

@Component
class SimpleRouter extends RouteBuilder{

    @Autowired
    SimpleInputService simpleInputService

    @Autowired
    SimpleOutputService simpleOutputService

    @Override
    void configure() throws Exception {

        from('direct:test-input')
            .log(DEBUG, log,'Received message on test-input')
            .bean(simpleInputService)
            .choice()
            .when(header('SEND_OUT').isNotNull())
                .log(DEBUG, log,'Message is valid and will be sent to direct:test-output')
                .to('direct:test-output')
            .endChoice()


        from('direct:test-output')
            .log(DEBUG, log, 'Received message on test-output')
            .bean(simpleOutputService)
            .to('log:out')
    }

}
