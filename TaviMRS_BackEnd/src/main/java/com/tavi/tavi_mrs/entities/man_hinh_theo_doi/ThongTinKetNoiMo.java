package com.tavi.tavi_mrs.entities.man_hinh_theo_doi;

import com.tavi.tavi_mrs.entities.mo.Mo;
import com.tavi.tavi_mrs.entities.vi_pham.LoaiViPham;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ThongTinKetNoiMo {

    private Mo mo;
    private Integer trangThaiKetNoi;
    private Integer tongThietBiMatKetNoi;
    private Set<LoaiViPham> loaiViPhamSet = new HashSet<>();
    private Integer tongXe;
}
