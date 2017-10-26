package com.telkom.recruitment.helper;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.hateoas.ResourceSupport;

/**
 *
 * @author Irman Juliansyah <irmanjuliansyah@gmail.com>
 */
public class HateoasResource extends ResourceSupport {

    private final Object content;

    @JsonCreator
    public HateoasResource(@JsonProperty("upload") Object content) {
        this.content = content;
    }

    public Object getContent() {
        return content;
    }
}
