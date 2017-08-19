package com.twospoons.camel.spock.router

import com.twospoons.camel.spock.service.SimpleInputService
import com.twospoons.camel.spock.service.SimpleOutputService
import org.apache.camel.builder.AdviceWithRouteBuilder
import org.apache.camel.component.mock.MockEndpoint
import org.apache.camel.impl.DefaultCamelContext
import org.apache.camel.model.ModelCamelContext
import org.apache.camel.model.RouteDefinition
import spock.lang.Specification


class SimpleRouterSpec extends Specification {
    ModelCamelContext context

    def mockSimpleInputService = Mock(SimpleInputService)
    def mockSimpleOutputService = Mock(SimpleOutputService)

    MockEndpoint mockTestOutputEndpoint

    def setup(){
        context = new DefaultCamelContext()
        def router = new SimpleRouter()

        router.simpleInputService = mockSimpleInputService
        router.simpleOutputService = mockSimpleOutputService

        context.addRoutes(router)
        context.start()

        context.routeDefinitions.toList().each { RouteDefinition routeDefinition ->
            routeDefinition.adviceWith(context as ModelCamelContext, new AdviceWithRouteBuilder() {
                @Override
                void configure() throws Exception {
                    mockEndpointsAndSkip('log:out')
                }
            })
        }

        mockTestOutputEndpoint = MockEndpoint.resolve(context, 'mock:log:out')
    }

    def cleanup(){

        mockTestOutputEndpoint.reset()

        context.stop()
    }


    def 'test -- empty message body && no headers'(){
        given:
            def producer = context.createProducerTemplate()

            mockTestOutputEndpoint.expectedCount = 0
        when:
            producer.sendBody('direct:test-input', '')
        then:
            1 * mockSimpleInputService.performSimpleStringTask('')
            0 * mockSimpleOutputService.performSomeOtherSimpleStringTask(_)
            mockTestOutputEndpoint.assertIsSatisfied()
    }

    def 'test -- empty message body && valid output header'(){
        given:
            def producer = context.createProducerTemplate()

            mockTestOutputEndpoint.expectedCount = 1
        when:
            producer.sendBodyAndHeaders('direct:test-input', '', ['SEND_OUT': true])
        then:
            1 * mockSimpleInputService.performSimpleStringTask('')
            1 * mockSimpleOutputService.performSomeOtherSimpleStringTask(_)
            mockTestOutputEndpoint.assertIsSatisfied()
    }

}