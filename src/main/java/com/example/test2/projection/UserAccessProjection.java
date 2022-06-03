package com.example.test2.projection;

import com.example.test2.entity.UserAccessLog;

import java.util.Date;

public interface UserAccessProjection {

    Long getCount();

    Long getId();

    String getStatus();

    String getUserAgent();

    String getIp();

    Date getDate();
}
