package com.codeelit.ts.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Test implements Serializable {
    String Name;
    ArrayList<question> Questions;
    Long time;

    public Test() {
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public ArrayList<question> getQuestions() {
        return Questions;
    }

    public void setQuestions(ArrayList<question> questions) {
        Questions = questions;
    }
}
