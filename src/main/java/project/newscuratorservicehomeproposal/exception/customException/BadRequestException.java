package project.newscuratorservicehomeproposal.exception.customException;

import lombok.Getter;
import project.newscuratorservicehomeproposal.exception.ErrorCode;

@Getter
public class BadRequestException extends RestApiException {
    public BadRequestException() {
        super(ErrorCode.BAD_REQUEST.getMessage(), ErrorCode.BAD_REQUEST);

    }



}