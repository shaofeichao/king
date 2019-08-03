package com.sfc.sso_server.pub.utils;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.concurrent.ConcurrentHashMap;

public class JsonObj {
	public static final String _DATE_TIME_FMT = "yyyy-MM-dd/HH:mm:ss.SSS";
	private static ConcurrentHashMap<Long, ObjectMapper> _ObjectMapperMap;
	private static Logger L = LoggerFactory.getLogger(JsonObj.class);

	static {
		_ObjectMapperMap = new ConcurrentHashMap<>();
	}

	public String toJsonStr() {
		ObjectMapper object_mapper_ = GetCachedObjectMapper();
		String s = null;
		try {
			s = object_mapper_.writeValueAsString(this);
		} catch (Exception e) {
			L.warn("writeValueAsString exception", e);
		}
		return s;
	}

	public String toJsonStrWithThrow() throws JsonProcessingException {
		ObjectMapper object_mapper_ = GetCachedObjectMapper();
		return object_mapper_.writeValueAsString(this);
	}

	public static ObjectMapper GetCachedObjectMapper() {
		long thread_id_ = Thread.currentThread().getId();
		ObjectMapper object_mapper_ = _ObjectMapperMap.get(thread_id_);
		if (object_mapper_ == null) {
			object_mapper_ = new ObjectMapper();
			object_mapper_.setDateFormat(new SimpleDateFormat(_DATE_TIME_FMT));
			object_mapper_.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
			object_mapper_.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
			_ObjectMapperMap.putIfAbsent(thread_id_, object_mapper_);
		}
		return object_mapper_;
	}

	public static void EvictCachedObjectMapper(Long thread_id) {
		if (thread_id == null) {
			thread_id = Thread.currentThread().getId();
		}
		_ObjectMapperMap.remove(thread_id);
	}

	public static void ClearCachedObjectMapper() {
		_ObjectMapperMap.clear();
	}
}
