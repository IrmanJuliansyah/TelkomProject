package com.telkom.recruitment.helper.storage;

/**
 *
 * @author Irman Juliansyah <irmanjuliansyah@gmail.com>
 */
public class StorageException extends RuntimeException {

    public StorageException(String message) {
        super(message);
    }

    public StorageException(String message, Throwable throwable) {
        super(message, throwable);
    }

}
