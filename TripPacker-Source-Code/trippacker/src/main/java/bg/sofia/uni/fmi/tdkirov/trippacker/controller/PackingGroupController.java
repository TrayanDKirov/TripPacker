package bg.sofia.uni.fmi.tdkirov.trippacker.controller;

import bg.sofia.uni.fmi.tdkirov.trippacker.dto.packinggroup.PackingGroupCreateDto;
import bg.sofia.uni.fmi.tdkirov.trippacker.dto.packinggroup.PackingGroupResponseDto;
import bg.sofia.uni.fmi.tdkirov.trippacker.dto.packinggroup.PackingGroupUpdateDto;
import bg.sofia.uni.fmi.tdkirov.trippacker.service.PackingGroupService;
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
import java.util.Set;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/packing-group")
public class PackingGroupController {
    private PackingGroupService service;

    @PostMapping
    public ResponseEntity createPackingGroup(@NotNull @RequestBody PackingGroupCreateDto packingGroupDto) {
        Long id = service.createPackingGroup(packingGroupDto, "");

        URI location = URI.create("/api/v1/packing-group/" + id);

        return ResponseEntity.created(location).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PackingGroupResponseDto> getPackingGroup(@NotNull @PathVariable Long id) {
        PackingGroupResponseDto result = service.getPackingGroupById(id, "");

        return ResponseEntity.ok(result);
    }

    @GetMapping("/all")
    public ResponseEntity<Set<PackingGroupResponseDto>> getAllPackingGroups() {
        Set<PackingGroupResponseDto> result = service.getAllPackingGroups("");

        return ResponseEntity.ok(result);
    }

    @PatchMapping("/{id}")
    public ResponseEntity updatePackingGroup(@NotNull @PathVariable Long id,
                                             @NotNull @RequestBody PackingGroupUpdateDto packingGroupDto) {
        service.updatePackingGroup(id, packingGroupDto, "");

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity deletePackingGroup(@NotNull @PathVariable Long id) {
        service.deletePackingGroupById(id, "");

        return ResponseEntity.ok().build();
    }
}
