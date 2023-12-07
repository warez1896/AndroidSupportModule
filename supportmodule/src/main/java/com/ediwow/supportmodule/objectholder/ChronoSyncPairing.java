package com.ediwow.supportmodule.objectholder;

import android.os.SystemClock;

import androidx.annotation.NonNull;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

public class ChronoSyncPairing {
    private final Date serverTimestamp;
    private final BigDecimal cpuUptime;

    public ChronoSyncPairing(@NonNull Date serverTimestamp, @NonNull BigDecimal cpuUptime) {
        this.serverTimestamp = serverTimestamp;
        this.cpuUptime = cpuUptime;
    }

    public Calendar getServerTime() {
        //insert server time into Calendar object
        // Get current CPU uptime in milliseconds, divided by 1k to become seconds
        // Determine delta in seconds between current and stamped CPU uptime
        // Add delta to stamped server time
        Calendar cal = Calendar.getInstance();
        cal.setTime(serverTimestamp);
        BigDecimal currentUptime = new BigDecimal(SystemClock.elapsedRealtime() / 1000);
        BigDecimal difference = currentUptime.subtract(cpuUptime);
        cal.add(Calendar.SECOND, difference.intValue());
        return cal;
    }
}
