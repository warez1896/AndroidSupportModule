package com.ediwow.supportmodule.objectholder;

public class Meta {
    public static final String PROTOCOL_HTTP = "http";
    public static final String PROTOCOL_HTTPS = "https";
    public static boolean isProduction = false;
    private static User user = null;

    public static void setUser(User user) {
        Meta.user = user;
    }

    public static User getUser() {
        return user;
    }
}
