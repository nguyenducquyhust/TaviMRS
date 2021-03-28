package com.tavi.tavi_mrs.entities.quyen;

import com.tavi.tavi_mrs.entities.base.BaseEntity;
import com.tavi.tavi_mrs.entities.nguoi_dung.NguoiDung;
import com.tavi.tavi_mrs.entities.thong_bao.ThongBao;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "Nhom_Quyen", schema = "dbo")
public class NhomQuyen extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_nhom_quyen")
    private Integer idNhomQuyen;

    @Column(name = "ten_nhom_quyen")
    private String tenNhomQuyen;

//    @ManyToMany(mappedBy = "nhomQuyenCoQuyen")
//    private List<Quyen> danhSachQuyen;

    @ManyToMany
    @JoinTable(name = "Nhom_Quyen_Co_Quyen",
                joinColumns = @JoinColumn(name = "nhom_quyen_id"),
                inverseJoinColumns = @JoinColumn(name = "quyen_id"))
    private List<Quyen> nhomQuyenCoQuyen;
}
