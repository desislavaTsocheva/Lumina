package com.club.lumina.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonProperty("name")
    private String clubName;
    private double latitude;
    private double longitude;
    @JsonProperty("promotion")
    private String currentPromotion;
    @JsonProperty("artist")
    private String performerTonight;
    @JsonProperty("status")
    private String occupancyStatus;

}
