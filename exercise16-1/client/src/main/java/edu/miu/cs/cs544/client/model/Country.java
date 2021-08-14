package edu.miu.cs.cs544.client.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Country {
    private String id;

    private String name;

    private LocalDate modifiedDate;
}
