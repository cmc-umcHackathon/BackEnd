package com.example.hackathon.exception;

import com.example.hackathon.response.Code;
import com.example.hackathon.response.ReasonDTO;
import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException{

    private final Code code;

    public BusinessException(Code code) {
        super(code.getMessage());
        this.code = code;
    }

    public ReasonDTO getReason() {
        return this.code.getReason();
    }
}