package com.tuodao.bp.api.facade.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.tuodao.bp.model.input.demo.DemoInput;
import com.tuodao.bp.model.output.member.DemoOuput;

@Component
public class DemoServiceFallback implements DemoService {

	private static final Logger logger = LoggerFactory.getLogger(DemoServiceFallback.class);
	
	@Override
	public DemoOuput getDemo(String name) {
		logger.info("----fallback name = {}",name);
		
		DemoOuput out = new DemoOuput();
		out.setOutAddress("fallbackAddress");
		out.setOutName("fallbackName");
		return out;
	}

	@Override
	public DemoOuput getEntity(DemoInput demoInput) {
		logger.info("----fallback demoInput = {}",demoInput);
		
		DemoOuput out = new DemoOuput();
		out.setOutAddress("fallbackAddress--input");
		out.setOutName("fallbackName--input");
		return out;
	}
}
