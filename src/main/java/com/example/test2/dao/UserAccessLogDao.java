package com.example.test2.dao;

import com.example.test2.entity.UserAccessLog;
import com.example.test2.projection.UserAccessProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface UserAccessLogDao extends JpaRepository<UserAccessLog, Long> {

    @Query(value = "select count(*) from user_access_log", nativeQuery = true)
    Long getTotalCount();

    @Query(value = "select ual.user_agent as userAgent, ual.status as status, ual.ip as ip, count(ual) from user_access_log ual where ual.date BETWEEN :startDate AND :endDate group by ual.ip, ual.user_agent, ual.status having count(ual) > :numberOfOccurrences", nativeQuery = true)
    List<UserAccessProjection> findAllHavingMoreThanHundredWithDateRange(@Param("startDate") Date startDate,
                                                                         @Param("endDate") Date endDate,
                                                                         @Param("numberOfOccurrences") long range);

}
