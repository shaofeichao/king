package com.rest.rest_server.pub.exception.bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.concurrent.ConcurrentHashMap;


public class JsonObj {
	public static final String JSON_DATE_TIME_FMT = "yyyyMMddHHmmss";
	private static ConcurrentHashMap<Long, ObjectMapper> objectMapperMap;
	private static Logger logger = LoggerFactory.getLogger(JsonObj.class);
	private static String logOutFlag;


	static {
		objectMapperMap = new ConcurrentHashMap<>();
	}

	public String toJsonStr() {
		ObjectMapper objectMapper = getCachedObjectMapper();
		String s = null;
		try {
			s = objectMapper.writeValueAsString(this);
		} catch (Exception e) {
			logger.warn("writeValueAsString exception", e);
		}
		return s;
	}

	public String toJsonStrWithThrow() throws JsonProcessingException {
		ObjectMapper objectMapper = getCachedObjectMapper();
		return objectMapper.writeValueAsString(this);
	}

	public static ObjectMapper getCachedObjectMapper() {
		long threadId = Thread.currentThread().getId();
		ObjectMapper objectMapper = objectMapperMap.get(threadId);
		if (objectMapper == null) {
			objectMapper = new ObjectMapper();
			objectMapper.setDateFormat(new SimpleDateFormat(JSON_DATE_TIME_FMT));
			
			objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
			
			objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
			objectMapperMap.putIfAbsent(threadId, objectMapper);
		}
		return objectMapper;
	}

	public static void evictCachedObjectMapper(Long threadId) {
		if (threadId == null) {
			threadId = Thread.currentThread().getId();
		}
		objectMapperMap.remove(threadId);
	}

	public static void clearCachedObjectMapper() {
		objectMapperMap.clear();
	}
}
