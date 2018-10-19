package com.example.issues.issues;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Status {

    OPEN("com.example.issues.statusOpen"), CLOSED("com.example.issues.statusClosed");

    final String nameProperty;

}
