package com.spring.beebeta.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateBillStatus {
    private String Code;
    private Integer Status;
}
