package com.ediwow.supportmodule.objectholder;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class URLQueryParams {
    private String param, value;

    public URLQueryParams(String param, String value, Boolean urlEnc) throws Exception {
        if (urlEnc) {
            setParam(URLEncoder.encode(param, StandardCharsets.UTF_8.name()));
            setValue(URLEncoder.encode(value, StandardCharsets.UTF_8.name()));
        } else {
            setParam(param);
            setValue(value);
        }
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public static class JoinedParams {
        private String paramSet;

        public JoinedParams(URLQueryParams params) {
            setParamSet(String.format("%s=%s", params.getParam(), params.getValue()));
        }

        public String getParamSet() {
            return paramSet;
        }

        public void setParamSet(String paramSet) {
            this.paramSet = paramSet;
        }

        @Override
        public String toString() {
            return getParamSet();
        }
    }
}
