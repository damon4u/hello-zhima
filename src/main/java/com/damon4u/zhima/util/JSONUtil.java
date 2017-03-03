package com.damon4u.zhima.util;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Writer;

public class JSONUtil {
    private static final Logger log = LoggerFactory.getLogger(JSONUtil.class);

    private static final ObjectMapper mapper = new ObjectMapper();
    private static final JsonFactory jsonFactory = new JsonFactory();

    static {
        //设置输入时忽略在JSON字符串中存在但Java对象实际没有的属性
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    }

    public static ObjectMapper getMapper() {
        return mapper;
    }

    public static <T> T fromJson(String jsonAsString, Class<T> pojoClass) {
        if (StringUtils.isEmpty(jsonAsString)) {
            return null;
        }
        try {
            return mapper.readValue(jsonAsString, pojoClass);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    public static String toJson(Object pojo){
        try {
            return mapper.writeValueAsString(pojo);
        } catch (JsonProcessingException e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    public static void writeJson(Object pojo,Writer writer){
        try {
            JsonGenerator jg = jsonFactory.createGenerator(writer);
            mapper.writeValue(jg, pojo);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }
}
