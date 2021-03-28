package com.tavi.tavi_mrs.entities.thiet_bi;

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
@Table(name = "Camera", schema = "dbo")
public class Camera extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_camera")
    private Integer idCamera;

    @ManyToOne
    @JoinColumn(name = "thiet_bi_id")
    private ThietBi thietBi;

    @Column(name = "thong_so")
    private String thongSo;

    @Column(name = "thu_tu_uu_tien")
    private Integer thuTuUuTien;

    private Boolean xoa;
}
