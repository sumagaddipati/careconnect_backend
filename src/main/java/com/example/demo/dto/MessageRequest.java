package com.example.demo.dto;

public class MessageRequest {

    private Long requestId;
    private String msg;

    public Long getRequestId() { return requestId; }
    public void setRequestId(Long requestId) { this.requestId = requestId; }

    public String getMsg() { return msg; }
    public void setMsg(String msg) { this.msg = msg; }
}