package dev.anirban.stockbot.dto.response;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ResponseWrapper<T> {
    private String message;
    private T data;

    public ResponseWrapper(String message) {
        this.message = message;
    }

    public ResponseWrapper(T data) {
        this.data = data;
    }
}