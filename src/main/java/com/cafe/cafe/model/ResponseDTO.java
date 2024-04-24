package com.cafe.cafe.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ResponseDTO {
    private List<String> messages;

    public ResponseDTO(String messages) {
        this.messages = Collections.singletonList(messages);
    }
}