package com.tavi.tavi_mrs.entities.mo;

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
@Table(name = "Nhat_Ky_Khai_Thac", schema = "dbo")
public class NhatKyKhaiThac extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_nhat_ky_khai_thac")
    private Integer idNhatKyKhaiThac;

    @Temporal(TemporalType.DATE)
    private Date ngay;

    @Column(name = "tru_luong_khai_thac")
    private Double truLuongKhaiThac;

    @ManyToOne
    @JoinColumn(name = "mo_id")
    private Mo mo;

}
