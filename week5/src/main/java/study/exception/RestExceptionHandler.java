package study.config;

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
    @ExceptionHandler({NullPointerException.class})
    public ResponseEntity<String> handleNullPointerException(Exception e) {
        System.out.println("null");
        e.printStackTrace();
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<String> handleException(Exception e) {
        e.printStackTrace();
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
