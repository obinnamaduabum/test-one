package com.example.test2.service.user_access_log;

import com.example.test2.dao.UserAccessLogDao;
import com.example.test2.entity.UserAccessLog;
import com.example.test2.projection.UserAccessProjection;
import com.example.test2.utils.MyUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

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
    public List<UserAccessProjection> findAllHavingMoreThanHundredWithDateRange(Date startDate,
                                                                                Date endDate,
                                                                                long range) {
        return this.userAccessLogDao.findAllHavingMoreThanHundredWithDateRange(startDate, endDate, range);
    }

    public List<UserAccessLog> setUpData(String fileName) throws ParseException, FileNotFoundException {
        List<UserAccessLog> userAccessLogList = new ArrayList<>();

        File myObj = new File("src/main/resources/logs/"+ fileName);
        Scanner myReader = new Scanner(myObj);
        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            String[] splitValues = data.split("\\|");
            UserAccessLog userAccessLog = new UserAccessLog();
            userAccessLog.setDate(MyUtils.dateOfTypeStringToDate(splitValues[0], "yyyy-MM-dd HH:mm:ss"));
            userAccessLog.setIp(splitValues[1]);
            userAccessLog.setUserAgent(splitValues[4]);
            userAccessLog.setStatus(splitValues[3]);
            userAccessLogList.add(userAccessLog);
        }
        myReader.close();
        return userAccessLogList;
    }
}
