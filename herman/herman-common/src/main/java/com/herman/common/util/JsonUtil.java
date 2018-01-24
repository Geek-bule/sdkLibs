package com.herman.common.util;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * json工具类
 * Created by herman on 2017/9/14.
 */
public class JsonUtil {

    private static final ObjectMapper mapper = new ObjectMapper();

    public static String objectToJson(Object obj) {
        String result = null;

        try {
            result = mapper.writeValueAsString(obj);
        } catch (JsonGenerationException var3) {
            var3.printStackTrace();
        } catch (JsonMappingException var4) {
            var4.printStackTrace();
        } catch (IOException var5) {
            var5.printStackTrace();
        }

        return result;
    }

    /**
     * json字符串转为Map对象
     * @param jsonString
     * @return
     */
    public static Map jsonToMap(String jsonString){
        Map map = new HashMap();
        try {
            map=mapper.readValue(jsonString, Map.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }
}
