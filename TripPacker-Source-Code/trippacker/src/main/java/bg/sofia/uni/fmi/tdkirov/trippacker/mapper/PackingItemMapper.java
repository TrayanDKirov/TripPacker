package bg.sofia.uni.fmi.tdkirov.trippacker.mapper;

import bg.sofia.uni.fmi.tdkirov.trippacker.dto.packingitem.PackingItemCreateDto;
import bg.sofia.uni.fmi.tdkirov.trippacker.dto.packingitem.PackingItemResponseDto;
import bg.sofia.uni.fmi.tdkirov.trippacker.model.PackingItem;
import bg.sofia.uni.fmi.tdkirov.trippacker.model.User;
import org.springframework.stereotype.Component;

@Component
public class PackingItemMapper {
    public PackingItem toEntity(PackingItemCreateDto packingItemDto, User user) {
        return null;
    }

    public PackingItemResponseDto toResponseDto(PackingItem packingItem) {
        return null;
    }
}
