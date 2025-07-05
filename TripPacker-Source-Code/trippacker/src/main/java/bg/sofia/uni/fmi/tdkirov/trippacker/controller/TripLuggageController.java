package bg.sofia.uni.fmi.tdkirov.trippacker.controller;

import bg.sofia.uni.fmi.tdkirov.trippacker.dto.TripLuggageUpdateDto;
import bg.sofia.uni.fmi.tdkirov.trippacker.dto.TripLuggageCreateDto;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/trip-luggage")
public class TripLuggageController {
    @PostMapping
    public ResponseEntity createTripLuggage(@NotNull @RequestBody TripLuggageCreateDto tripLuggageDto) {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity getTripLuggage(@NotNull @PathVariable Long id) {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/all")
    public ResponseEntity getAllTripLuggage() {
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity updateTripLuggage(@NotNull @PathVariable Long id,
                                            @NotNull @RequestBody TripLuggageUpdateDto tripLuggageDto) {
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteTripLuggage(@NotNull @PathVariable Long id) {
        return ResponseEntity.ok().build();
    }
}
