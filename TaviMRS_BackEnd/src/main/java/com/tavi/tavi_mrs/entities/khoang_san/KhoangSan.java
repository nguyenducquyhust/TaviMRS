package com.tavi.tavi_mrs.entities.khoang_san;

import com.tavi.tavi_mrs.entities.base.BaseEntity;
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
@Table(name = "Khoang_San", schema = "dbo")
public class KhoangSan extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_khoang_san")
    private Integer idKhoangSan;

    @Column(name = "ten_khoang_san")
    private String tenKhoangSan;

    @ManyToOne
    @JoinColumn(name = "nhom_khoang_san_id")
    private NhomKhoangSan nhomKhoangSan;

    private Boolean xoa;
}
