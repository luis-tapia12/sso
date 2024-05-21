package com.jcontrerast.sso.utils;

public class Constants {
    public final static int DEFAULT_PAGE_SIZE = 10;
    public final static int DEFAULT_PAGE_NUMBER = 0;

    public final static String USER_NAME_REGEX = "^(?!.*[._-]{2})(?![0-9])[a-zA-Z0-9]+(?:[._-][a-zA-Z0-9]+)*$";

    public final static String APP_BASE_HOLDER = "{0}/app";
    public final static String APP_INDEX_HOLDER = APP_BASE_HOLDER + "/index.html";
}
