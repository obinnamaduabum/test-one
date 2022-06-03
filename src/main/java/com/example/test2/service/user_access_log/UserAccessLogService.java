package com.example.test2.service.user_access_log;

import com.example.test2.entity.UserAccessLog;
import com.example.test2.projection.UserAccessProjection;

import java.util.Date;
import java.util.List;

public interface UserAccessLogService {

    void save(UserAccessLog userAccessLog);

    void bulk(List<UserAccessLog> accessLogList);

    List<UserAccessLog> findByLimit();

    Long getTotalCount();

    List<UserAccessProjection> findAllHavingMoreThanHundredWithDateRange(Date startDate, Date endDate);

    List<UserAccessProjection> findAllHavingMoreThanHundredWithDateRangeWithDateAsString(String startDate, String endDate);
}
