package com.hyd.mybatisdemo.controller;

import com.hyd.mybatisdemo.service.AdService;
import com.hyd.mybatisdemo.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

@RestController
@RequestMapping
public class AdController {

    @Autowired
    private AdService adService;

    @GetMapping("/getad")
    public Object getad() {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        Map<String, Object> data = new HashMap<>();
        Callable<List> bannerListCallable = () -> adService.queryIndex();
        FutureTask<List> bannerTask = new FutureTask<>(bannerListCallable);
        executorService.submit(bannerTask);
        try {
            data.put("banner", bannerTask.get());
        } catch (Exception e) {
            e.printStackTrace();
        }
        executorService.shutdown();
        return ResponseUtil.ok(data);
    }

}
