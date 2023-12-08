package project.newscuratorservicehomeproposal.exception.customException;

import lombok.Getter;
import project.newscuratorservicehomeproposal.exception.ErrorCode;

/**
 * 404 NOT_FOUND: 리소스를 찾을 수 없음
 */
@Getter
public class NotFoundException extends RestApiException {
    public NotFoundException(){
        super(ErrorCode.NOT_FOUND.getMessage(), ErrorCode.NOT_FOUND);
    }
}
