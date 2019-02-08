package com.loyalty.hotel.command;


import lombok.EqualsAndHashCode;
import lombok.Value;


@EqualsAndHashCode(callSuper = true)
@Value
public class AddToAvailableRoomsCommand extends BaseCommand {
    public AddToAvailableRoomsCommand(String id, long availableRooms) {
        super(id, availableRooms);
    }
}
