package com.tuodao.bp.api.facade.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.tuodao.bp.model.input.demo.DemoInput;
import com.tuodao.bp.model.output.member.DemoOuput;

@FeignClient(value="BIZ-USER-ACCOUNT",fallback=DemoServiceFallback.class)
public interface DemoService {
	
	@RequestMapping(value="/demo/getDemo",method=RequestMethod.POST)
	public DemoOuput getDemo(@RequestParam(value="name") String name);
	
	@RequestMapping(value="/demo/entity",method=RequestMethod.POST)
	public DemoOuput getEntity(@RequestBody DemoInput demoInput);
}
