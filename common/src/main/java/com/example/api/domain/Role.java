package com.example.api.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Role {

    ADMIN("com.example.role.admin"), DEVELOPER("com.example.role.developer"), USER("com.example.role.user");

    final String nameProperty;

}
