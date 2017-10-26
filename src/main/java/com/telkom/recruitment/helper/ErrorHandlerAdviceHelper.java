package com.telkom.recruitment.helper;

import org.springframework.hateoas.VndErrors;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author Irman Juliansyah <irmanjuliansyah@gmail.com>
 */
@ControllerAdvice
public class ErrorHandlerAdviceHelper {

    @ResponseBody
    @ExceptionHandler(NotFoundRestHelper.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public VndErrors idNotFoundExceptionHandler(NotFoundRestHelper notFoundRestHelper) {
        return new VndErrors("error", notFoundRestHelper.getMessage());
    }

}
