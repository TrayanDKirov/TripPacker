package bg.sofia.uni.fmi.tdkirov.trippacker.controller;

import bg.sofia.uni.fmi.tdkirov.trippacker.dto.tripluggage.TripLuggagePreviewDto;
import bg.sofia.uni.fmi.tdkirov.trippacker.dto.tripluggage.TripLuggageResponseDto;
import bg.sofia.uni.fmi.tdkirov.trippacker.dto.tripluggage.TripLuggageUpdateDto;
import bg.sofia.uni.fmi.tdkirov.trippacker.dto.tripluggage.TripLuggageCreateDto;
import bg.sofia.uni.fmi.tdkirov.trippacker.service.TripLuggageService;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.security.Principal;
import java.util.Set;

@AllArgsConstructor
@RestController
@RequestMapping("/api/trip-luggage")
public class TripLuggageController {
    private TripLuggageService service;

    @PostMapping
    public ResponseEntity createTripLuggage(@NotNull @RequestBody TripLuggageCreateDto tripLuggageDto,
                                            Principal principal) {
        Long id = service.createTripLuggage(tripLuggageDto, principal.getName());

        URI location = URI.create("api/trip-luggage/" + id);

        return ResponseEntity.created(location).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TripLuggageResponseDto> getTripLuggage(@NotNull @PathVariable Long id,
                                                                 Principal principal) {
        TripLuggageResponseDto result = service.getTripLuggageById(id, principal.getName());

        return ResponseEntity.ok(result);
    }

    @GetMapping("/all")
    public ResponseEntity<Set<TripLuggagePreviewDto>> getAllTripLuggage(Principal principal) {
        Set<TripLuggagePreviewDto> result = service.getAllTripLuggage(principal.getName());

        return ResponseEntity.ok(result);
    }

    @PatchMapping("/{id}")
    public ResponseEntity updateTripLuggage(@NotNull @PathVariable Long id,
                                            @NotNull @RequestBody TripLuggageUpdateDto tripLuggageDto,
                                            Principal principal) {
        service.updateTripLuggage(id, tripLuggageDto, principal.getName());

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteTripLuggage(@NotNull @PathVariable Long id,
                                            Principal principal) {
        service.deleteTripLuggageById(id, principal.getName());

        return ResponseEntity.ok().build();
    }
}
