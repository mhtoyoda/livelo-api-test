package com.toyoda.livelo.handler;

import com.toyoda.livelo.dto.error.ErroDTO;
import com.toyoda.livelo.exception.ResourceArgumentInvalidException;
import com.toyoda.livelo.exception.ResourceDuplicationException;
import com.toyoda.livelo.exception.ResourceErrorException;
import com.toyoda.livelo.exception.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
@Slf4j
public class ExceptionStatusHandler {

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler({ResourceErrorException.class})
    public ResponseEntity<String> handleRunTimeException(ResourceErrorException exception) {
        return error(HttpStatus.INTERNAL_SERVER_ERROR, exception);
    }

    @ExceptionHandler({ResourceNotFoundException.class})
    public ResponseEntity<String> handleNotFoundException(ResourceNotFoundException exception) {
        return error(HttpStatus.NOT_FOUND, exception);
    }

    @ExceptionHandler({ResourceArgumentInvalidException.class})
    public ResponseEntity<String> handleArgumentInvalidException(ResourceArgumentInvalidException exception) {
        return error(HttpStatus.BAD_REQUEST, exception);
    }

    @ExceptionHandler({ResourceDuplicationException.class})
    public ResponseEntity<String> handleDuplicationException(ResourceDuplicationException exception) {
        return error(HttpStatus.CONFLICT, exception);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<List<ErroDTO>> handleArgumentNotValidException(MethodArgumentNotValidException exception) {
        List<ErroDTO> erros = new ArrayList<>();
        exception.getBindingResult().getFieldErrors().forEach(fieldError -> {
            String message = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
            ErroDTO erroDTO = new ErroDTO(fieldError.getField(), message);
            erros.add(erroDTO);
        });

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erros);
    }

    private ResponseEntity<String> error(HttpStatus status, Exception e) {
        log.error("Exception : ", e);
        return ResponseEntity.status(status).body(e.getMessage());
    }
}
