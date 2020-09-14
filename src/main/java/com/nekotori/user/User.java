package com.nekotori.user;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@RequiredArgsConstructor
@Data
public class User implements Serializable {
    @NonNull
    private String name;

    @NonNull
    private String id;


}
