package com.tavi.tavi_mrs.service.cms;

import com.tavi.tavi_mrs.entities.cms.organization.ThongTinDoanhNghiep;

import java.util.List;
import java.util.Map;

public interface CMSService {

    Map<Integer, ThongTinDoanhNghiep> findThongTinDoanhNghiepIdByFilter(Map<String, String> params);
}
