package hui.a7ifun.com.a7ifun.smscheck.bean;

/**
 * Created by 7Yan on 2017/1/19.
 */

public class AlidayuBean {
    /**
     * alibaba_aliqin_fc_sms_num_send_response : {"result":{"err_code":"0","model":"105730306040^1107775087847","success":true},"request_id":"12lp4otrq5z0e"}
     */

    private AlibabaAliqinFcSmsNumSendResponseBean alibaba_aliqin_fc_sms_num_send_response;

    public AlibabaAliqinFcSmsNumSendResponseBean getAlibaba_aliqin_fc_sms_num_send_response() {
        return alibaba_aliqin_fc_sms_num_send_response;
    }

    public void setAlibaba_aliqin_fc_sms_num_send_response(AlibabaAliqinFcSmsNumSendResponseBean alibaba_aliqin_fc_sms_num_send_response) {
        this.alibaba_aliqin_fc_sms_num_send_response = alibaba_aliqin_fc_sms_num_send_response;
    }

    public static class AlibabaAliqinFcSmsNumSendResponseBean {
        /**
         * result : {"err_code":"0","model":"105730306040^1107775087847","success":true}
         * request_id : 12lp4otrq5z0e
         */

        private ResultBean result;
        private String request_id;

        public ResultBean getResult() {
            return result;
        }

        public void setResult(ResultBean result) {
            this.result = result;
        }

        public String getRequest_id() {
            return request_id;
        }

        public void setRequest_id(String request_id) {
            this.request_id = request_id;
        }

        public static class ResultBean {
            /**
             * err_code : 0
             * model : 105730306040^1107775087847
             * success : true
             */

            private String err_code;
            private String model;
            private boolean success;

            public String getErr_code() {
                return err_code;
            }

            public void setErr_code(String err_code) {
                this.err_code = err_code;
            }

            public String getModel() {
                return model;
            }

            public void setModel(String model) {
                this.model = model;
            }

            public boolean isSuccess() {
                return success;
            }
            public boolean getSuccess() {
                return success;
            }

            public void setSuccess(boolean success) {
                this.success = success;
            }
        }
    }
}
