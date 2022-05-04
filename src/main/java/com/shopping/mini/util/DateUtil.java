package com.shopping.mini.util;

import org.springframework.stereotype.Service;

import java.sql.Timestamp;

/**
 * Created by @author Kaushalya Rajapakshe on 2022-04-07
 */
@Service
public class DateUtil {
    public Timestamp getDateTime() {
        long now = System.currentTimeMillis();
        return new Timestamp(now);
    }
}
