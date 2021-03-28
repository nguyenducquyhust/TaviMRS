package com.tavi.tavi_mrs.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tavi.tavi_mrs.entities.cms.organization.ThongTinDoanhNghiep;
import com.tavi.tavi_mrs.service_impl.xe_van_chuyen.NhatKyXeVaoMoServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class JsonUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(JsonUtils.class);
    private static ObjectMapper om = new ObjectMapper();

    public static String readDataStringFromJsonResult(String response) throws JsonProcessingException, JsonMappingException {
        JsonNode rootNode = om.readTree(response);
        LOGGER.info(response);
        if (rootNode.get("message").asText().equals("found")) {
            JsonNode dataNode = rootNode.get("data");
            return om.convertValue(dataNode, String.class);
        } else if (rootNode.get("message").asText().equals("not found")) {
            return null;
        } else return null;
    }
}
