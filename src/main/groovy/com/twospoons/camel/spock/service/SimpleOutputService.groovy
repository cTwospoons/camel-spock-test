package com.twospoons.camel.spock.service

import groovy.util.logging.Slf4j
import org.apache.camel.Body
import org.springframework.stereotype.Service

@Slf4j
@Service
class SimpleOutputService {

    def performSomeOtherSimpleStringTask(@Body String input){
        log.debug 'This input is the convered message body from the camel route: {}', input

        def newBody = 'Some output from the output service.'

        log.debug 'The message body will now be: {}', newBody

        newBody
    }
}
