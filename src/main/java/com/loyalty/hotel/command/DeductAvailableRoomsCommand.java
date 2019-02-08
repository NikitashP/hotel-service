package com.loyalty.hotel.command;

import lombok.EqualsAndHashCode;
import lombok.Value;

@EqualsAndHashCode(callSuper = true)
@Value
public class DeductAvailableRoomsCommand extends BaseCommand {

    public DeductAvailableRoomsCommand(String id, long availableRooms) {
        super(id, availableRooms);
    }
}
