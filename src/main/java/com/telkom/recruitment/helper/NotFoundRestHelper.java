package com.telkom.recruitment.helper;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author Irman Juliansyah <irmanjuliansyah@gmail.com>
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundRestHelper extends RuntimeException {

    public NotFoundRestHelper(String id, String message) {
        super(message + " " + id);
    }

}
