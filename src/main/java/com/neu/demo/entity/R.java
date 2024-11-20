package com.neu.demo.entity;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)  // 仅返回非空字段
public class R<T> {
    private Integer code;      // 状态码，200表示成功，其他表示失败
    private String message;    // 返回消息
    private T data;            // 返回的数据（泛型，适应不同的返回类型）
    @Override
    public String toString() {
        return "{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

    // 默认构造函数，状态码为200，表示成功
    public R() {
        this.code = 200;
        this.message = "成功";
    }

    // 构造函数，用于自定义状态码和消息
    public R(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    // 构造函数，用于返回数据
    public R(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    // 静态方法：成功的响应（没有数据）
    public static <T> R<T> ok() {
        return new R<>(200, "成功", null);
    }

    // 静态方法：成功的响应（有数据）
    public static <T> R<T> ok(T data) {
        return new R<>(200, "成功", data);
    }

    // 静态方法：失败的响应
    public static <T> R<T> error(String message) {
        return new R<>(500, message, null);
    }

    // 静态方法：自定义响应
    public static <T> R<T> custom(Integer code, String message, T data) {
        return new R<>(code, message, data);
    }

    // getter 和 setter 方法
    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
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
}
