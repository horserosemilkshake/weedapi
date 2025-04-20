package com.project.weedapi.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.project.weedapi.bean.GenericStrain;

import java.util.List;

@Mapper
public interface GenericStrainMapper {
    public GenericStrain getRandomStrain();

    public List<GenericStrain> findStrain(GenericStrain genericStrain);

    public List<String> getEffectsList();

    public List<String> getFlavoursList();
}

