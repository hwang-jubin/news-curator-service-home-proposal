package project.newscuratorservicehomeproposal.exception.exceptionHandler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import project.newscuratorservicehomeproposal.exception.ErrorResponse;
import project.newscuratorservicehomeproposal.exception.customException.BadRequestException;
import project.newscuratorservicehomeproposal.exception.customException.NotFoundException;

@RestControllerAdvice
@Slf4j
public class ExceptionHandlers {
    /**
     * BadRequest 400 handler
     * @param e
     * @return
     */
    @ExceptionHandler(BadRequestException.class)
    protected ResponseEntity<ErrorResponse> badRequestExceptionHandler(BadRequestException e){

        log.info(e.getMessage());
        //exception에서 온 error를 규격화한 response에 넣음
        ErrorResponse errorResponse = new ErrorResponse(e.getErrorCode().getHttpStatus(), e.getMessage());

        //response를 responseEntity에 넣어서 return
        return new ResponseEntity<>(errorResponse, errorResponse.getHttpStatus());
    }

    /**
     * notFound 404 handler
     * @param e
     * @return
     */
    @ExceptionHandler(NotFoundException.class)
    protected ResponseEntity<ErrorResponse> notFoundExceptionHandler(NotFoundException e){

        log.info(e.getMessage());

        ErrorResponse errorResponse = new ErrorResponse(e.getErrorCode().getHttpStatus(), e.getMessage());

        return new ResponseEntity<>(errorResponse, errorResponse.getHttpStatus());
    }
}
