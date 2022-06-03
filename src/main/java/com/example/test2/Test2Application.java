package com.example.test2;

import com.example.test2.entity.BlockedIPTable;
import com.example.test2.entity.UserAccessLog;
import com.example.test2.projection.UserAccessProjection;
import com.example.test2.service.blocked_ip.BlockedIPService;
import com.example.test2.service.user_access_log.UserAccessLogService;
import com.example.test2.utils.DateIncrementEnum;
import com.example.test2.utils.MyUtils;
import com.google.gson.Gson;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

@SpringBootApplication
public class Test2Application {

    private final Logger logger = Logger.getLogger(Test2Application.class.getName());

    public final UserAccessLogService userAccessLogService;

    public final BlockedIPService blockedIPService;

    public final Gson gson;

    public Test2Application(UserAccessLogService userAccessLogService,
                            BlockedIPService blockedIPService) {
        this.userAccessLogService = userAccessLogService;
        this.blockedIPService = blockedIPService;
        this.gson = new Gson();
    }

    public static void main(String[] args) {
        SpringApplication.run(Test2Application.class, args);
    }

    @PostConstruct
    public void initRun() {
        try {

            String fileName = "logs/user_access.txt";
            String startDateAsString = "2022-01-01 13:00:00";
            DateIncrementEnum dateIncrementEnum = DateIncrementEnum.HOURLY;

            if(this.userAccessLogService.getTotalCount() <= 0L) {

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
                this.userAccessLogService.bulk(userAccessLogList);

            } else {
                this.logger.info("Already loaded!");
            }


//            String endDateAsString = "2022-01-01 14:00:00";

            Date startDate = MyUtils.dateOfTypeStringToDate(startDateAsString, "yyyy-MM-dd HH:mm:ss");

            LocalDateTime localDateTime = MyUtils.convertDateToLocalDateTime(startDate);

            localDateTime = MyUtils.increment(localDateTime, dateIncrementEnum, 1);

            Date endDate = MyUtils.convertLocalDateTimeToDate(localDateTime);

            List<UserAccessProjection> logList = this.userAccessLogService.findAllHavingMoreThanHundredWithDateRange(startDate, endDate);

            List<BlockedIPTable> blockedIPTableList = new ArrayList<>();

            for(UserAccessProjection userAccessLog: logList) {
                BlockedIPTable blockedIPTable = new BlockedIPTable();
                blockedIPTable.setComment("Because of too many request total count is: "+ userAccessLog.getCount());
                blockedIPTable.setRequestNumber(String.valueOf(userAccessLog.getCount()));
                blockedIPTable.setIp(userAccessLog.getIp());
                blockedIPTableList.add(blockedIPTable);
                logger.info(this.gson.toJson(blockedIPTable));
            }

            this.blockedIPService.bulkSave(blockedIPTableList);

        } catch (FileNotFoundException | ParseException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

}
