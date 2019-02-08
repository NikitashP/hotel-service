package com.loyalty.hotel.event;


import lombok.Getter;
import lombok.NonNull;
import org.axonframework.commandhandling.TargetAggregateIdentifier;

import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

public abstract class BaseEvent {

    @Getter
    @TargetAggregateIdentifier
    protected final String id;

    @Getter
    @NotNull
    protected final long availableRooms;

    @Getter
    @NonNull
    protected final String message;

    @Getter
    @NonNull
    private final Instant creationTime;

    protected BaseEvent(String id, @NotNull long availableRooms, String message, Instant creationTime) {
        this.id = id;
        this.availableRooms = availableRooms;
        this.message = message;
        this.creationTime = creationTime.truncatedTo(ChronoUnit.SECONDS);
    }
}
