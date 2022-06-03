//package com.example.test2.service;
//
//import com.example.test2.dao.UserAccessLogDao;
//import com.example.test2.entity.UserAccessLog;
//import com.example.test2.projection.UserAccessProjection;
//import com.example.test2.service.user_access_log.UserAccessLogServiceImpl;
//import com.example.test2.utils.DateIncrementEnum;
//import com.example.test2.utils.MyUtils;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mock;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.io.FileNotFoundException;
//import java.text.ParseException;
//import java.time.LocalDateTime;
//import java.util.Date;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//public class UserAccessLogServiceImplTest {
//
//    private UserAccessLogServiceImpl userAccessLogServiceImpl;
//
//    @Mock
//    private UserAccessLogDao userAccessLogDao;
//
//    @BeforeEach
//    void setUp() throws FileNotFoundException, ParseException {
//        String fileName = "logs/user_access.txt";
//        this.userAccessLogServiceImpl = new UserAccessLogServiceImpl(userAccessLogDao);
//        if(this.userAccessLogServiceImpl.getTotalCount() <= 0L) {
//            List<UserAccessLog> userAccessLogList = this.userAccessLogServiceImpl.setUpData(fileName);
//            this.userAccessLogServiceImpl.bulk(userAccessLogList);
//        }
//    }
//
//    @AfterEach
//    void tearDown() {
//    }
//
//    @DisplayName("findAllHavingMoreThanHundredWithDateRange")
//    @Test
//    void findAllHavingMoreThanHundredWithDateRange() throws ParseException {
//
//        String startDateAsString = "2022-01-01 13:00:00";
//
//        DateIncrementEnum dateIncrementEnum = DateIncrementEnum.HOURLY;
//
//        long range = 200L;
//
//        Date startDate = MyUtils.dateOfTypeStringToDate(startDateAsString, "yyyy-MM-dd HH:mm:ss");
//
//        LocalDateTime localDateTime = MyUtils.convertDateToLocalDateTime(startDate);
//
//        localDateTime = MyUtils.increment(localDateTime, dateIncrementEnum, 1);
//
//        Date endDate = MyUtils.convertLocalDateTimeToDate(localDateTime);
//
//        List<UserAccessProjection> list = userAccessLogServiceImpl.findAllHavingMoreThanHundredWithDateRange(startDate, endDate, range);
//
//        assertEquals(list.size(), 2);
//    }
//}
