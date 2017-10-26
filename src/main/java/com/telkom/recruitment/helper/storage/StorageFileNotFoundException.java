package com.telkom.recruitment.helper.storage;

/**
 *
 * @author Irman Juliansyah <irmanjuliansyah@gmail.com>
 */
public class StorageFileNotFoundException extends StorageException {

    public StorageFileNotFoundException(String message) {
        super(message);
    }

    public StorageFileNotFoundException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
