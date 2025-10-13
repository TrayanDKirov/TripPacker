package bg.sofia.uni.fmi.tdkirov.trippacker.controller;

import bg.sofia.uni.fmi.tdkirov.trippacker.dto.grouptopack.GroupToPackCreateDto;
import bg.sofia.uni.fmi.tdkirov.trippacker.dto.grouptopack.GroupToPackPreviewDto;
import bg.sofia.uni.fmi.tdkirov.trippacker.dto.grouptopack.GroupToPackResponseDto;
import bg.sofia.uni.fmi.tdkirov.trippacker.dto.grouptopack.GroupToPackUpdateDto;
import bg.sofia.uni.fmi.tdkirov.trippacker.service.GroupToPackService;
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
@RequestMapping("/api/group-to-pack")
public class GroupToPackController {
    private GroupToPackService service;

    @PostMapping
    public ResponseEntity createPackingGroup(@NotNull @RequestBody GroupToPackCreateDto groupToPackDto) {
        Long id = service.createPackingGroup(groupToPackDto, "");

        URI location = URI.create("/api/group-to-pack" + id);

        return ResponseEntity.created(location).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<GroupToPackResponseDto> getPackingGroup(@NotNull @PathVariable Long id) {
        GroupToPackResponseDto result = service.getGroupToPackById(id, "");

        return ResponseEntity.ok(result);
    }

    @GetMapping("/all")
    public ResponseEntity<Set<GroupToPackPreviewDto>> getAllPackingGroupPreviews() {
        Set<GroupToPackPreviewDto> result = service.getAllPackingGroupPreviews("");

        return ResponseEntity.ok(result);
    }

    @PatchMapping("/{id}")
    public ResponseEntity updatePackingGroup(@NotNull @PathVariable Long id,
                                             @NotNull @RequestBody GroupToPackUpdateDto packingGroupDto) {
        service.updatePackingGroup(id, packingGroupDto, "");

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePackingGroup(@NotNull @PathVariable Long id) {
        service.deletePackingGroupById(id, "");

        return ResponseEntity.ok("Successfully delete group to pack. ");
    }
}
