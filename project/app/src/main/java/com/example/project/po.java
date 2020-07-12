package com.example.project;

public class po {
    String name;
    String total;
    String present;

    public po(String name, String total, String present) {
        this.name = name;
        this.total = total;
        this.present = present;
    }

    public po() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getPresent() {
        return present;
    }

    public void setPresent(String present) {
        this.present = present;
    }
}
