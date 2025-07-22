package bg.sofia.uni.fmi.tdkirov.trippacker.controller;

import bg.sofia.uni.fmi.tdkirov.trippacker.dto.itemtopack.ItemToPackCreateDto;
import bg.sofia.uni.fmi.tdkirov.trippacker.service.ItemToPackService;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@AllArgsConstructor
@RestController
@RequestMapping("/api/item-to-pack")
public class ItemToPackController {
    private ItemToPackService service;

    @PostMapping
    public ResponseEntity createItem(@NotNull @RequestBody ItemToPackCreateDto itemToPackDto) {
        Long id = service.createItem(itemToPackDto, "");

        URI location = URI.create("/api/item-to-pack/" + id);

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteItemById(@NotNull @PathVariable Long id) {
        service.deleteItemById(id, "");

        return ResponseEntity.ok().build();
    }
}
