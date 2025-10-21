package bg.sofia.uni.fmi.tdkirov.trippacker.controller;

import bg.sofia.uni.fmi.tdkirov.trippacker.dto.itemtopack.ItemToPackCreateDto;
import bg.sofia.uni.fmi.tdkirov.trippacker.dto.itemtopack.ItemToPackResponseDto;
import bg.sofia.uni.fmi.tdkirov.trippacker.service.ItemToPackService;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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
@RequestMapping("/api/item-to-pack")
public class ItemToPackController {
    private ItemToPackService service;

    @PostMapping
    public ResponseEntity createItem(@NotNull @RequestBody ItemToPackCreateDto itemToPackDto,
                                     Principal principal) {
        Long id = service.createItem(itemToPackDto, principal.getName());

        URI location = URI.create("/api/item-to-pack/" + id);

        return ResponseEntity.created(location).build();
    }

    @GetMapping("/group/{groupId}")
    public ResponseEntity<Set<ItemToPackResponseDto>> getItemsByGroupId(@NotNull @PathVariable Long groupId,
                                                                        Principal principal) {
        var items = service.getItemsByGroupId(groupId, principal.getName());

        return ResponseEntity.ok(items);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteItemById(@NotNull @PathVariable Long id,
                                         Principal principal) {
        service.deleteItemById(id, principal.getName());

        return ResponseEntity.ok("Successfully deleted item with id " + id + ". ");
    }
}
