package org.biz.biz.useraccount.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tuodao.bp.model.input.demo.DemoInput;
import com.tuodao.bp.model.output.member.DemoOuput;

@RestController
@RequestMapping("/demo")
public class DemoController {
	
	private static final Logger logger = LoggerFactory.getLogger(DemoController.class);
	
	@RequestMapping(value="/getDemo",method=RequestMethod.POST)
	public DemoOuput getDemo(@RequestParam(value="name") String name) {
		logger.info("name = {}",name);
		
		DemoOuput out = new DemoOuput();
		out.setOutName("fastJSON");
		out.setOutAddress("China");
		
		logger.info("out = {}", out);
		return out;
	}
	
	@RequestMapping(value="/entity",method=RequestMethod.POST)
	@ResponseBody
	public DemoOuput getEntity(@RequestBody DemoInput demoInput) {
		logger.info("input = {}",demoInput);
		
		DemoOuput out = new DemoOuput();
		out.setOutName("fastJSON");
		out.setOutAddress("China");
		
		logger.info("out = {}", out);
		return out;
	}
}
