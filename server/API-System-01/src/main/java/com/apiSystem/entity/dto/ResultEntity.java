package com.apiSystem.entity.dto;

public class ResultEntity<T> {

    public static final String SUCCESS = "200";
    public static final String FAILED = "400";
    public static final String TOKEN_INVALID_CODE = "401";
    public static final String NO_MESSAGE = "NO_MESSAGE";
    public static final String NO_DATA = "NO_DATA";

    // 方便返回成功结果（不携带查询结果情况）
    public static ResultEntity<String> successWithoutData() {
        return new ResultEntity<String>(SUCCESS, NO_MESSAGE, NO_DATA);
    }

    // 方便返回成功结果（携带查询结果情况）
    public static <E> ResultEntity<E> successWithData(E data) {
        return new ResultEntity<E>(SUCCESS, NO_MESSAGE, data);
    }

    // 方便返回失败结果
    public static <E> ResultEntity<E> failed(E data, String message) {
        return new ResultEntity<E>(FAILED, message, data);
    }

    private String result;
    private String message;
    private T data;

    public ResultEntity(String result, String message, T data) {
        super();
        this.result = result;
        this.message = message;
        this.data = data;
    }

    public ResultEntity() {
        super();
        // TODO Auto-generated constructor stub
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResultEntity [result=" + result + ", message=" + message
                + ", data=" + data + "]";
    }

}
