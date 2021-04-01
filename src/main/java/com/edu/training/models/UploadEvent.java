package com.edu.training.models;

public class UploadEvent {

    private String eventType = "progress";
    private Object state;

    public UploadEvent() {}

    public UploadEvent(String eventType, Object state) {
        this.eventType = eventType;
        this.state = state;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getEventType() {
        return eventType;
    }

    public void setState(Object state) {
        this.state = state;
    }

    public Object getState() {
        return state;
    }
}
