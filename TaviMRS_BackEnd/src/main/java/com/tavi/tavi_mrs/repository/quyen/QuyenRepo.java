package com.tavi.tavi_mrs.repository.quyen;

import com.tavi.tavi_mrs.entities.quyen.NhomQuyen;
import com.tavi.tavi_mrs.entities.quyen.Quyen;
import com.tavi.tavi_mrs.entities.thiet_bi.ThietBi;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface QuyenRepo extends JpaRepository<Quyen, Integer> {
    Optional<Quyen> findByIdQuyen(int idQuyen);

    @Query(value = "from Quyen q order by q.idQuyen asc ")
    Page<Quyen> findAllToPage(Pageable pageable);

}
