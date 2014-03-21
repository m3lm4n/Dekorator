package com.lastowski.dekorator.api;

/**
 * Created by adamastowski on 29.12.2013.
 */
public class ApiResponse<T> {
    private T data;
    private String errorMessage;
    private int errorCode;

    public ApiResponse(T data){
        this.data = data;
        errorCode = 0;
    }

    public ApiResponse(int errorCode, String errorMessage){
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public T getData(){
        return data;
    }

    public int getErrorCode(){
        return errorCode;
    }

    public String getErrorMessage(){
        return errorMessage;
    }
}
