package com.tavi.tavi_mrs.entities.man_hinh_theo_doi;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ThietBiMatKetNoi {
    Integer idThietBi;
    String viTri;
    String moTa;
}
