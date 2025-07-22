package bg.sofia.uni.fmi.tdkirov.trippacker.mapper;

import bg.sofia.uni.fmi.tdkirov.trippacker.dto.packinggroup.PackingGroupCreateDto;
import bg.sofia.uni.fmi.tdkirov.trippacker.dto.packinggroup.PackingGroupResponseDto;
import bg.sofia.uni.fmi.tdkirov.trippacker.model.PackingGroup;
import bg.sofia.uni.fmi.tdkirov.trippacker.model.User;
import org.springframework.stereotype.Component;

@Component
public class PackingGroupMapper { // TODO check response dto
    public PackingGroup toEntity(PackingGroupCreateDto packingGroupDto, User user) {
        PackingGroup result = new PackingGroup(packingGroupDto.getName(), null);
        result.setCreatedBy(user);

        return result;
    }

    public PackingGroupResponseDto toResponseDto(PackingGroup packingGroup) {
        return new PackingGroupResponseDto(packingGroup.getId(), packingGroup.getName());
    }
}
