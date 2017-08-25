package com.tuodao.bp.unit.test;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.google.common.base.Charsets;
import com.google.common.base.Preconditions;
import com.google.common.hash.Hashing;
import com.google.common.io.Files;
import com.google.gson.Gson;
import com.tuodao.bp.unit.test.enums.ContentType;
import com.tuodao.bp.utils.HmacSha1Utils;

public class BaseAPI {
	private static final Logger log = LoggerFactory.getLogger(BaseAPI.class);
    /** 请求来源时间格式 */
    private static final String REQUEST_TIME_FORMAT = "yyyyMMddHHmmss";
	// 对内
    protected String url = "http://192.168.0.200:20001";
	// 对外
	//public String url = "http://115.236.89.210:2001";

    protected HttpHeaders headers = new HttpHeaders();

    protected String accessId = "";
    protected String accessKey = "";
    protected String resp = "";

    private final RestTemplate testRestTemplate = new RestTemplate();

	private final Gson gson = new Gson();

    public BaseAPI(){}

	public BaseAPI(String url){
		this.url = url;
	}

	public <T> String doService(String action, ContentType type, T content){
		String requestUrl = url + "/" +action + "." + type.name().toLowerCase();

        // 转换成指定类型
		String requestBody = content(content, type);
        headers.set("format", type.name().toLowerCase());

		log.info(requestBody);
		try{

            String md5Sign = Hashing.md5().hashString(requestBody, Charsets.UTF_8).toString().toLowerCase();

			String hmacSha1Sign = HmacSha1Utils.signToString(md5Sign, accessKey, Charsets.UTF_8.name());

            headers.set("sign", URLEncoder.encode(hmacSha1Sign, Charsets.UTF_8.name()));

            headers.set("reqlength", String.valueOf(requestBody.length()));
            
		}catch(Exception e){
			throw new RuntimeException(e);
		}

        // post发送json
        HttpEntity<String> entity = new HttpEntity<String>(requestBody, headers);
        ResponseEntity<String> result = testRestTemplate.exchange(requestUrl, HttpMethod.POST, entity, String.class);
        resp = result.getBody();
        log.info(resp);
        return resp;
	}

    public <T> String doServiceDown(String action, ContentType type, T content){
        String requestUrl = url + "/" +action + "." + type.name().toLowerCase();

        // 转换成指定类型
        String requestBody = content(content, type);
        headers.set("format", type.name().toLowerCase());

        log.info(requestBody);
        try{

            String md5Sign = Hashing.md5().hashString(requestBody, Charsets.UTF_8).toString().toLowerCase();

            String hmacSha1Sign = HmacSha1Utils.signToString(md5Sign, accessKey, Charsets.UTF_8.name());

            headers.set("sign", URLEncoder.encode(hmacSha1Sign, Charsets.UTF_8.name()));

            headers.set("reqlength", String.valueOf(requestBody.length()));

        }catch(Exception e){
            throw new RuntimeException(e);
        }

        // post发送json
        HttpEntity<String> entity = new HttpEntity<String>(requestBody, headers);
        ResponseEntity< byte[]> result = testRestTemplate.exchange(requestUrl, HttpMethod.POST, entity, byte[].class);
        byte[] respBytes = result.getBody();
        try {
            System.out.println(respBytes.length);
            Files.write(respBytes,new File("E:/hello.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        log.info(resp);
		return resp;
    }

    /**
     * 将content装换为指定类型的字符串
     *
     * @param content   内容体
     * @param type      类型(JSON, XML)
     * @param <T>       内容体类型
     * @return          转换后字符串
     */
	public <T> String content(T content, ContentType type) {

        try {
            switch (type) {
                case XML:
                    headers.setContentType(MediaType.APPLICATION_XML);
                    return new XmlMapper().writer().writeValueAsString(content);
                case JSON:
                default:
                    headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
                    return new ObjectMapper().writer().writeValueAsString(content);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
	
	
	protected void responseJSON() {
    	JSONObject response = JSON.parseObject(resp);
    	Preconditions.checkNotNull(response);
    	Boolean flag = response.getBoolean("flag");
    	Integer code = response.getInteger("errorCode");
    	if(Boolean.FALSE.equals(flag) && code != 100000) {
    		log.info("not success,resp={}",resp);
    	}
    }
	protected void responseJSONParser() {
    	JSONObject response = JSON.parseObject(resp).getJSONObject("response");
    	Preconditions.checkNotNull(response);
		
		int code = response.getJSONObject("info").getInteger("code");
		if(code != 100000) {
			throw new RuntimeException("not success");
		}
		
    }
	
	protected void responseXML(String methodName, Object input){
		String resp = doService(methodName, ContentType.XML, input);
		Preconditions.checkNotNull(resp);
	}
}
