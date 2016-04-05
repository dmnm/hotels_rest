package org.example.rest.entity;

public class Person extends Entity {
    private static final long serialVersionUID = -2360639169033798784L;
    private String name;
    private String phone;

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(final String phone) {
        this.phone = phone;
    }

}
