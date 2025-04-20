package com.project.weedapi.controller;

import com.google.common.util.concurrent.RateLimiter;
import com.project.weedapi.mapper.GenericStrainMapper;
import com.project.weedapi.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.weedapi.bean.GenericStrain;
import com.project.weedapi.service.GenericStrainService;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

@RestController
public class GenericStrainController {
    @Autowired
    private GenericStrainMapper genericStrainMapper;

    @Autowired
    private GenericStrainService genericStrainService;

    private final RateLimiter rateLimiter = RateLimiter.create(Constants.PERMITS_PER_SECOND, 1, TimeUnit.SECONDS);

    @GetMapping("/find-strain")
    public Map<String, List<GenericStrain>> getStrain(@RequestParam(required = false) String strain,
                                         @RequestParam(required = false) String type,
                                   @RequestParam(required = false) Double rating,
                                   @RequestParam(required = false) List<String> effects,
                                   @RequestParam(required = false) List<String> flavours
    ) {
        boolean tryAcquire = rateLimiter.tryAcquire(50, TimeUnit.MILLISECONDS);
        List<GenericStrain> threadErrorResult = new ArrayList<>();
        if (!tryAcquire) {
            threadErrorResult.add(new GenericStrain(Constants.SERVER_BUSY));
            return Map.of("query", threadErrorResult);
        }

        try {
            CompletableFuture<List<GenericStrain>> queryResult = genericStrainService.getStrainService(strain, type, rating, effects, flavours);
            return Map.of("query", queryResult.get());
        } catch (ExecutionException | InterruptedException e) {
            threadErrorResult.add(new GenericStrain(Constants.THREAD_ERROR));
            return Map.of("query", threadErrorResult);
        }
    }

    @GetMapping("/metrics")
    public Map<String, List<String>> getEffectsList(@RequestParam(required = true) String type) {
        boolean tryAcquire = rateLimiter.tryAcquire(50, TimeUnit.MILLISECONDS);
        List<String> threadErrorResult = new ArrayList<>();
        if (!tryAcquire) {
            threadErrorResult.add(Constants.SERVER_BUSY);
            return Map.of("query", threadErrorResult);
        }

        if (type.equalsIgnoreCase("effects")) {
            return Map.of("query", Optional.of(genericStrainService.getEffectListService()).orElse(new ArrayList<>()));
        } else {
            return Map.of("query", Optional.of(genericStrainService.getFlavoursListService()).orElse(new ArrayList<>()));
        }
    }

    @GetMapping("/random-strain")
    public Map<String, GenericStrain> getRandomStrain() {
        boolean tryAcquire = rateLimiter.tryAcquire(50, TimeUnit.MILLISECONDS);
        GenericStrain threadErrorResult = new GenericStrain(Constants.SERVER_BUSY);
        if (!tryAcquire) {
            return Map.of("query", threadErrorResult);
        }

        return Map.of("query", Optional.of(genericStrainService.getRandomStrain()).orElse(new GenericStrain()));
    }
}
