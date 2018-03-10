package com.crud.tasks.domain;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@Getter
@RequiredArgsConstructor
public class Mail {
    @NonNull
    private String mailTo;

    @NonNull
    private String subject;

    @NonNull
    private String message;

    private Optional<String> toCc;

}
