package com.phen.mbanking.exception;

import lombok.Builder;

@Builder
public record FieldError(
        String field,
        String detail
) {
}
