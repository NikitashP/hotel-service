package com.loyalty.hotel.command;

import lombok.Getter;
import org.axonframework.commandhandling.TargetAggregateIdentifier;

public abstract class BaseCommand {
    @Getter
    @TargetAggregateIdentifier
    protected final String id;
    @Getter
    protected final long rooms;

    protected BaseCommand(String id, long rooms) {
        this.id = id;
        this.rooms = rooms;
    }
}
