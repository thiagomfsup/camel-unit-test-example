package com.example.test;

import org.apache.camel.EndpointInject;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.component.mock.MockEndpoint;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.processor.StartProcessor;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { 
	"/route-defs.xml", 
	"/test-route-defs.xml",
	"/test-camel-context-def.xml" 
})
@DirtiesContext
public class FilterTest {

	private static final String LOWERCASE_MOCK_VALUE = "bar";

	@EndpointInject(uri = "mock:result")
	protected MockEndpoint resultEndpoint;

	@Produce(uri = "direct:testStart")
	protected ProducerTemplate template;
	
	@Before
	public void setUp() {
		resultEndpoint.reset();
	}

	@Test
	public void testResponse() throws InterruptedException {
		resultEndpoint.expectedBodiesReceived((Object) null);
		resultEndpoint.expectedHeaderReceived(StartProcessor.FOO_HEADER,
				LOWERCASE_MOCK_VALUE.toUpperCase());

		template.sendBodyAndHeader(null, StartProcessor.FOO_HEADER,
				LOWERCASE_MOCK_VALUE);

		resultEndpoint.assertIsSatisfied();
	}

	@Test
	public void testNotSendExpectedHeader() throws InterruptedException {
		resultEndpoint.expectedBodiesReceived((Object) null);
		resultEndpoint.expectedHeaderReceived(StartProcessor.FOO_HEADER, "");

		template.sendBodyAndHeader(null, "UnexpectedHeader",
				LOWERCASE_MOCK_VALUE);

		resultEndpoint.assertIsSatisfied();
	}
	
}
