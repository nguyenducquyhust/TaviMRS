package com.tavi.tavi_mrs.repository.quyen;

import com.tavi.tavi_mrs.entities.mo.Mo;
import com.tavi.tavi_mrs.entities.quyen.NhomQuyen;
import com.tavi.tavi_mrs.entities.thiet_bi.ThietBi;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.Optional;

public interface NhomQuyenRepo extends JpaRepository<NhomQuyen, Integer> {
    Optional<NhomQuyen> findByIdNhomQuyen(int idNhomQuyen);

    @Query(value = "from NhomQuyen n order by n.idNhomQuyen asc ")
    Page<NhomQuyen> findAllToPage(Pageable pageable);

}
