package com.example.hackathon.exception;

import com.example.hackathon.response.Code;
import com.example.hackathon.response.ReasonDTO;
import lombok.Getter;

@Getter
public class GlobalException extends RuntimeException {

    private final Code code;

    public GlobalException(Code code) {
        super(code.getMessage());
        this.code = code;
    }

    public ReasonDTO getReason() {
        return this.code.getReason();
    }
}
