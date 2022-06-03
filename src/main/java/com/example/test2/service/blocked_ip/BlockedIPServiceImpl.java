package com.example.test2.service.blocked_ip;

import com.example.test2.dao.BlockedIPTableDao;
import com.example.test2.entity.BlockedIPTable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BlockedIPServiceImpl implements BlockedIPService {

    private final BlockedIPTableDao blockedIPTableDao;

    public BlockedIPServiceImpl(BlockedIPTableDao blockedIPTableDao) {
        this.blockedIPTableDao = blockedIPTableDao;
    }

    @Transactional
    public void save(BlockedIPTable blockedIPTable) {
        this.blockedIPTableDao.save(blockedIPTable);
    }

    @Transactional
    public void bulkSave(List<BlockedIPTable> blockedIPTableList) {
        for(BlockedIPTable blockedIPTable: blockedIPTableList) {
            this.blockedIPTableDao.save(blockedIPTable);
        }
    }
}
