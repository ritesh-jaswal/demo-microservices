package com.hotel.service.HotelService.services;

import com.hotel.service.HotelService.entities.Hotel;

import java.util.List;

public interface HotelService
{
    //create

    Hotel create(Hotel hotel);

    //get all
    List<Hotel> getAll();

    //get single
    Hotel get(String id);
}
