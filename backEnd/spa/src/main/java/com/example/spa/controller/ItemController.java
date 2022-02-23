package com.example.spa.controller;

import com.example.spa.domain.Item;
import com.example.spa.prop.ShopProperties;
import com.example.spa.service.ItemService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/items")
public class ItemController {
    private final ShopProperties shopProperties;
    private final ItemService itemService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<Item> register(@RequestPart("item") String itemString,
                                         @RequestPart("file")MultipartFile originalImageFile,
                                         @RequestPart("file2")MultipartFile previewImageFile) throws Exception{
        Item item = new ObjectMapper().readValue(itemString, Item.class);

        
    }
}
