package com.example.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;

/**
 * A {@link Processor} implementation that capitalize a header, if found.
 *
 */
public class StartProcessor implements Processor {

	public static final String FOO_HEADER = "foo";
	
	/* (non-Javadoc)
	 * @see org.apache.camel.Processor#process(org.apache.camel.Exchange)
	 */
	public void process(Exchange exchange) throws Exception {
		final Message in = exchange.getIn();
		final String fooHeader = in.getHeader(FOO_HEADER, String.class);
		
		// http://camel.apache.org/using-getin-or-getout-methods-on-exchange.html
		in.setHeader(FOO_HEADER, (fooHeader == null ? "" : fooHeader).toUpperCase());
	}	
}
