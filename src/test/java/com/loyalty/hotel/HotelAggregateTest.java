package com.loyalty.hotel;

import com.loyalty.hotel.command.AddToAvailableRoomsCommand;
import com.loyalty.hotel.command.DeductAvailableRoomsCommand;
import com.loyalty.hotel.command.InitializeHotelCommand;
import com.loyalty.hotel.domain.HotelAggregate;
import com.loyalty.hotel.event.AddToAvailableRoomsEvent;
import com.loyalty.hotel.event.DeductAvailableRoomsEvent;
import com.loyalty.hotel.event.InitializeHotelEvent;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class HotelAggregateTest {

    private FixtureConfiguration fixture;

    @BeforeEach
    public void setUp() {
        fixture = new AggregateTestFixture(HotelAggregate.class);
    }

    @Test
    public void initializeCustomer() {
        fixture.given().when(new InitializeHotelCommand("id",10L,100L,"name")).expectSuccessfulHandlerExecution().expectEvents(
                new InitializeHotelEvent("id",100L,"name",10L));
    }

    @Test
    public void AddPointsToCustomer() {
        fixture.given(new InitializeHotelEvent("id",10L,"name",100L))
                .when(new AddToAvailableRoomsCommand("id",10L)).expectSuccessfulHandlerExecution().expectSuccessfulHandlerExecution().expectEvents(
                new AddToAvailableRoomsEvent("id",10L));
    }

    @Test
    public void DeductPointsToCustomer() {
        fixture.given(new InitializeHotelEvent("id",10L,"name",100L))
                .when(new DeductAvailableRoomsCommand("id",10L)).expectSuccessfulHandlerExecution().expectSuccessfulHandlerExecution().expectEvents(
                new DeductAvailableRoomsEvent("id",10L));
    }
}
