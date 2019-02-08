package com.loyalty.hotel.event;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Value;

import javax.validation.constraints.NotNull;
import java.time.Instant;

@EqualsAndHashCode(callSuper = true)
@Value
public class InitializeHotelEvent extends BaseEvent{

    @Getter
    @NotNull
    protected final long pointsNeeded;
    
    @Getter
    @NotNull
    protected final String name;
    

    public InitializeHotelEvent(String id, long pointsNeeded, String name, long availableRooms) {
        super(id, availableRooms, "initialize hotel", Instant.now());
        this.pointsNeeded = pointsNeeded;
        this.name = name;
    }
}
