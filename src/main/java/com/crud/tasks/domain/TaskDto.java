package com.crud.tasks.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
@EqualsAndHashCode
@Getter
@AllArgsConstructor
public class TaskDto {
    private Long id;
    private String title;
    private String content;

}
