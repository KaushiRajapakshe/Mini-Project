package com.shopping.mini.model;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

/**
 * Created by @author Kaushalya Rajapakshe on 2022-03-24
 */
@Getter
@Setter
public class Category {
    private int categoryId;
    private String categoryName;
    private Timestamp addedDate;
    private Timestamp updatedDate;
    private boolean active;
    private String imgURL;

    public boolean getActive() {
        return this.active;
    }
}
