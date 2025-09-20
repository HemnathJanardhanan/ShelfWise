package com.shelfwise.backend.common.utils.scheduler;


import com.shelfwise.backend.modules.fine.service.FineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class FineScheduler {

    @Autowired
    FineService fineService;

    @Scheduled(cron="0 0 0 * * ?")
    public void checkAndApplyFine(){
        fineService.processOverDueTransactions();
    }

}
