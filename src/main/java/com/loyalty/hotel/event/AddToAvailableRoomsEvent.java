package com.loyalty.hotel.event;

import lombok.EqualsAndHashCode;
import lombok.Value;

import java.time.Instant;


@EqualsAndHashCode(callSuper = true)
@Value
public class AddToAvailableRoomsEvent extends BaseEvent{

    public AddToAvailableRoomsEvent(String id, long availableRooms) {
        super(id, availableRooms, "add rooms", Instant.now());
    }
}
