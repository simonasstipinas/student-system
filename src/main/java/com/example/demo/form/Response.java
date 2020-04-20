package com.example.demo.form;

public class Response {
    public int status;
    public String message;
    public Object answer;

    public Response(int status, String message, Object answer) {
        this.status = status;
        this.message = message;
        this.answer = answer;
    }

    public Response(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getAnswer() {
        return answer;
    }

    public void setAnswer(Object answer) {
        this.answer = answer;
    }
}
