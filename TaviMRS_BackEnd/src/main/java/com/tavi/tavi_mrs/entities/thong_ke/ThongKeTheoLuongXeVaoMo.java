package com.tavi.tavi_mrs.entities.thong_ke;


import com.tavi.tavi_mrs.entities.mo.Mo;
import com.tavi.tavi_mrs.entities.xe_van_chuyen.XeVanChuyen;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ThongKeTheoLuongXeVaoMo {

    private XeVanChuyen xeVanChuyen;
    private Mo mo;
    private Long soLuot;
    private Double tong;
}
