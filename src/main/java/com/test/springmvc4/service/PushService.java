package com.test.springmvc4.service;


import org.springframework.stereotype.Service;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.context.request.async.DeferredResult;

/**
 * Created by lauearo on 28/04/2017.
 */
@Service
public class PushService {
    private DeferredResult<String> deferredResult;  //deferredResult obj

    public DeferredResult<String> getAsyncUpdate() {
        deferredResult = new DeferredResult<String>();
        return deferredResult;
    }

    @Scheduled(fixedDelay = 5000)   //schedule to update deferredResult, Async thread
    public void refresh() {
        if (deferredResult != null) {
            deferredResult.setResult(((Long) System.currentTimeMillis()).toString());
        }
    }
}
