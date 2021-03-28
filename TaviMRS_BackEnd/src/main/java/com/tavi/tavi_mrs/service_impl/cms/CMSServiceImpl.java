package com.tavi.tavi_mrs.service_impl.cms;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tavi.tavi_mrs.entities.cms.organization.ThongTinDoanhNghiep;
import com.tavi.tavi_mrs.service.cms.CMSService;
import org.apache.http.client.fluent.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CMSServiceImpl implements CMSService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CMSServiceImpl.class);

    @Value("${microservices.cms.host}")
    private String HOST;

    private static final String LOCAL_HOST = "http://localhost:9898/";

    @Override
    public Map<Integer, ThongTinDoanhNghiep> findThongTinDoanhNghiepIdByFilter(Map<String, String> params) {
        try {
            Map<Integer, ThongTinDoanhNghiep> thongTinDoanhNghiepMap = new HashMap<>();
            //String url = "http://localhost:9999/api/v1/admin/thong-tin-doanh-nghiep/searchToIds?district-id=" + Integer.valueOf(params.get("huyenId") + "&name=" + params.get("ten"));
            String url = HOST + "api/v1/public/organization/mrs/find-by-name-and-district?"
                    + "name=" + params.get("name")
                    + "&district-id=" + params.get("district-id")
                    + "&province-id=" + params.get("province-id");
            System.out.println(url);
            String response = Request.Get(url)
                    .addHeader("Authorization", params.get("Authorization"))
                    .addHeader("Content-type", "application/json; charset=utf-8")
                    .execute().returnContent().asString();

            ObjectMapper om = new ObjectMapper();
            JsonNode rootNode = om.readTree(response);
            System.out.println(response);
            if (rootNode.get("message").asText().equals("found")) {
                JsonNode dataNode = rootNode.get("data");
                thongTinDoanhNghiepMap = om.convertValue(dataNode, new TypeReference<Map<Integer, ThongTinDoanhNghiep>>() {});
                return thongTinDoanhNghiepMap;
            } else if (rootNode.get("message").asText().equals("not found")) {
                return thongTinDoanhNghiepMap;
            } else return null;
        } catch (Exception ex) {
            LOGGER.error("CMS error", ex);
            ex.printStackTrace();
            return null;
        }
    }
}
