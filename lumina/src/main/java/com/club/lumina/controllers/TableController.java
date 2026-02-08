package com.club.lumina.controllers;

import com.club.lumina.models.ClubTable;
import com.club.lumina.models.Reservation;
import com.club.lumina.services.ClubTableService;
import com.club.lumina.services.ReservationService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/table")
public class TableController {

    private final ClubTableService tableService;
    private final ReservationService reservationService;

    public TableController(ClubTableService tableService, ReservationService reservationService) {
        this.tableService = tableService;
        this.reservationService = reservationService;
    }

    @GetMapping("/book_table/{clubId}")
    public String bookTable(@PathVariable UUID clubId,
                            @RequestParam(required = false)
                            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateTime,
                            Model model) {

        if (dateTime == null) {
            dateTime = LocalDateTime.now().withSecond(0).withNano(0);
        }

        List<ClubTable> allTables = tableService.findAllByClubId(clubId);

        List<UUID> occupiedIds = reservationService.getOccupiedTableIds(clubId, dateTime);

        Map<UUID, Boolean> occupancyMap = allTables.stream()
                .collect(Collectors.toMap(
                        ClubTable::getId,
                        table -> occupiedIds != null && occupiedIds.contains(table.getId()),
                        (existing, replacement) -> existing
                ));

        model.addAttribute("tables", allTables);
        model.addAttribute("occupancyMap", occupancyMap);
        model.addAttribute("selectedDateTime", dateTime);
        model.addAttribute("clubId", clubId);

        return "table_view";
    }

    @PostMapping("/reserve")
    public String confirmReservation(@RequestParam UUID tableId,
                                     @RequestParam UUID clubId,
                                     @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime reservationTime,
                                     @RequestParam int numberOfPeople) {

        if (reservationService.isTableOccupied(tableId, reservationTime)) {
            return "redirect:/table/book_table/" + clubId + "?error=already_booked";
        }

        reservationService.createReservation(tableId, reservationTime, numberOfPeople);

        return "redirect:/table/my_reservations";
    }
}