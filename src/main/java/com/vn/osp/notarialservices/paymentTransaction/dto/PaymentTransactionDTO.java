package com.vn.osp.notarialservices.paymentTransaction.dto;

import com.vn.osp.notarialservices.file.domain.AttachFiles;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentTransactionDTO {
    private Long id;
    private String transaction_id;
    private String order_id;
    private String transaction_time;
    private String reference_number;
    private String amount;
    private String content;
    private String bank_account;
    private String trans_type;
    private int error;
    private String error_reason;
    private String toast_message;
    private String ref_transaction_id;
    private String status;
    private String status_name;
    private String transaction_status;
    private String transaction_status_name;
    private String note;

    private String notary_office_code;
    private String province_code;
    private String notary_office_name;
    private String province_name;
    private String update_by;
    private String update_by_name;
    private String transaction_time_formatted;
    private String update_user_fullname;

    private List<String> file_names;
    private List<String> file_paths;
    private String files_id_remove;
    private List<AttachFiles> attach_files;

    private String entry_user_name;
    private String update_user_name;
    private int verify_number;
    private String verify_fee;

    private List<PaymentTransactionDTO> transaction_hists;
}
