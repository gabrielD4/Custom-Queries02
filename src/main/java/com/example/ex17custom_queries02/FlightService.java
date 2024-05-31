package com.example.ex17custom_queries02;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class FlightService {

    @Autowired
    private FlightRepository flightRepository;

    private final Random random = new Random();

    private String generateRandomString(int length) {
        return random.ints(65, 91) // A-Z ASCII range
                .limit(length)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    private Status randomStatus() {
        int x = random.nextInt(Status.values().length);
        return Status.values()[x];
    }

    public Collection<Flight> create(int flightsNumber) {
        List<Flight> flights = IntStream.range(0, flightsNumber)
                .mapToObj(i -> new Flight(
                        generateRandomString(10),
                        generateRandomString(3),
                        generateRandomString(3),
                        randomStatus()
                ))
                .collect(Collectors.toList());

        return flightRepository.saveAll(flights);
    }

    public Page<Flight> getAll(int page, int size) {
        Sort sort = Sort.by(Sort.Direction.ASC, "fromAirport");
        Pageable pageable = PageRequest.of(page, size, sort);
        return flightRepository.findAll(pageable);
    }

    public Collection<Flight> getOnTime() {
        Collection<Flight> flights = flightRepository.findAll();
        Collection<Flight> onTimeFlights = new ArrayList<>();
        for (Flight flight : flights) {
            if (flight.getStatus() == Status.ONTIME) {
                onTimeFlights.add(flight);
            }
        }
        return onTimeFlights;
    }

    public Collection<Flight> getFlightBy(String status, String status2) {
        Status enumStatus = Status.convertFrom(status);
        Status enumStatus2 = Status.convertFrom(status2);
        return flightRepository.getFlightsByStatusOrStatus(enumStatus, enumStatus2);
    }
}

