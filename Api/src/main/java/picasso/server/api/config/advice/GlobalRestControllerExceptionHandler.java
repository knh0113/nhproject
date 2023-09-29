package picasso.server.api.config.advice;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import picasso.server.common.dto.ErrorDetail;
import picasso.server.common.exception.GlobalException;
import picasso.server.common.exception.RestBaseException;

@Slf4j
@RequiredArgsConstructor
@RestControllerAdvice
public class GlobalRestControllerExceptionHandler {
    @ExceptionHandler(RestBaseException.class)
    protected ResponseEntity<ErrorDetail> baseExceptionHandle(RestBaseException e, HttpServletRequest request) {
        ErrorDetail errorDetail = e.getErrorCode().getErrorDetail();
        log.warn("ExceptionName >>> {}, ErrorCode >>> {}, ExceptionReason >>> {}",
                e.getClass(), errorDetail.getStatusCode(), errorDetail.getReason());

        return ResponseEntity.status(errorDetail.getStatusCode()).body(errorDetail);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ErrorDetail> argumentNotValidHandle(MethodArgumentNotValidException e, HttpServletRequest request) {
        ErrorDetail errorDetail = ErrorDetail.builder()
                .statusCode(GlobalException.METHOD_ARGUMENT_ERROR.getStatusCode())
                .reason(GlobalException.METHOD_ARGUMENT_ERROR.getReason())
                .build();
        return ResponseEntity.status(errorDetail.getStatusCode()).body(errorDetail);
    }
}
