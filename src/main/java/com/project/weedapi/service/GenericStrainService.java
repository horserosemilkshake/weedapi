package com.project.weedapi.service;

import com.project.weedapi.bean.GenericStrain;
import com.project.weedapi.mapper.GenericStrainMapper;
import com.project.weedapi.utils.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.regex.Pattern;


@Service
public class GenericStrainService {
    @Autowired
    private GenericStrainMapper genericStrainMapper;
    private final Logger logger = LoggerFactory.getLogger(GenericStrainService.class);

    @Async("taskExecutor")
    public CompletableFuture<List<GenericStrain>> getStrainService(String strain,
                                                                   String type,
                                                                   Double rating,
                                                                   List<String> effects,
                                                                   List<String> flavours) {

        List<GenericStrain> res = Collections.synchronizedList(new ArrayList<>());

        try {
            if (strain == null && type == null && rating == null && effects == null && flavours == null) {
                res.add(new GenericStrain(Constants.NO_PARAMETER));
                return CompletableFuture.completedFuture(res);
            }

            if (strain != null && !Pattern.matches(Constants.SQL_INJECTION_PATTERN, strain)) {
                res.add(new GenericStrain(Constants.INVALID_STRAIN_NAME));
                return CompletableFuture.completedFuture(res);
            }

            if (type != null && !type.equals(Constants.HYBRID) && !type.equals(Constants.SATIVA) && !type.equals(Constants.INDICA)) {
                res.add(new GenericStrain(Constants.INVALID_TYPE));
                return CompletableFuture.completedFuture(res);
            }

            if (rating != null && (Double.compare(rating, 0.0) <= 0 || Double.compare(rating, 5.0) == 1)) {
                res.add(new GenericStrain(Constants.INVALID_RATING));
                return CompletableFuture.completedFuture(res);
            }

            if (effects != null) {
                List<String> validEffects = genericStrainMapper.getEffectsList();
                boolean allValidEffectInputs = new HashSet<>(validEffects).containsAll(effects);
                if (!allValidEffectInputs) {
                    res.add(new GenericStrain(Constants.INVALID_EFFECTS, "", -1.0, validEffects.toArray(new String[0]), new String[]{}, ""));
                    return CompletableFuture.completedFuture(res);
                }
            }

            if (flavours != null) {
                List<String> validFlavours = genericStrainMapper.getFlavoursList();
                boolean allValidFlavours = new HashSet<>(validFlavours).containsAll(flavours);
                if (!allValidFlavours) {
                    res.add(new GenericStrain(Constants.INVALID_FLAVOURS, "", -1.0, new String[]{}, validFlavours.toArray(new String[0]),""));
                    return CompletableFuture.completedFuture(res);
                }
            }

            res = genericStrainMapper.findStrain(new GenericStrain(strain, type, rating, effects == null ? null : effects.toArray(new String[0]), flavours == null ? null : flavours.toArray(new String[0]), ""));
        } catch (Exception e) {
            logger.debug(e.toString());
            Thread.currentThread().interrupt();
        }
        return CompletableFuture.completedFuture(res);
    }

    public List<String> getEffectListService() {
        return genericStrainMapper.getEffectsList();
    }

    public List<String> getFlavoursListService() {
        return genericStrainMapper.getFlavoursList();
    }

    public GenericStrain getRandomStrain() {
        return genericStrainMapper.getRandomStrain();
    }
}
