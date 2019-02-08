package com.loyalty.hotel.controller.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class CreateHotel {

    @NotNull
    private long requiredBonusPoints;
    @NotNull
    private String name;
    @NotNull
    private long availableRooms;
}
