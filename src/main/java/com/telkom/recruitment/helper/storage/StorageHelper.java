package com.telkom.recruitment.helper.storage;

import org.springframework.core.io.Resource;


/**
 *
 * @author Irman Juliansyah <irmanjuliansyah@gmail.com>
 */
public interface StorageHelper {

    void init();

    String store(String filename);

    Resource loadAsResource(String filename);

    void delete(String filename);
}
