package com.tuodao.bp.api.facade.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.common.base.Joiner;
import com.google.common.collect.Maps;
import com.tuodao.bp.api.core.response.RespResult;

@RequestMapping("/router")
@RestController
public class FacadeMemberController {

	private static final Logger logger = LoggerFactory.getLogger(FacadeMemberController.class);

	private RestTemplate rest;

	@RequestMapping("/getMemberInfo")
	public RespResult<String> getMemberInfo(@RequestParam String userNo, @RequestParam String userName) {

		logger.info("userNO [{}],userName [{}]", userNo, userName);

		logger.info("get member info...from memberController...");
		ResponseEntity<String> memberResult = rest.getForEntity("http://localhost:15001/member/name", String.class);
		String mResult = memberResult.getBody();

		logger.info("get pay info...from payController...");
		ResponseEntity<String> payResult = rest.postForEntity(
				"http://localhost:15002/pay/getPayInfoFromRouter?userNo={userNo}", null, String.class, userNo);
		String pResult = payResult.getBody();

		logger.info("get task info...from taskController...");
		// ResponseEntity<String> taskResult =
		// rest.getForEntity("http://RIBBON-TASK:15003/task/taskInfo",
		// String.class, userNo);
		String tResult = "task is wrong..";

		String content = Joiner.on(",").skipNulls().join("mResult=" + mResult, "pResult=" + pResult,
				"tResult=" + tResult);

		return RespResult.<String> create().setContent(content);
	}

	@RequestMapping("/getMemberInfoe")
	public RespResult<String> getMemberInfoe(@RequestParam String userNo) {

		logger.info("userNO [{}]", userNo);

		logger.info("get member info...from memberController...with Exception");

		Map<String, Object> map = Maps.newHashMap();
		map.put("name", "Polly");
		map.put("age", "13");
		String json = "";
		try {
			json = Jackson2ObjectMapperBuilder.json().build().writeValueAsString(map);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

		HttpEntity<?> requestEntity = new HttpEntity<>(json, headers);

		
		ResponseEntity<String> result = null;
		try {
			result = rest.exchange("http://localhost:15001/member/getMemberException",HttpMethod.POST, requestEntity, String.class);
		}catch(Exception ex) {
			logger.error("|"+ex.getMessage()+"|",ex);
			if(null == result) {
				result = new ResponseEntity<String>("memberException", HttpStatus.BAD_REQUEST);
			}
		}
		logger.info("memberResult = {}", result);

		logger.info("get pay info...from payController...with Exception");

		Map<String, Object> pay = Maps.newHashMap();
		pay.put("name", "Polly");
		pay.put("age", "13");
		String payjson = "";
		try {
			payjson = Jackson2ObjectMapperBuilder.json().build().writeValueAsString(map);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		HttpHeaders payHeaders = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

		HttpEntity<?> payRequestEntity = new HttpEntity<>(payjson, payHeaders);

		ResponseEntity<String> payResult = null;
		
		try {
			payResult = rest.exchange("http://localhost:15002/pay/getPayException", HttpMethod.POST,payRequestEntity, String.class);
		}catch(Exception e) {
			logger.error("/"+e.getMessage()+"/",e);
			payResult = new ResponseEntity<String>("payException", HttpStatus.BAD_REQUEST);
		}
		
		logger.info("payResult = {}", payResult);

		logger.info("get task info...from taskController...with Exception");
		String taskResult = "NO-TASK-EXCEPTION";

		String join = Joiner.on("|").skipNulls().join(result.getBody(), payResult.getBody(), taskResult);

		return RespResult.<String> create().setContent(join);
	}
}
