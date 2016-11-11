package com.example.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class StartProcessor implements Processor {

	private static final String FOO_HEADER = "foo";
	
	public void process(Exchange exchange) throws Exception {
		String fooHeader = exchange.getIn().getHeader(FOO_HEADER, String.class);
		
		// http://camel.apache.org/using-getin-or-getout-methods-on-exchange.html
		exchange.getIn().setHeader(FOO_HEADER, fooHeader.toUpperCase());
	}	
}
