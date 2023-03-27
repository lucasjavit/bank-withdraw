package com.lucasit.withdraw.handler;

import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Data
@SuperBuilder
public class ExceptionDetails {

    protected final String title;
    protected final int status;
    protected final String message;
    protected final LocalDateTime timestamp;
}
