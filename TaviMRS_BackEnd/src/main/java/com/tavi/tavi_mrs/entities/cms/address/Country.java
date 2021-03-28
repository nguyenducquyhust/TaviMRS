package com.tavi.tavi_mrs.entities.cms.address;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Country implements Serializable {

    private Integer id;

    private String code;

    private String name;

    private Boolean deleted;
}
