package com.loyalty.hotel.query;
import com.loyalty.hotel.event.AddToAvailableRoomsEvent;
import com.loyalty.hotel.event.DeductAvailableRoomsEvent;
import com.loyalty.hotel.event.InitializeHotelEvent;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HotelEventHandler {

    private final HotelRepository hotelRepository;

    @Autowired
    public HotelEventHandler(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    @EventHandler
    void on(InitializeHotelEvent event){
    hotelRepository.save(new Hotel(event.getId(),event.getPointsNeeded(),event.getName(),event.getAvailableRooms()));
    }


    @EventHandler
    void on(AddToAvailableRoomsEvent addPointsEvent){
        hotelRepository.findById(addPointsEvent.getId()).ifPresent(hotel -> {
            hotel.setAvailableRooms(hotel.getAvailableRooms()+addPointsEvent.getAvailableRooms());
            hotelRepository.save(hotel);
        });
    }

    @EventHandler
    void on(DeductAvailableRoomsEvent deductAvailableRoomsEvent){
        hotelRepository.findById(deductAvailableRoomsEvent.getId()).ifPresent(hotel -> {
            hotel.setAvailableRooms(hotel.getAvailableRooms()-deductAvailableRoomsEvent.getAvailableRooms());
            hotelRepository.save(hotel);
        });
    }

}
