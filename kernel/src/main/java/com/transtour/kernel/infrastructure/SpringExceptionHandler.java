package com.transtour.kernel.infrastructure;

import com.transtour.kernel.domain.ResponseError;
import com.transtour.kernel.domain.bus.command.CommandHandlerExecutionError;
import com.transtour.kernel.domain.bus.query.QueryHandlerExecutionError;
import com.transtour.kernel.infrastructure.event.kafka.KafkaException;
import com.transtour.kernel.infrastructure.jackson.ProcessDataException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

public class SpringExceptionHandler {

    @ExceptionHandler({QueryHandlerExecutionError.class, CommandHandlerExecutionError.class, ProcessDataException.class})
    public ResponseEntity<ResponseError> handleQueryHandlerException(Exception ex) {
        return new ResponseEntity<>(
            new ResponseError(
                LocalDateTime.now().toString(),
                String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()),
                HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                ex.getClass().getSimpleName(),
                ex.getMessage()
            ), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({KafkaException.class})
    public ResponseEntity<ResponseError> handleKafkaException(KafkaException ex) {
        return new ResponseEntity<>(
            new ResponseError(
                LocalDateTime.now().toString(),
                String.valueOf(HttpStatus.SERVICE_UNAVAILABLE.value()),
                HttpStatus.SERVICE_UNAVAILABLE.getReasonPhrase(),
                ex.getClass().getSimpleName(),
                ex.getMessage()
            ), HttpStatus.SERVICE_UNAVAILABLE);
    }

    //TODO: Método privado para generar la responseEntity
}
