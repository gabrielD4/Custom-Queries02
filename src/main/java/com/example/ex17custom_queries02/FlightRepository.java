package com.example.ex17custom_queries02;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {

    Collection<Flight> getFlightsByStatusOrStatus(Status status, Status status2);
}
