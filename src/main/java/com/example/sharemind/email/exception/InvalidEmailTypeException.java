package com.example.sharemind.email.exception;

import com.example.sharemind.email.application.content.EmailTypes;

public class InvalidEmailTypeException extends RuntimeException{
    public InvalidEmailTypeException(EmailTypes type){super("해당 EmailType이 존재하지 않습니다. : "+ type);}
}
