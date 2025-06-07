
/**
 * Base Response
 * Copyright (C) 2025
 * Created by hanxu on 2025/05/24.
 */
package com.example.flightapi.common;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Base Response
 */
@Data
@AllArgsConstructor
public class BaseResponse<T> {

    private boolean success;
    private int code;
    private String message;
    private T data;
    

    /**
     * Create a success response with data
     * @param <T>
     * @param data
     * @return
     */
    public static <T> BaseResponse<T> success(T data) {
        return new BaseResponse<>(true, 200, "OK", data);
    }
    
    /**
     * Create a success response without data
     * @param <T>
     * @return
     */
    public static BaseResponse<Void> success() {
        return new BaseResponse<>(true, 200, "OK", null);
    }
    
    /**
     * Create an error response with code and message
     * @param <T>
     * @param code
     * @param message
     * @return
     */
    public static <T> BaseResponse<T> error(int code, String message) {
        return new BaseResponse<>(false, code, message, null);
    }

    /**
     * Create an error response with code, message and data
     * @param <T>
     * @param code
     * @param message
     * @param data
     * @return
     */
    public static <T> BaseResponse<T> error(int code, String message, T data) {
        return new BaseResponse<>(false, code, message, data);
    }
}
