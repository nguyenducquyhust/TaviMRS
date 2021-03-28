package com.tavi.tavi_mrs.entities.cms.address;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Commune {

    private Integer id;

    private String code;

    private String name;

    private String level;

    private Boolean deleted;

    private District district;
}
