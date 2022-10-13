package com.sorcerer_king.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DecimalFormat;

public class Globals {
    public static final String MOD_ID = "sorcerer-king";
    public static final boolean DEBUG = true;
    public static final Logger LOGGER = LoggerFactory.getLogger(Globals.MOD_ID);
    public static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("0.00");
}