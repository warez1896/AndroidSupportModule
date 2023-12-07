package com.ediwow.supportmodule.backend;

import androidx.annotation.NonNull;

import org.json.JSONObject;

import java.math.BigDecimal;

public class DecimalConverter {
    public static BigDecimal toBigDecimal(String sValue) {
        return (sValue != null && sValue != JSONObject.NULL && !sValue.equals("null")) ? new BigDecimal(sValue) : null;
    }

    public String toString(BigDecimal bigDecimal) {
        return (bigDecimal != null) ? bigDecimal.toPlainString() : null;
    }
}
