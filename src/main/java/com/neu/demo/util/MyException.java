package com.neu.demo.util;

public class MyException extends RuntimeException{
    public MyException(){

    }
    public MyException(String message){
        super(message);
    }
}