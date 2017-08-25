package com.tuodao.bp.api.facade.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.AsyncRestTemplate;
import org.springframework.web.client.RestTemplate;

import com.tuodao.bp.api.core.response.RespResult;
import com.tuodao.bp.api.facade.common.BaseController;
import com.tuodao.bp.api.facade.domain.ResultInput;
import com.tuodao.bp.api.facade.domain.ResultOutput;

@RequestMapping("/router")
@RestController
public class ResultController extends BaseController {

	private static final Logger logger = LoggerFactory.getLogger(ResultController.class);

	private RestTemplate rest;

	private AsyncRestTemplate asynRest;

	@ResponseBody
	@RequestMapping(value = "/getQueryResult", method = RequestMethod.POST)
	public RespResult<ResultOutput> getQueryResult(ResultInput input) {
		long start = System.currentTimeMillis();
		logger.info("input:{}", input);
	
		ResultOutput out = new ResultOutput();
		
		System.out.println(System.currentTimeMillis() - start);
		return RespResult.<ResultOutput> create().setContent(out);
	}


}
