package com.example.test2.service.user_access_log;

import com.example.test2.dao.UserAccessLogDao;
import com.example.test2.entity.UserAccessLog;
import com.example.test2.projection.UserAccessProjection;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class UserAccessLogServiceImpl implements UserAccessLogService {

    private final UserAccessLogDao userAccessLogDao;

    public UserAccessLogServiceImpl(UserAccessLogDao userAccessLogDao) {
        this.userAccessLogDao = userAccessLogDao;
    }

    @Transactional
    @Override
    public void save(UserAccessLog userAccessLog) {
        this.userAccessLogDao.save(userAccessLog);
    }


    @Transactional
    @Override
    public void bulk(List<UserAccessLog> accessLogList) {
        for(UserAccessLog userAccessLog: accessLogList) {
            this.userAccessLogDao.save(userAccessLog);
        }
    }

    @Override
    public List<UserAccessLog> findByLimit() {
        return null;
    }

    @Override
    public Long getTotalCount() {
        return this.userAccessLogDao.getTotalCount();
    }

    @Override
    public List<UserAccessProjection> findAllHavingMoreThanHundredWithDateRange(Date startDate, Date endDate) {
        return this.userAccessLogDao.findAllHavingMoreThanHundredWithDateRange(startDate, endDate);
    }

    @Override
    public List<UserAccessProjection> findAllHavingMoreThanHundredWithDateRangeWithDateAsString(String startDate, String endDate) {
        return this.userAccessLogDao.findAllHavingMoreThanHundredWithDateRangeWithString(startDate, endDate);
    }
}
