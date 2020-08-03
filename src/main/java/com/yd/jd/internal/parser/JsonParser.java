package com.yd.jd.internal.parser;


import java.util.Iterator;
import java.util.Map;

import com.yd.jd.common.exception.JdException;
import com.yd.jd.internal.JSON.JSON;
import com.yd.jd.common.response.AbstractResponse;
import com.yd.jd.utils.StringUtil;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.DeserializationConfig.Feature;
import org.codehaus.jackson.node.JsonNodeFactory;
import org.codehaus.jackson.node.ObjectNode;

public class JsonParser implements Parser {
    private final ObjectMapper mapper = new ObjectMapper();

    public JsonParser() {
        this.mapper.getDeserializationConfig().set(Feature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
        this.mapper.getDeserializationConfig().set(Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        this.mapper.getDeserializationConfig().set(Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public <T extends AbstractResponse> T parse(String json, Class<T> responseClass, String apiName) throws JdException {
        T response = null;

        try {
            if (StringUtil.isEmpty(json)) {
                throw new JdException("response json is empty!");
            } else {
                response = this.fromJson(json, responseClass, apiName);
//                if (response != null) {
//                    response.setMsg(json);
//                }

                return response;
            }
        } catch (Exception var6) {
            throw new JdException(var6);
        }
    }

    public <T extends AbstractResponse> T fromJson(String json, Class<T> responseClass, String apiName) throws Exception {
        ObjectNode rootNode = null;

        try {
            rootNode = (ObjectNode) this.mapper.readTree(json);
        } catch (Exception var9) {
            rootNode = (ObjectNode) this.mapper.readTree(JSON.toString(JSON.parse(json)));
        }

        String innerJson = "";
        if (apiName.startsWith("jd.union.") && rootNode.get("error_response") == null) {
            ObjectNode node = new ObjectNode(JsonNodeFactory.instance);
            Iterator iterator = ((JsonNode) ((Map.Entry) rootNode.getFields().next()).getValue()).getFields();

            while (iterator.hasNext()) {
                Map.Entry<String, JsonNode> next = (Map.Entry) iterator.next();
                if (((String) next.getKey()).equals("code")) {
                    node.put((String) next.getKey(), (JsonNode) next.getValue());
                } else if (next.getKey().equals("msg")) {
                    node.put((String) next.getKey(), (JsonNode) next.getValue());
                } else {
                    node.put((String) next.getKey(), this.mapper.readTree(((JsonNode) next.getValue()).asText()));

                }
            }

            innerJson = node.toString();
        } else {
            //请求出错的情况
            ObjectNode node = new ObjectNode(JsonNodeFactory.instance);
            Iterator iterator = rootNode.getFields().next().getValue().getFields();
            while (iterator.hasNext()) {
                Map.Entry<String, JsonNode> next = (Map.Entry) iterator.next();
                if (next.getKey().equals("code")) {
                    node.put(next.getKey(), next.getValue());
                } else if (next.getKey().equals("zh_desc")) {
                    node.put("zhDesc", next.getValue());
                }
            }
            innerJson = node.toString();
        }

        return this.mapper.readValue(innerJson, responseClass);
    }
}
