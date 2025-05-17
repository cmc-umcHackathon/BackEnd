package com.example.hackathon.global.exception;

import com.example.hackathon.global.response.Code;
import com.example.hackathon.global.response.ReasonDTO;
import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException{

    private final Code code;

    public BusinessException(Code code) {
        super(code.getMessage());
        this.code = code;
    }

    public BusinessException(Code code, String s) {
        super(code.getMessage());
        this.code = code;
    }

    public ReasonDTO getReason() {
        return this.code.getReason();
    }
}