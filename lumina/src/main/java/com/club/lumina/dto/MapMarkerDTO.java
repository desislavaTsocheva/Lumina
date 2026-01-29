package com.club.lumina.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class MapMarkerDTO {
    private String clubName;
    private double latitude;
    private double longitude;
    private String currentPromotion;
    private String performerTonight;
    private String occupancyStatus;

}
