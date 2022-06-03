package com.example.test2.service.user_access_log;

import com.example.test2.entity.UserAccessLog;
import com.example.test2.projection.UserAccessProjection;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

public interface UserAccessLogService {

    void save(UserAccessLog userAccessLog);

    void bulk(List<UserAccessLog> accessLogList);

    List<UserAccessLog> findByLimit();

    Long getTotalCount();

    List<UserAccessLog> setUpData(String fileName) throws ParseException, FileNotFoundException;

    List<UserAccessProjection> findAllHavingMoreThanHundredWithDateRange(Date startDate, Date endDate, long range);
}
