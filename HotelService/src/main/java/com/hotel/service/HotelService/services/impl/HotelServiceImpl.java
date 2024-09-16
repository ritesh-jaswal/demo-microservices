package com.hotel.service.HotelService.services.impl;

import com.hotel.service.HotelService.entities.Hotel;
import com.hotel.service.HotelService.exceptions.ResourceNotFoundException;
import com.hotel.service.HotelService.repositories.HotelRepository;
import com.hotel.service.HotelService.services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class HotelServiceImpl implements HotelService
{
    @Autowired
    private HotelRepository hotelRepository;

    @Override
    public Hotel create(Hotel hotel)
    {
        String hotelId = UUID.randomUUID().toString();
        hotel.setId(hotelId);
        return hotelRepository.save(hotel);
    }

    @Override
    public List<Hotel> getAll() {
        return hotelRepository.findAll();
    }

    @Override
    public Hotel get(String id) {
        return hotelRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Hotel","HotelId",id));
    }
}
