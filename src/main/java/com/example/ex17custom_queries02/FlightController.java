package com.example.ex17custom_queries02;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/v1/flights")
public class FlightController {

    @Autowired
    private FlightService flightService;

    @PostMapping
    public Collection<Flight> create(@RequestParam(required = false, defaultValue = "100") int flightsNumber) {
        return flightService.create(flightsNumber);
    }

    @GetMapping
    public Page<Flight> loadAll(@RequestParam int page, @RequestParam int size) {
        return flightService.getAll(page, size);
    }

    @GetMapping("/onTime")
    public Collection<Flight> loadOnTime() {
        return flightService.getOnTime();
    }

    @GetMapping("/status")
    public Collection<Flight> loadBy(@RequestParam String status, @RequestParam String status2) {
        return flightService.getFlightBy(status, status2);
    }
}
