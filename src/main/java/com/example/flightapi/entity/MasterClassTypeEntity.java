/**
 * Seats Class Type Entity
 * Copyright (C) 2025
 * Created by hanxu on 2025/05/24.
 */
package com.example.flightapi.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "master_class_type")
public class MasterClassTypeEntity {
    @Id
    @Column(name = "TYPE_ID", nullable = false)
    private Long typeId;

    @Column(name = "TYPE_NAME", nullable = false, length = 30)
    private String typeName;

    @OneToMany(mappedBy = "classType")
    private List<FlightEntity> flights;
}
