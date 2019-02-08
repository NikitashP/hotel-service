package com.loyalty.hotel.query;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(of = { "id" })
public class Hotel {

    @Id
    private String id;

    private long requiredBonusPoints;

    private String name;

    @Setter
    private long availableRooms;

    public Hotel(String id, long requiredBonusPoints, String name, long availableRooms) {
        this.id = id;
        this.requiredBonusPoints = requiredBonusPoints;
        this.name = name;
        this.availableRooms = availableRooms;
    }
}
