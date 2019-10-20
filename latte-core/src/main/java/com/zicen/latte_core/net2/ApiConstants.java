package com.zicen.latte_core.net2;


public class ApiConstants {
    public static final String APP_HOST = "https://app.missevan.com/";
    public static final String FM_API_HOST = "https://fm.missevan.com/api/";

    public static String getHost(int hostType) {
        String host;
        switch (hostType) {
            case HostType.TYPE_HOST_APP:
                host = APP_HOST;
                break;
            case HostType.TYPE_FM:
                host = FM_API_HOST;
                break;
            default:
                host = "";
                break;
        }
        return host;
    }


}
