package study.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


/**
 * - @RestControllerAdvice: @ControllerAdvice + @ResponseBody
 * 1. @ControllerAdvice: @Controller 어노태이션이 달린 클래스를 찾아 @ExceptionHandler를 적용한다.
 */
@RestControllerAdvice
public class RestExceptionHandler {

    /**
     * - @ExceptionHandler: @ExceptionHandler 어노테이션이 달린 클래스 내에서 예외가 발생했을 때 처리할 핸들러를 정의한다.
     */
    @ExceptionHandler({IllegalArgumentException.class})
    public ResponseEntity<ExceptionResponse> handleIllegalArgumentException(Exception e) {
        e.printStackTrace();
        return new ResponseEntity<>(ExceptionResponse.builder().message(e.getMessage()).exceptionType("ARGS").build(),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({NullPointerException.class})
    public ResponseEntity<ExceptionResponse> handleNullPointerException(Exception e) {
        e.printStackTrace();
        return new ResponseEntity<>(ExceptionResponse.builder().message(e.getMessage()).exceptionType("NULL").build(),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<ExceptionResponse> handleException(Exception e) {
        e.printStackTrace();
        return new ResponseEntity<>(ExceptionResponse.builder().message(e.getMessage()).exceptionType("RUNTIME").build(),
                HttpStatus.BAD_REQUEST);
    }
}
