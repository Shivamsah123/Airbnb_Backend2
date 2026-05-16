package com.airbnb.server.dto;

import lombok.Data;

@Data
public class ApiResponse {
    private String status;
    private Object data;
    private Object error;

    public static ApiResponse success(Object data) {
        ApiResponse response = new ApiResponse();
        response.setStatus("success");
        response.setData(data);
        return response;
    }

    public static ApiResponse error(Object error) {
        ApiResponse response = new ApiResponse();
        response.setStatus("error");
        response.setError(error);
        return response;
    }
}
