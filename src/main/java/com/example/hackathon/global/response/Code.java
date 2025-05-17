package com.example.hackathon.global.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum Code implements BaseCode {

    // ✅ 성공 응답
    OK(HttpStatus.OK, "COMMON200", "요청이 성공적으로 처리되었습니다."),
    NO_CONTENT(HttpStatus.NO_CONTENT, "COMMON204", "요청은 성공했으나 반환할 내용이 없습니다."),

    // ❌ 공통 에러
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON500", "서버 에러입니다. 관리자에게 문의하세요."),
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "COMMON400", "잘못된 요청입니다."),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "COMMON401", "인증이 필요합니다."),
    FORBIDDEN(HttpStatus.FORBIDDEN, "COMMON403", "접근이 거부되었습니다."),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "COMMON404", "요청한 리소스를 찾을 수 없습니다."),

    POINT_NOT_FOUND(HttpStatus.NOT_FOUND, "COMMON404", "해당 유저는 포인트가 존재하지 않습니다."),
    POINT_NOT_ENOUGH(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON500", "보유한 포인트가 충분하지 않습니다."),
    CATEGORY_NOT_FOUND(HttpStatus.NOT_FOUND, "COMMON404", "유효한 카테고리가 없습니다."),
    PRODUCT_NOT_FOUND(HttpStatus.NOT_FOUND, "COMMON404", "유효한 상품이 없습니다.")
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
