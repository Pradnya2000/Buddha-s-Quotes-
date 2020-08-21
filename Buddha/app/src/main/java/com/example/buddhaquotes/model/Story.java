package com.example.buddhaquotes.model;

public class Story {
    String title;
    String storyData;

    public Story(String title, String storyData) {
        this.title = title;
        this.storyData = storyData;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStoryData() {
        return storyData;
    }

    public void setStoryData(String storyData) {
        this.storyData = storyData;
    }
}
