package org.example.rest.entity;

import java.io.Serializable;

import org.example.rest.common.HasId;

public class Entity implements HasId, Serializable {
    private static final long serialVersionUID = 7793265693152582597L;
    private Long id;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(final Long id) {
        this.id = id;
    }

}
