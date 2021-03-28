package com.tavi.tavi_mrs.entities.cms.address;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {

    private Integer id;

    private String location;

    private String code;

    private Boolean deleted;

    private Commune commune;

    private District district;

    private  Province province;

    private Country country;

}
