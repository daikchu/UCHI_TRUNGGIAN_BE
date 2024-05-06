package com.vn.osp.notarialservices.utils.config;

/**
 * Created by Admin on 2018-05-12.
 */
public class Constants {
    public static final int NUMBER_PER_ROW		= 20;
    public static class HEADER_AUTH_BASIC_VIETQR{
        public static final String USERNAME = "customer-bl-user05";
        public static final String PASSWORD = "Y3VzdG9tZXItYmwtdXNlcjA1";
    }
    public static final String URL_GET_TOKEN = "http://112.78.1.220:8084/vqr/api/token_generate";
    public static final String URL_CREATE_VIETQR_CODE = "http://112.78.1.220:8084/vqr/api/qr/generate-customer";
    public static final String URL_CHECK_ORDER = "http://112.78.1.220:8084/vqr/api/transactions/check-order";
    public static final String REGEX = ":";
    //token set thời hạn 5 phút
    public static final long EXPIRES_IN = 300000;
    public static final String SECRET_KEY = "uchi_osp";

    public static class ERROR_CODE_INPUT {
        public static final String E24 = "E24";  // Không tìm thấy ngân hàng tương ứng với bankCode (mã ngân hàng)
        public static final String E26 = "E26"; // Nội dung không hợp lệ (dài hơn 50 ký tự)
    }
    public static class HEADER_AUTH_BASIC{
        public static final String USERNAME = "uchi_osp";
        public static final String PASSWORD = "Uchi@123";
    }
    public enum DataBaseName {
        CITIZEN_INFO
        , CITIZEN_VERIFY_NUMBER_MANAGEMENT
        , CITIZEN_VERIFY_ORDERS
        , CITIZEN_VERIFICATIONS
        , CITIZEN_VERIFY_PACKAGE
        ;

        public String getValue() {
            switch (this) {
                case CITIZEN_INFO:
                    return "npo_citizen_info";
                case CITIZEN_VERIFY_NUMBER_MANAGEMENT:
                    return "npo_citizen_verification_number";
                case CITIZEN_VERIFY_ORDERS:
                    return "npo_citizen_verification_orders";
                case CITIZEN_VERIFICATIONS:
                    return "npo_citizen_verifications";
                case CITIZEN_VERIFY_PACKAGE:
                    return "npo_citizen_verify_packages";
                default:
                    return null;
            }
        }
    }
    public static class USER_NAME_UPDATE {
        public static final String SYSTEM = "Hệ thống";
        public static final String SYSTEM_ACCOUNT = "system";
    }
    public enum ERROR_CODE_PAYMENT {
        SUCCESS("000","Thành công"),
        ERROR_CODE_E46("E46", "Request Body không hợp lệ"),
        ERROR_CODE_E74("E74","Token không hợp lệ"),
        ERROR_CODE_E75("E75","Dịch vụ test callback API không khả dụng"),
        ERROR_CODE_E76("E76","Khách hàng chưa được đăng ký trong hệ thống"),
        ERROR_CODE_E77("E77","TK ngân hàng không khớp với thông tin của khách hàng"),
        ERROR_CODE_002("002","Số tiền không hợp lệ"),
        ERROR_CODE_003("003","transType không hợp lệ"),
        ERROR_CODE_004("004","Thời gian ghi nhận giao dịch không hợp lệ"),
        ERROR_CODE_007("007","ID của giao dịch không hợp lệ"),
        ERROR_CODE_008("008","Mã reference number của ngân hàng trả về không hợp lệ"),
        ERROR_CODE_009("009","Nội dung không hợp lệ"),
        ERROR_CODE_010("010","Số tài khoản không hợp lệ"),
        ERROR_CODE_E05("E05","Lỗi không xác định (Mã lỗi này thường trả về kèm theo mô tả lỗi)");

        ERROR_CODE_PAYMENT(String errorCode, String message) {
            this.errorCode = errorCode;
            this.message = message;
        }
        private String errorCode;
        private String message;

        public String getErrorCode() {
            return errorCode;
        }

        public void setErrorCode(String errorCode) {
            this.errorCode = errorCode;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }

    public enum Status_PaymentTransaction {
        FAIL
        , SUCCESS
        , PAY_NOT_ENOUGH
        , PAY_OVER
        , PENDING
        ;
        public String getValue() {
            switch (this) {
                case SUCCESS:
                    return "success";
                case FAIL:
                    return "fail";
                case PAY_NOT_ENOUGH:
                    return "payment_not_enough";
                case PAY_OVER:
                    return "payment_over";
                case PENDING:
                    return "pending";
                default:
                    return null;
            }
        }
    }

    public enum StatusAddition_PaymentTransaction {
        FAIL
        , SUCCESS
        , PAY_NOT_ENOUGH
        , PAY_OVER
        , PENDING
        , WRONG_CONTENT
        , OVER_WRONG_CONTENT
        , PAYMENT_NOT_ENOUGH_WRONG_CONTENT
        ;
        public String getValue() {
            switch (this) {
                case SUCCESS:
                    return "success";
                case FAIL:
                    return "fail";
                case PAY_NOT_ENOUGH:
                    return "payment_not_enough";
                case PAY_OVER:
                    return "payment_over";
                case PENDING:
                    return "pending";
                case WRONG_CONTENT:
                    return "wrong_content";
                case OVER_WRONG_CONTENT:
                    return "over_wrong_content";
                case PAYMENT_NOT_ENOUGH_WRONG_CONTENT:
                    return "payment_not_enough_wrong_content";
                default:
                    return null;
            }
        }
    }
    // khai bao ma loi khong xac dinh
    public enum ERROR_CODE_E05_PAYMENT{
        ERROR_CODE_E0501("E0501", "Mã hóa đơn không hợp lệ");

        ERROR_CODE_E05_PAYMENT(String errorCode, String message) {
            this.errorCode = errorCode;
            this.message = message;
        }
        private String errorCode;
        private String message;

        public String getErrorCode() {
            return errorCode;
        }

        public void setErrorCode(String errorCode) {
            this.errorCode = errorCode;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }

    public enum STATUS_VERIFY_CITIZEN {
        FAIL
        , SUCCESS
        ;
        public String getValue() {
            switch (this) {
                case SUCCESS:
                    return "success";
                case FAIL:
                    return "fail";
                default:
                    return null;
            }
        }
    }

    public enum ATTACH_FILE_CODE {
        PAYMENT_TRANSACTION
        ;
        public String getValue() {
            switch (this) {
                case PAYMENT_TRANSACTION:
                    return "PAYMENT_TRANSACTION";
                default:
                    return null;
            }
        }
    }

    public enum NOTIFICATION_TYPE {
        CITIZEN_VERIFY_ORDER
        , COMMON
        ;
        public String getValue() {
            switch (this) {
                case CITIZEN_VERIFY_ORDER:
                    return "CITIZEN_VERIFY_ORDER";
                default:
                    return "COMMON";
            }
        }
    }

}
