package com.tavi.tavi_mrs.entities.bieu_do;

import com.tavi.tavi_mrs.entities.doanh_nghiep.DoanhNghiep;
import com.tavi.tavi_mrs.entities.khoang_san.KhoangSan;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BieuDo {

    private String x;

    private Double y;

    public BieuDo(DoanhNghiep x, Double y) {
        this.x = x == null ? null : x.getTenDoanhNghiep();
        this.y = y;
    }

    public BieuDo(KhoangSan x, Double y) {
        this.x = x == null ? null : x.getTenKhoangSan();
        this.y = y;
    }

}