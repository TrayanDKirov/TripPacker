package bg.sofia.uni.fmi.tdkirov.trippacker.service.implementation;

import bg.sofia.uni.fmi.tdkirov.trippacker.dto.tripluggage.TripLuggageCreateDto;
import bg.sofia.uni.fmi.tdkirov.trippacker.dto.tripluggage.TripLuggageResponseDto;
import bg.sofia.uni.fmi.tdkirov.trippacker.dto.tripluggage.TripLuggageUpdateDto;
import bg.sofia.uni.fmi.tdkirov.trippacker.service.TripLuggageService;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class TripLuggageServiceImpl implements TripLuggageService {
    @Override
    public Long createTripLuggage(TripLuggageCreateDto tripLuggageDto, String currentUser) {
        return 0L;
    }

    @Override
    public TripLuggageResponseDto getTripLuggageById(Long id, String currentUser) {
        return null;
    }

    @Override
    public Set<TripLuggageResponseDto> getAllTripLuggage(String currentUser) {
        return Set.of();
    }

    @Override
    public void updateTripLuggage(Long id, TripLuggageUpdateDto tripLuggageDto, String currentUser) {

    }

    @Override
    public void deleteTripLuggageById(Long id, String currentUser) {

    }
}
