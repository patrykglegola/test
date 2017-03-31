package com.example.student.test1;


class Grade {
    private String label;
    private int value;
    private int id;

    public Grade(int id) {
        this.label = "Ocena"+(id+1);
        this.id = id;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public int getId() {
        return id;
    }

    public boolean hasValue() {
        if (value>0)
                return true;
        else
            return false;
    }
}
