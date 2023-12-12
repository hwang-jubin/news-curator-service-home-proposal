package project.newscuratorservicehomeproposal.exception.customException;

import lombok.Getter;
import project.newscuratorservicehomeproposal.exception.ErrorCode;

@Getter
public class RestApiException extends RuntimeException{
    ErrorCode errorCode ;
    public RestApiException(String Message, ErrorCode errorCode) {
        super(Message);
        this.errorCode = errorCode;
    }

}
