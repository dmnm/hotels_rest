package org.example.rest.entity;

public enum RoomType {
    Single, Double, Twin;

    public static RoomType byValue(final String value) {
        for (final RoomType t : values()) {
            if (t.name().equals(value)) {
                return t;
            }
        }
        return null;
    }
}
