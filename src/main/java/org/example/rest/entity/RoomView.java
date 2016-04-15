package org.example.rest.entity;

import com.fasterxml.jackson.annotation.JsonValue;

public enum RoomView {
    NONE("none"), SEA_VIEW("Sea view"), POOL_VIEW("Pool view"), TRASH_HEAP_VIEW("View on a trash heap");

    public final String value;

    private RoomView(final String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    public static RoomView byValue(final String value) {
        for (final RoomView v : values()) {
            if (v.value.equals(value)) {
                return v;
            }
        }
        return null;
    }

}
