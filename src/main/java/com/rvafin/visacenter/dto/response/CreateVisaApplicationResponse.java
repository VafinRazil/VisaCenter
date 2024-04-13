package com.rvafin.visacenter.dto.response;

public class CreateVisaApplicationResponse {

    private boolean created;

    private String message;

    public CreateVisaApplicationResponse(){}

    public boolean isCreated() {
        return created;
    }

    public void setCreated(boolean created) {
        this.created = created;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
