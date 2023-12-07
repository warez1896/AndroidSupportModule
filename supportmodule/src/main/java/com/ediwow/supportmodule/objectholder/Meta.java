package com.ediwow.supportmodule.objectholder;

import androidx.annotation.Nullable;

public class Meta {
    public static final String PROTOCOL_HTTP = "http";
    public static final String PROTOCOL_HTTPS = "https";
    public static boolean isProduction = false;
    @Nullable
    private static User user = null;
    private static ChronoSyncPairing chronoSyncPairing = null;

    public static void setUser(User user) {
        Meta.user = user;
    }

    public static User getUser() {
        return user;
    }

    public static boolean isIsProduction() {
        return isProduction;
    }

    public static void setIsProduction(boolean isProduction) {
        Meta.isProduction = isProduction;
    }

    public static ChronoSyncPairing getChronoSyncPairing() {
        return chronoSyncPairing;
    }

    public static void setChronoSyncPairing(ChronoSyncPairing chronoSyncPairing) {
        Meta.chronoSyncPairing = chronoSyncPairing;
    }
}
