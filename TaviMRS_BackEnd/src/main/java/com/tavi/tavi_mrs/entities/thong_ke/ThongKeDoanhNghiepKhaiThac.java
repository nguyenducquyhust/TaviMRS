package com.tavi.tavi_mrs.entities.thong_ke;

import com.tavi.tavi_mrs.entities.giay_phep_khai_thac.GiayPhepKhaiThac;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ThongKeDoanhNghiepKhaiThac {

    Map<Integer, List<String[]>> dataForm = new HashMap<>();
    String diaBan;

//    String[] contains the following order:

//    String stt;
//    String thongTinQuyetDinh;
//    String coQuanCap;
//    String tenDoanhNghiep;
//    String viTriMo;
//    String loaiKhoangSan;
//    String dienTich;
//    String truLuongVaCongSuat;
//    String thoiHanCapPhep;

}
