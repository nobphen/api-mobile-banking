package com.phen.mbanking.exception;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class ErrorResponse<T> {
    private Integer code;
    private T reason;
}
