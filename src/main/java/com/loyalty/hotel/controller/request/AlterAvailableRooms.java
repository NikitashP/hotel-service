package com.loyalty.hotel.controller.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class AlterAvailableRooms {
    @NotNull
    private String id;
    @NotNull
    private long rooms;
}
