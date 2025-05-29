/**
 * Seats Class Type Entity
 * Copyright (C) 2025
 * Created by hanxu on 2025/05/24.
 */
package com.example.flightapi.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

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
}
