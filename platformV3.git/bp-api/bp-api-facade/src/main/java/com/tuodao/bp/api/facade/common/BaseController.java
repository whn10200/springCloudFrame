package com.tuodao.bp.api.facade.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.client.AsyncRestTemplate;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSON;
import com.tuodao.bp.api.core.constant.FrameConstant;
import com.tuodao.bp.api.core.response.RespResult;


@Component
public class BaseController {

	private RestTemplate rest;

	private AsyncRestTemplate asynRest;

	protected <T> RespResult<T> parseRespResult(ResponseEntity<String> entity) {
		RespResult<T> pr = JSON.parseObject(entity.getBody(), RespResult.class);
		return pr;
	}

	protected <T> Object parseContent(ResponseEntity<String> entity, Class<T> clazz) {
		RespResult<T> pr = parseRespResult(entity);
		if (pr.getCode() == FrameConstant.SUCCESS_CODE) {
			T parseObject = JSON.parseObject(pr.getContent().toString(), clazz);
			return parseObject;
		} else {
			return pr;
		}
	}

	protected boolean isSuccess(ResponseEntity<String> entity) {
		RespResult<Object> pr = parseRespResult(entity);
		return FrameConstant.SUCCESS_CODE == pr.getCode() && pr.isSuccess();
	}
	
	protected <T> T parseContent(RespResult<T> result, Class<T> clazz) {
		T parseObject = JSON.parseObject(result.getContent().toString(), clazz);
		return parseObject;
	}

	protected <T> ResponseEntity<T> exchange(String url, HttpMethod method, Object obj, Class<T> respClazz,
			Object... uriVariables) {

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

		HttpEntity<String> requestEntity = new HttpEntity<String>(JSON.toJSONString(obj), headers);

		ResponseEntity<T> exchange = rest.exchange(url, method, requestEntity, respClazz, uriVariables);
		return exchange;
	}
	
	
	protected <T> ListenableFuture<ResponseEntity<T>> exchangeAsyn(String url, HttpMethod method, Object obj, Class<T> respClazz,
			Object... uriVariables) {

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

		HttpEntity<String> requestEntity = new HttpEntity<String>(JSON.toJSONString(obj), headers);

		ListenableFuture<ResponseEntity<T>> exchangeAsyn = asynRest.exchange(url, method, requestEntity, respClazz, uriVariables);
		return exchangeAsyn;
	}

}
