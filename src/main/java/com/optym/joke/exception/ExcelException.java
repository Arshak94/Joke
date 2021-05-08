package com.optym.joke.exception;

public class ExcelException extends RuntimeException{
    public ExcelException() {
        super("Can't write to excel file");
    }
}
