package com.tavi.tavi_mrs.entities.thong_ke;

import com.tavi.tavi_mrs.entities.vi_pham.ViPham;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ThongKeSaiPham {
    List<ViPham> saiPhamList = new ArrayList<>();
}
