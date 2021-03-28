package com.tavi.tavi_mrs.entities.xe_van_chuyen;

import com.tavi.tavi_mrs.entities.base.BaseEntity;
import com.tavi.tavi_mrs.entities.hinh_anh.HinhAnhLuuTru;
import com.tavi.tavi_mrs.entities.khoang_san.KhoangSan;
import com.tavi.tavi_mrs.entities.mo.Mo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@NamedNativeQuery(
        name = "NhatKyXeVaoMo.abstractBieuDoByKhoangSan",
        query =  "SELECT CONVERT(VARCHAR(2), MONTH(thoi_gian_ra)) as x, SUM(trong_luong_khoang_san) AS y\n" +
                "FROM TaviMineralResources_DEV.dbo.Nhat_Ky_Xe_Vao_Mo\n" +
                "WHERE khoang_san_id = ?1\n" +
                "GROUP BY MONTH(thoi_gian_ra);",
        resultSetMapping = "BieuDoMapping"
)
@NamedNativeQuery(
        name = "NhatKyXeVaoMo.abstractBieuDoByDoanhNghiep",
        query = "SELECT CONVERT(VARCHAR(12), d.thong_tin_doanh_nghiep_id) AS x, SUM(trong_luong_khoang_san) AS y\n" +
                "FROM TaviMineralResources_DEV.dbo.Nhat_Ky_Xe_Vao_Mo AS n\n" +
                "INNER JOIN TaviMineralResources_DEV.dbo.Mo AS m\n" +
                "ON n.mo_id = m.id_mo \n" +
                "INNER JOIN TaviMineralResources_DEV.dbo.Doanh_Nghiep AS d\n" +
                "ON m.doanh_nghiep_id = d.id_doanh_nghiep\n" +
                "WHERE n.khoang_san_id = ?1 \n" +
                "GROUP BY d.thong_tin_doanh_nghiep_id;",
        resultSetMapping = "BieuDoMapping"
)

@SqlResultSetMapping(
        name = "BieuDoMapping",
        classes = @ConstructorResult(
                targetClass = com.tavi.tavi_mrs.entities.bieu_do.BieuDo.class, columns = { @ColumnResult(name = "x"), @ColumnResult(name = "y") })
)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "Nhat_Ky_Xe_Vao_Mo", schema = "dbo")
public class NhatKyXeVaoMo extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_nhat_ky_xe_vao_mo")
    private Integer idNhatKyXeVaoMo;

    @ManyToOne
    @JoinColumn(name = "xe_van_chuyen_id")
    private XeVanChuyen xeVanChuyen;

    @ManyToOne
    @JoinColumn(name = "mo_id")
    private Mo mo;

    @Column(name = "thoi_gian_vao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date thoiGianVao;

    @Column(name = "thoi_gian_ra")
    @Temporal(TemporalType.TIMESTAMP)
    private Date thoiGianRa;

    @ManyToOne
    @JoinColumn(name = "khoang_san_id")
    private KhoangSan khoangSan;

    @ManyToOne
    @JoinColumn(name = "hinh_anh_vao_id")
    private HinhAnhLuuTru hinhAnhVao;

    @ManyToOne
    @JoinColumn(name = "hinh_anh_ra_id")
    private HinhAnhLuuTru hinhAnhRa;

    @Column(name = "trong_luong_khoang_san")
    private Double trongLuongKhoangSan;
}
