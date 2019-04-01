package com.loyalty.hotel.query;

import org.axonframework.eventsourcing.eventstore.EventStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class HotelQueryHandler {


    private final EventStore eventStore;

    private final HotelRepository hotelRepository;

    @Autowired
    public HotelQueryHandler(EventStore eventStore, HotelRepository hotelRepository) {
        this.eventStore = eventStore;
        this.hotelRepository = hotelRepository;
    }

    public Hotel findHotel(String hotelId) {
        return hotelRepository.findById(hotelId).orElseThrow(() -> new IllegalArgumentException("unable to find hotel with this Id"));

    }


    public List<Object> getAllChangesForHotel(String hotelId){
        return eventStore.readEvents(hotelId).asStream().map(s -> s.getPayload()).collect(Collectors.toList());
    }
}
