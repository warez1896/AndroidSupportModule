package com.ediwow.supportmodule.backend;

public class Encapsulator {
    public static class BigDecimal extends java.math.BigDecimal{
        public BigDecimal(String val) {
            super(val);
        }

    }
}
