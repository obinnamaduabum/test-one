package com.example.test2.dao;

import com.example.test2.entity.BlockedIPTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlockedIPTableDao extends JpaRepository<BlockedIPTable, Long> {
}
