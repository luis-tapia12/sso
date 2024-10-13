package com.jcontrerast.sso.utils;

public class Constants {
    public static final String REGEX_NAME = "^(?! )[A-Za-záéíóúÁÉÍÓÚñÑ]+( [A-Za-záéíóúÁÉÍÓÚñÑ]+)*(?<! )$";
    public static final String REGEX_USER_NAME = "^(?!.*\\.\\.)(?!.*__)[a-zA-Z][a-zA-Z0-9._]*[a-zA-Z0-9]$";

    public final static String APP_BASE_HOLDER = "{0}/app";
    public final static String APP_INDEX_HOLDER = APP_BASE_HOLDER + "/index.html";
    public final static String AUTH_BASE_HOLDER = "{0}/auth";
    public final static String AUTH_INDEX_HOLDER = AUTH_BASE_HOLDER + "/index.html";
}
