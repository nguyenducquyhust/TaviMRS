package com.tavi.tavi_mrs.entities.base;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@MappedSuperclass
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CanhBao extends BaseEntity {

    @Column(name = "trang_thai_xu_ly")
    private Integer trangThaiXuLy;

    @Column(name = "thoi_gian_xay_ra")
    @Temporal(TemporalType.TIMESTAMP)
    private Date thoiGianXayRa;
}
