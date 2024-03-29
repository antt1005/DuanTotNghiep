package com.spring.beebeta.request;


import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
public class VoucherRequest {
    @NotBlank(message = "Không được bỏ trống mã")
    @Size(max = 30,message = "Mã có độ dài nhỏ hơn hoặc bằng 30 kí tự")
    private String Code;
    @NotBlank(message = "Không được bỏ trống tên khuyến mãi")
    @Size(max = 30,message = "Tên có độ dài nhỏ hơn hoặc bằng 30 kí tự")
    private String Name;
    private Boolean TypeVoucher;
    private Boolean IsVoucher;
//    @NotNull(message = "Khuyến mãi theo % không được bỏ trống !")
//    @Min(value = 1, message = "Khuyến mãi theo % phải là số nguyên dương !")
//    @Max(value = 100, message = "Khuyến mãi theo % nhỏ hơn hoặc bằng 99")
    private Integer Discount;
//     @NotNull(message = "Khuyến mãi theo tiền không được bỏ trống !")
//     @Min(value = 1, message = "Khuyến mãi theo tiền phải là số nguyên dương !")

    private BigDecimal Cash;
    @NotNull(message = "Không được bỏ trống ngày bắt đầu ")
    private Date StartDate;
    @NotNull(message = "Không được bỏ trống ngày kết thúc ")
    private Date EndDate;
//    @NotNull(message = "Hóa đơn áp dụng không được bỏ trống !")
//    @Min(value = 1, message = "Hóa đơn áp dụng phải là số nguyên dương !")
    private Integer Minimum;



}
