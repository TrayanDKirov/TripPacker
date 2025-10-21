package bg.sofia.uni.fmi.tdkirov.trippacker.controller;

import bg.sofia.uni.fmi.tdkirov.trippacker.dto.packingitem.PackingItemCreateDto;
import bg.sofia.uni.fmi.tdkirov.trippacker.dto.packingitem.PackingItemUpdateDto;
import bg.sofia.uni.fmi.tdkirov.trippacker.service.PackingItemService;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.security.Principal;

@AllArgsConstructor
@RestController
@RequestMapping("/api/packing-item")
public class PackingItemController {
    private PackingItemService packingItemService;

    @PostMapping
    public ResponseEntity createItem(@NotNull @RequestBody PackingItemCreateDto packingItemDto,
                                     Principal principal) {
        Long id = packingItemService.createItem(packingItemDto, principal.getName());

        URI location = URI.create("/api/packing-item/" + id);

        return ResponseEntity.created(location).build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity updateItemById(@NotNull @PathVariable Long id,
                                         @NotNull @RequestBody PackingItemUpdateDto packingItemDto,
                                         Principal principal) {
        packingItemService.updatePackingItemById(id, packingItemDto, principal.getName());

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteItem(@NotNull @PathVariable Long id,
                                     Principal principal) {
        packingItemService.deleteItemById(id, principal.getName());

        return ResponseEntity.ok().build();
    }
}
