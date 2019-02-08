package com.loyalty.hotel.event;

import lombok.EqualsAndHashCode;
import lombok.Value;

import java.time.Instant;

@EqualsAndHashCode(callSuper = true)
@Value
public class DeductAvailableRoomsEvent extends BaseEvent{

    public DeductAvailableRoomsEvent(String id, long points) {
        super(id, points, "deducted rooms", Instant.now());
    }
}
