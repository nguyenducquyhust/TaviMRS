package com.tavi.tavi_mrs.entities.hinh_anh;

import com.tavi.tavi_mrs.entities.base.BaseEntity;
import com.tavi.tavi_mrs.entities.nguoi_dung.NguoiDung;
import com.tavi.tavi_mrs.entities.vi_pham.ViPham;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "Hinh_Anh_Vi_Pham", schema = "dbo")
public class HinhAnhViPham extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_hinh_anh_vi_pham")
    private Integer idHinhAnhViPham;

    @ManyToOne
    @JoinColumn(name = "vi_pham_id")
    private ViPham viPham;

    @OneToOne
    @JoinColumn(name = "hinh_anh_id")
    private HinhAnhLuuTru hinhAnhLuuTru;
}
