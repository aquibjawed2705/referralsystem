package com.suggest.referal.ReferalSystem.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {

	private static ObjectMapper objectMapper = new ObjectMapper();

	public static <T> T convert(String value, Class<T> clazz) throws JsonProcessingException {
		return objectMapper.readValue(value, clazz);
	}

	public static <T> T convert(JsonNode jsonNode, Class<T> clazz) throws JsonProcessingException {
		return objectMapper.treeToValue(jsonNode, clazz);
	}

}
