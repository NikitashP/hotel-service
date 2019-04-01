package com.loyalty.hotel.controller;


import com.loyalty.hotel.command.AddToAvailableRoomsCommand;
import com.loyalty.hotel.command.DeductAvailableRoomsCommand;
import com.loyalty.hotel.command.InitializeHotelCommand;
import com.loyalty.hotel.controller.request.AlterAvailableRooms;
import com.loyalty.hotel.controller.request.CreateHotel;
import com.loyalty.hotel.query.Hotel;
import com.loyalty.hotel.query.HotelQueryHandler;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.commandhandling.model.AggregateNotFoundException;
import org.axonframework.common.IdentifierFactory;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.concurrent.CompletableFuture;


@RestController
@RequiredArgsConstructor
public class HotelController {

    private final IdentifierFactory identifierFactory = IdentifierFactory.getInstance();

    private final CommandGateway commandGateway;

    private final HotelQueryHandler hotelQueryHandler;

    @PostMapping("/create")
    public CompletableFuture<String> createHotel(@Valid @RequestBody CreateHotel createHotel){
        return commandGateway.send(new InitializeHotelCommand(identifierFactory.generateIdentifier(),createHotel.getAvailableRooms(),createHotel.getRequiredBonusPoints(),createHotel.getName()));
    }

    @PutMapping("/rooms/add")
    public void addToAvailableRooms(@Valid @RequestBody AlterAvailableRooms alterAvailableRooms){
        commandGateway.send(new AddToAvailableRoomsCommand(alterAvailableRooms.getId(),alterAvailableRooms.getRooms()));
    }

    @PutMapping("/rooms/deduct")
    public void deductAvailableRooms(@Valid @RequestBody AlterAvailableRooms alterAvailableRooms){
        commandGateway.send(new DeductAvailableRoomsCommand(alterAvailableRooms.getId(),alterAvailableRooms.getRooms()));
    }

    @GetMapping("/hotel/{id}")
    public Hotel getHotel(@NonNull @PathVariable("id") String hotelId){
        return hotelQueryHandler.findHotel(hotelId);
    }

    @GetMapping("/events/{id}")
    public List<Object> getAllChangesToHotel(@NonNull @PathVariable("id") String hotelId){
        return hotelQueryHandler.getAllChangesForHotel(hotelId);
    }


    @ExceptionHandler(AggregateNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void notFound() {
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String invalidInput(IllegalArgumentException exception) {
        return exception.getMessage();
    }
}
