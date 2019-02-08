package com.loyalty.hotel.domain;

import com.loyalty.hotel.command.AddToAvailableRoomsCommand;
import com.loyalty.hotel.command.DeductAvailableRoomsCommand;
import com.loyalty.hotel.command.InitializeHotelCommand;
import com.loyalty.hotel.event.AddToAvailableRoomsEvent;
import com.loyalty.hotel.event.DeductAvailableRoomsEvent;
import com.loyalty.hotel.event.InitializeHotelEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;

import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;

@Aggregate
public class HotelAggregate {

    private static final long serialVersionUID = -5977984483620451665L;

    @AggregateIdentifier
    private String id;

    private long requiredBonusPoints;

    private String name;

    private long availableRooms;

    HotelAggregate(){

    }

    @CommandHandler
    public HotelAggregate(InitializeHotelCommand command){
    apply(new InitializeHotelEvent(command.getId(), command.getPointsNeeded(),command.getName(),command.getRooms()));
    }

    @EventSourcingHandler
    void on(InitializeHotelEvent initializeHotelEvent){
        this.id= initializeHotelEvent.getId();
        this.availableRooms=initializeHotelEvent.getAvailableRooms();
        this.name=initializeHotelEvent.getName();
        this.requiredBonusPoints=initializeHotelEvent.getPointsNeeded();
    }

    @CommandHandler
    void handle(DeductAvailableRoomsCommand deductAvailableRoomsCommand){

        if(deductAvailableRoomsCommand.getRooms() > availableRooms){
            throw new IllegalArgumentException("Requested rooms is greater than available rooms");
        }

        apply(new DeductAvailableRoomsEvent(deductAvailableRoomsCommand.getId(), deductAvailableRoomsCommand.getRooms()));
    }

    @EventSourcingHandler
    void on(DeductAvailableRoomsEvent deductAvailableRoomsEvent) {
        this.availableRooms -= deductAvailableRoomsEvent.getAvailableRooms();
    }

    @CommandHandler
    void handle(AddToAvailableRoomsCommand deductPointsCommand){
        apply(new AddToAvailableRoomsEvent(deductPointsCommand.getId(),deductPointsCommand.getRooms()));
    }

    @EventSourcingHandler
    void on(AddToAvailableRoomsEvent addToAvailableRoomsEvent){
        this.availableRooms+= addToAvailableRoomsEvent.getAvailableRooms();
    }

}
