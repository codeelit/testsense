package com.codeelit.ts.Intro;

public class ScreenItem {

    String title, description;
    int ScreenImg;

    public ScreenItem(String title, String description, int screenImg) {
        this.title = title;
        this.description = description;
        ScreenImg = screenImg;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getScreenImg() {
        return ScreenImg;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setScreenImg(int screenImg) {
        ScreenImg = screenImg;
    }
}
