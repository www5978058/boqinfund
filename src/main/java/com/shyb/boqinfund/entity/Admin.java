package com.shyb.boqinfund.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 20180801800000
 */
@Data
@Accessors(chain = true)
public class Admin extends BaseEntity implements Serializable {
    private Integer id;

    private String username;

    private String password;

    private Date ctime;

    private Date mtime;

    private static final long serialVersionUID = 1L;

}
