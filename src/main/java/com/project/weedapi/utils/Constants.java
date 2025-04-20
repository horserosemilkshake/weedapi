package com.project.weedapi.utils;

import com.project.weedapi.bean.GenericStrain;

import java.util.List;

public final class Constants {
    public static final String SQL_INJECTION_PATTERN = "^[a-zA-Z0-9.-]{0,19}$";
    public static final String SERVER_BUSY = "Error: server busy, please try later";
    public static final double PERMITS_PER_SECOND = 5000.0;
    public static final String NO_PARAMETER = "Error: at least one parameter (strain, type, rating, effect, or flavours) is needed to do searching";
    public static final String THREAD_ERROR = "Error: multithreading failure";
    public static final String HYBRID = "hybrid";
    public static final String SATIVA = "sativa";
    public static final String INDICA = "indica";
    public static final String INVALID_RATING = "Error: rating must be (0.0,5.0].";
    public static final String INVALID_EFFECTS = "Error: invalid effect(s). See below for a list of valid options.";
    public static final String INVALID_FLAVOURS = "Error: invalid effect(s). See below for a list of valid options.";
    public static final String INVALID_STRAIN_NAME = "Error: invalid strain name";
    public static final String INVALID_TYPE = "Error: invalid type name. Only hybrid, sativa, or indica supported.";

}
