package com.example.test;

import org.apache.camel.EndpointInject;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.component.mock.MockEndpoint;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/route-defs.xml", "/test-route-defs.xml",
		"/test-camel-context-def.xml" })
@DirtiesContext
public class FilterTest {

	@EndpointInject(uri = "mock:result")
	protected MockEndpoint resultEndpoint;

	@Produce(uri = "direct:testStart")
	protected ProducerTemplate template;

	@Test
	public void test() throws Exception {
		String expectedBody = "<matched/>";

		resultEndpoint.expectedBodiesReceived(expectedBody);

		template.sendBodyAndHeader(expectedBody, "foo", "bar");

		resultEndpoint.assertIsSatisfied();
	}

}
