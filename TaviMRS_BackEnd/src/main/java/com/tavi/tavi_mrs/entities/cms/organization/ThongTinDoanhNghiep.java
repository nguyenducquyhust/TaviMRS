package com.tavi.tavi_mrs.entities.cms.organization;

import com.tavi.tavi_mrs.entities.cms.address.Address;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ThongTinDoanhNghiep {

    private Integer id;

    private String name;

    private String address;

    private String commune;

    private String district;

    private String province;
}
