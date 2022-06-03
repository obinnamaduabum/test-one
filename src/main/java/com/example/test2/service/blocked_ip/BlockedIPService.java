package com.example.test2.service.blocked_ip;

import com.example.test2.entity.BlockedIPTable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface BlockedIPService {

    void save(BlockedIPTable blockedIPTable);

    @Transactional
    void bulkSave(List<BlockedIPTable> blockedIPTableList);
}
