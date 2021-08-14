package edu.miu.cs544.springstartproject.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;


@Data
@Entity
@Table(name = "countryregion")
public class CountryRegion {
    @Id
    @Column(name = "CountryRegionCode")
    private String id;

    private String Name;

    private LocalDate ModifiedDate;
}
