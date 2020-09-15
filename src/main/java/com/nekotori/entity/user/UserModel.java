package com.nekotori.entity.user;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@RequiredArgsConstructor
@Data
public class UserModel implements Serializable {
    @NonNull
    private String name;

    @NonNull
    private String id;


}
