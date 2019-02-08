package com.loyalty.hotel.command;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Value;


@EqualsAndHashCode(callSuper = true)
@Value
public class InitializeHotelCommand extends BaseCommand{

    @Getter private final long pointsNeeded;
    @Getter private final String name;

    public InitializeHotelCommand(String id, long availableRooms, long pointsNeeded, String name) {
        super(id, availableRooms);
        this.pointsNeeded = pointsNeeded;
        this.name = name;
    }
}
