package com.lucasit.withdraw.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lucasit.withdraw.exception.BusinessException;
import com.lucasit.withdraw.exception.DataBaseException;
import com.lucasit.withdraw.exception.ExternalException;
import com.lucasit.withdraw.model.Transactions;
import com.lucasit.withdraw.repository.TransactionRepository;
import com.lucasit.withdraw.request.external.ExternalError;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
@RequiredArgsConstructor
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    private final TransactionRepository transactionRepository;


    @ExceptionHandler(value = {BusinessException.class})
    public ResponseEntity<ErrorMessage> handlerResourceNotFoundException(BusinessException e) {

        return new ResponseEntity<>(ErrorMessage.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.NOT_FOUND.value())
                .title(e.getMessage())
                .message(e.getMessage())
                .build(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {DataBaseException.class})
    public ResponseEntity<ErrorMessage> handlerDataBaseException(DataBaseException e) {

        return new ResponseEntity<>(ErrorMessage.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.NOT_FOUND.value())
                .title("Resource not founded.")
                .message(e.getMessage())
                .build(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {ExternalException.class})
    public ResponseEntity<ErrorMessage> handlerExternalException(ExternalException e) throws JsonProcessingException {
        ExternalError externalError = new ObjectMapper().readValue(e.getMessage(), ExternalError.class);
        Transactions transactions = e.getTransactions();
        transactionRepository.save(transactions);
        return new ResponseEntity<>(ErrorMessage.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .title("External Http Request Error.")
                .message(externalError.getMessage())
                .build(), HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        List<FieldError> fieldErros = ex.getBindingResult().getFieldErrors();

        String fields = fieldErros.stream().map(FieldError::getField).collect(Collectors.joining(", "));
        String messages = fieldErros.stream().map(FieldError::getDefaultMessage).collect(Collectors.joining(", "));

        return new ResponseEntity<>(ValidationExceptionDetails.builder()
                .fields(fields)
                .message("Check the fields error.")
                .fieldsMessage(messages)
                .timestamp(LocalDateTime.now())
                .status(status.value())
                .title("Not Found")
                .build(), HttpStatus.BAD_REQUEST);
    }


    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatusCode statusCode, WebRequest request) {
        ErrorMessage errorMessage = ErrorMessage.builder()
                .timestamp(LocalDateTime.now())
                .status(statusCode.value())
                .title(ex.getCause().getMessage())
                .message(ex.getMessage())
                .build();

        return new ResponseEntity(errorMessage, headers, statusCode);
    }


}
