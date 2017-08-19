package com.twospoons.camel.spock.controller

import org.apache.camel.Produce
import org.apache.camel.ProducerTemplate
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class SimpleController {

    @Produce(uri = 'direct:test-input')
    ProducerTemplate simpleInputProducer

    @RequestMapping('/simple')
    def invokeSimpleRouter(){
        simpleInputProducer.sendBody('Invoking test-input route from REST call.')


    }
}
