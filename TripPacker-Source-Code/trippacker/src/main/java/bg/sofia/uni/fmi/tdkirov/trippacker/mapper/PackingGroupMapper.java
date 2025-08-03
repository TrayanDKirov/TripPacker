package bg.sofia.uni.fmi.tdkirov.trippacker.mapper;

import bg.sofia.uni.fmi.tdkirov.trippacker.dto.packinggroup.PackingGroupCreateDto;
import bg.sofia.uni.fmi.tdkirov.trippacker.dto.packinggroup.PackingGroupResponseDto;
import bg.sofia.uni.fmi.tdkirov.trippacker.model.PackingGroup;
import bg.sofia.uni.fmi.tdkirov.trippacker.model.PackingItem;
import bg.sofia.uni.fmi.tdkirov.trippacker.model.User;
import org.springframework.stereotype.Component;

@Component
public class PackingGroupMapper {
    public PackingGroup toEntity(PackingGroupCreateDto packingGroupDto, User user) {
        return null;
    }

    public PackingGroupResponseDto toResponseDto(PackingGroup packingGroup) {
        return null;
    }
}
