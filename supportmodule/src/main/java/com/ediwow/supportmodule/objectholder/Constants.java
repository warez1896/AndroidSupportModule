package com.ediwow.supportmodule.objectholder;

public class Constants {

    public static class RequestResponse {
        public static final int RES_OK = 0;
        public static final int RES_TIMEOUT = -1;
        public static final int RES_ERR_EXCEPTION = -2;
        public static final int RES_BLANK = -3;

        private RequestResponse() {
        }
    }

    public static class Android {
        public static final int REQ_PERMISSIONS = 100;

        private Android() {

        }
    }
}
