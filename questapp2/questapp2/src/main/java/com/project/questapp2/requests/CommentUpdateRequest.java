package com.project.questapp2.requests;

import lombok.Data;
@Data
public class CommentUpdateRequest {
    String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}