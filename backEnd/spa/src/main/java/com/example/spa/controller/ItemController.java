package com.example.spa.controller;

import com.example.spa.domain.Item;
import com.example.spa.prop.ShopProperties;
import com.example.spa.service.ItemService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.UUID;

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

        String itemName =  item.getItemName();
        String description = item.getDescription();

        if(itemName != null){
            item.setItemName(itemName);
        }

        if(description!= null){
            item.setDescription(description);
        }

        item.setPicture(originalImageFile);
        item.setPreview(previewImageFile);

        MultipartFile pictureFile = item.getPicture();
        MultipartFile previewFile = item.getPreview();

        // 이미지 이름과 Byte 를 전달하여 저장위치 Url 반환
        String createPictureFilename = uploadFile(pictureFile.getOriginalFilename(), pictureFile.getBytes());
        String createPreviewFilename = uploadFile(previewFile.getOriginalFilename(), previewFile.getBytes());

        item.setPictureUrl(createPictureFilename);
        item.setPreviewUrl(createPreviewFilename);

        itemService.register(item);

        Item createItem = new Item();
        createItem.setItemId(item.getItemId());

        return new ResponseEntity<>(createItem, HttpStatus.OK);
    }

    private String uploadFile(String originalName, byte[] fileData) throws Exception{
        UUID uid = UUID.randomUUID();
        String createFileName = uid.toString() + "_" + originalName;
        File target = new File(shopProperties.getUploadPath(), createFileName);
        FileCopyUtils.copy(fileData, target);
        return createFileName;
    }

    @GetMapping
    public ResponseEntity<List<Item>> list() throws Exception{
        return new ResponseEntity<>(itemService.list(), HttpStatus.OK);
    }

    @GetMapping("/{itemId}")
    public ResponseEntity<Item> read(@PathVariable("itemId")Long itemId) throws Exception{
        Item item = itemService.read(itemId);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{itemId}")
    public ResponseEntity<Item> modify(@PathVariable("itemId")Long itemId, @RequestPart("item")String itemString,
                                       @RequestPart(name="file", required = false) MultipartFile originalImageFile,
                                       @RequestPart(name="file2", required = false) MultipartFile previewImageFile) throws Exception{

        Item item = new ObjectMapper().readValue(itemString, Item.class);
        item.setItemId(itemId);

        String itemName = item.getItemName();
        String description = item.getDescription();

        item.setPicture(originalImageFile);
        item.setPreview(previewImageFile);

        MultipartFile pictureFile = item.getPicture();

        if(pictureFile != null && pictureFile.getSize() > 0 ){
            String createFilename = uploadFile(pictureFile.getOriginalFilename(), pictureFile.getBytes());

            item.setPictureUrl(createFilename);
        }else{
            Item oldItem = this.itemService.read(itemId);
            item.setPictureUrl(oldItem.getPictureUrl());
        }

        MultipartFile previewFile = item.getPreview();

        if(previewFile != null && previewFile.getSize() > 0){
            String createFilename = uploadFile(previewFile.getOriginalFilename(), previewFile.getBytes());
            item.setPreviewUrl(createFilename);
        }
        else{
            Item oldItem = this.itemService.read(item.getItemId());
            item.setPreviewUrl(oldItem.getPreviewUrl());
        }

        itemService.modify(item);

        Item createdItem = new Item();
        createdItem.setItemId(item.getItemId());

        return new ResponseEntity<>(createdItem, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{itemId}")
    public ResponseEntity<Void> remove(@PathVariable("itemId")Long itemId) throws Exception{
        itemService.remove(itemId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/display")
    public ResponseEntity<byte[]> displayFile(@RequestPart("itemId") Long itemId) throws Exception{
        ResponseEntity<byte[]> entity = null;
        String fileName = itemService.getPicture(itemId);

        try{
            String formatName = fileName.substring(fileName.lastIndexOf(".") + 1);
            MediaType mediaType = getMediaType(formatName);

            HttpHeaders headers = new HttpHeaders();

            File file = new File(shopProperties.getUploadPath() + File.separator + fileName);

            if(mediaType != null){
                headers.setContentType(mediaType);
            }

            entity = new ResponseEntity<byte[]>(FileCopyUtils.copyToByteArray(file), headers, HttpStatus.CREATED);
        }catch (Exception e){
            e.printStackTrace();
            entity = new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
        }
        return entity;
    }

    private MediaType getMediaType(String formatName){
        if(formatName != null){
            if(formatName.equals("JPG")){
                return MediaType.IMAGE_JPEG;
            }

            if(formatName.equals("GIF")){
                return MediaType.IMAGE_GIF;
            }

            if(formatName.equals("PNG")){
                return MediaType.IMAGE_PNG;
            }
        }
        return null;
    }

    @GetMapping("/preview")
    public ResponseEntity<byte[]> previewFile(@RequestParam("itemId") Long itemId) throws Exception{
        ResponseEntity<byte[]> entity = null;

        String fileName = itemService.getPreview(itemId);

        try{
            String formatName = fileName.substring(fileName.lastIndexOf(".") + 1);
            MediaType mediaType = getMediaType(formatName);

            HttpHeaders headers = new HttpHeaders();

            File file = new File(shopProperties.getUploadPath() + File.separator + fileName);

            if(mediaType != null){
                headers.setContentType(mediaType);
            }

            entity = new ResponseEntity<byte[]>(FileCopyUtils.copyToByteArray(file), headers, HttpStatus.CREATED);
        }catch (Exception e){
            e.printStackTrace();
            entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return entity;
    }
}
