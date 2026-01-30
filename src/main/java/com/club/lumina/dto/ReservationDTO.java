package com.club.lumina.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;


@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class ReservationDTO {
    private LocalDateTime date;
    private Integer count;
    private String type;
    private Integer discount;
    private String status;

    private UUID clients_id;
    private UUID club_id;
    private UUID table_id;

}