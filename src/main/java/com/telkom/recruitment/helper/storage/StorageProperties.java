package com.telkom.recruitment.helper.storage;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 *
 * @author Irman Juliansyah <irmanjuliansyah@gmail.com>
 */
@ConfigurationProperties("storage")
public class StorageProperties {

    private String location = "upload-dir";

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}