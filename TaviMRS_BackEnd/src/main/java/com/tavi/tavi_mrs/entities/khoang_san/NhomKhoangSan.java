package com.tavi.tavi_mrs.entities.khoang_san;

import com.tavi.tavi_mrs.entities.base.BaseEntity;
import com.tavi.tavi_mrs.entities.nguoi_dung.NguoiDung;
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
@Table(name = "Nhom_Khoang_San", schema = "dbo")
public class NhomKhoangSan extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_nhom_khoang_san")
    private Integer idNhomKhoangSan;

    @Column(name = "ten_nhom_khoang_san")
    private String tenNhomKhoangSan;

    private Boolean xoa;
}