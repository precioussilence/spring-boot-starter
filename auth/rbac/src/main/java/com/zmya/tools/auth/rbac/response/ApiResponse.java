package com.zmya.tools.auth.rbac.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse<T> {

    private int status;
    private String message;
    private T data;
//    private LocalDateTime timestamp;

    public static <T> ApiResponse<T> success(T data, String message) {
        return new ApiResponse<>(
                HttpStatus.OK.value(),
                message,
                data
        );
    }

    public static <T> ApiResponse<T> error(int status, String message) {
        return new ApiResponse<>(
                status,
                message,
                null
        );
    }

    public static <T> ApiResponse<T> error(HttpStatus status) {
        return new ApiResponse<>(status.value(), status.getReasonPhrase(), null);
    }
}

