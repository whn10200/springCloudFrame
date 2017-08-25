package com.tuodao.bp.api.facade.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.tuodao.bp.api.core.response.RespResult;
import com.tuodao.bp.api.facade.common.BaseController;
import com.tuodao.bp.api.facade.domain.ResultInput;
import com.tuodao.bp.api.facade.domain.ResultOutput;
import com.tuodao.bp.api.facade.service.DemoService;
import com.tuodao.bp.model.input.demo.DemoInput;
import com.tuodao.bp.model.output.member.DemoOuput;


@RequestMapping("/router")
@RestController
public class ActiveController extends BaseController{
	
	
	private static final Logger logger = LoggerFactory.getLogger(ActiveController.class);
	
	private RestTemplate rest;
	
	@Autowired
	private DemoService demoService;
	
	@RequestMapping(value="/active",method=RequestMethod.POST)
	public RespResult<String> queryActive(){
		logger.info("query active start.....");
		
		ResponseEntity<String> entity = rest.getForEntity("http://RIBBON-MEMBER/member/getActive", String.class);
		
		logger.info("entity.getBody : {},statusValue : {}",entity.getBody(),entity.getStatusCodeValue());
		
		logger.info("query active end.....");
		
		return RespResult.<String>create().setContent(entity.getBody());
	}
	
	
	@RequestMapping(value="/p",method=RequestMethod.POST)
	public RespResult<String> p(@RequestParam String userNo){
		logger.info("query p start.....，userNo = {}",userNo);
		
		
		logger.info("query p end.....");
		
		return RespResult.<String>create().setContent("SUCCESS");
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/test", method = RequestMethod.POST)
	public RespResult<ResultOutput> test(ResultInput input) {
		long start = System.currentTimeMillis();
		logger.info("input:{}", input);
		
		ResultOutput out = new ResultOutput();
		
		System.out.println(System.currentTimeMillis() - start);
		
		return RespResult.<ResultOutput> create().setContent(out);
	}
	
	@ResponseBody
	@RequestMapping(value = "/demo", method = RequestMethod.POST)
	public RespResult<String> test() {
		long start = System.currentTimeMillis();
		
		DemoOuput demo = demoService.getDemo("张三");
		
		System.out.println(System.currentTimeMillis() - start);
		
		return RespResult.<String> create().setContent(demo.toString());
	}
	
	@ResponseBody
	@RequestMapping(value = "/entity", method = RequestMethod.POST)
	public RespResult<String> entity(DemoInput input) {
		long start = System.currentTimeMillis();
		
		logger.info("input = {}",input);
		
		DemoOuput demo = demoService.getEntity(input);
		
		System.out.println(System.currentTimeMillis() - start);
		
		return RespResult.<String> create().setContent(demo.toString());
	}
}
