package com.telkom.recruitment.helper;

/**
 *
 * @author Irman Juliansyah <irmanjuliansyah@gmail.com>
 */
public interface ValidationIdHelper {

    void validateSelf(String id);

    interface validateId {
        void validate(String id);
    }

    interface valiadateOtherId {
        void validateOther(String id);
    }
}
