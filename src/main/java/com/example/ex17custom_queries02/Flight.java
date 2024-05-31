package com.example.ex17custom_queries02;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Data
@Entity
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    private String fromAirport;

    private String toAirport;

    @Enumerated(EnumType.STRING)
    private Status status;

    public Flight(String description, String fromAirport, String toAirport, Status status) {
        this.description = description;
        this.fromAirport = fromAirport;
        this.toAirport = toAirport;
        this.status = status;
    }
}
