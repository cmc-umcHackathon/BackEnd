package com.example.hackathon.global.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum Code implements BaseCode {

    OK(HttpStatus.OK, "COMMON200", "성공입니다."),

    // Common Error
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON500", "서버 에러입니다. 관리자에게 문의하세요."),
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "COMMON400", "잘못된 요청입니다."),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "COMMON401", "인증이 필요합니다."),
    FORBIDDEN(HttpStatus.FORBIDDEN, "COMMON403", "금지된 요청입니다."),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "COMMON404", "찾을 수 없는 유저입니다."),

    POINT_NOT_FOUND(HttpStatus.NOT_FOUND, "COMMON404", "해당 유저는 포인트가 존재하지 않습니다."),
    POINT_NOT_ENOUGH(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON500", "보유한 포인트가 충분하지 않습니다.")
    ;



    private final HttpStatus status;
    private final String code;
    private final String message;

    @Override
    public ReasonDTO getReason() {
        return ReasonDTO.builder()
                .status(status)
                .code(code)
                .message(message)
                .build();
    }
}
