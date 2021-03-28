package com.tavi.tavi_mrs.service_impl.man_hinh_theo_doi;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tavi.tavi_mrs.entities.cms.organization.ThongTinDoanhNghiep;
import com.tavi.tavi_mrs.entities.man_hinh_theo_doi.ThietBiMatKetNoi;
import com.tavi.tavi_mrs.service.man_hinh_theo_doi.ThongTinKetNoiMoService;
import com.tavi.tavi_mrs.service_impl.lich_su_ket_noi.LichSuKetNoiServiceImpl;
import org.apache.http.client.fluent.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ThongTinKetNoiMoServiceImpl implements ThongTinKetNoiMoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ThongTinKetNoiMoServiceImpl.class);

    @Override
    public List<ThietBiMatKetNoi> pingMo(String urlMo) {
        try {
            List<ThietBiMatKetNoi> thietBiMatKetNois = new ArrayList<>();
            String response = Request.Get(urlMo)
                    .addHeader("Content-type", "application/json; charset=utf-8")
                    .execute().returnContent().asString();

            ObjectMapper om = new ObjectMapper();
            JsonNode rootNode = om.readTree(response);
            System.out.println(response);

            if (rootNode.get("message").asText().equals("found")) {
                JsonNode dataNode = rootNode.get("data");
                thietBiMatKetNois = om.convertValue(dataNode, new TypeReference<List<ThietBiMatKetNoi>>() {});
                return thietBiMatKetNois;

            } else if (rootNode.get("message").asText().equals("not found")) {
                return thietBiMatKetNois;

            } else return null;

        } catch (Exception ex) {
            LOGGER.error("ThongTinKetNoiMo error", ex);
            ex.printStackTrace();
            return null;
        }
    }
}
