package com.messageprocessor.utils;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MessageParserTest {
	String message = null;

	@Before
	public void setUp() throws Exception {
		message = "devide 20p apples";
	}

	@Test
	public void parseMessageTestInvalidMessage() {

		Object object = MessageParser.parseMessage(message);
		Assert.assertNull(object);
	}

	@After
	public void tearDown() throws Exception {
	}
}
