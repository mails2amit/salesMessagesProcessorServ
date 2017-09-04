package com.messageprocessor.impl;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.messageprocessor.processor.impl.MessageProcessorSalesImpl;

public class MessageProcessorSalesImplTest {
	String message = null;

	@Before
	public void setUp() throws Exception {
		message = "devide 20p apples";
	}

	@Test
	public void processTest() {
		String message1 = "apple at 10p";
		MessageProcessorSalesImpl messageProcessorSalesImpl = MessageProcessorSalesImpl
				.getInstance();
		boolean isProcessed = messageProcessorSalesImpl.process(message1);
		Assert.assertTrue(isProcessed);
	}

	@After
	public void tearDown() throws Exception {
	}
}
