package me.dio.gameawards.controller.exceptions;

import me.dio.gameawards.controller.exceptions.dto.ApiErrorDTO;
import me.dio.gameawards.exception.BusinessException;
import me.dio.gameawards.exception.NoContentException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api")
public abstract class BaseRestController {

    @ExceptionHandler(NoContentException.class)
    public ResponseEntity<Void> handlerNoContentException(NoContentException exception) {
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ApiErrorDTO> handlerBusinessException(BusinessException exception) {
        ApiErrorDTO errorDTO = new ApiErrorDTO(exception.getMessage());
        return ResponseEntity.badRequest().body(errorDTO);
    }

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<ApiErrorDTO> handlerUnexpectedException(Throwable exception) {
        exception.printStackTrace();
        ApiErrorDTO errorDTO = new ApiErrorDTO(exception.getMessage());
        return ResponseEntity.internalServerError().body(errorDTO);
    }
}
