package com.example.spa.service;

import com.example.spa.domain.Item;
import com.example.spa.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService{
    private final ItemRepository repository;

    @Override
    public void register(Item item) throws Exception {
        repository.save(item);
    }

    @Override
    public List<Item> list() throws Exception {
        return repository.findAll(Sort.by(Sort.Direction.DESC,"itemId"));
    }

    @Override
    public Item read(Long itemId) throws Exception {
        return repository.getById(itemId);
    }

    @Override
    public void modify(Item item) throws Exception {
        Item itemEntity = repository.getById(item.getItemId());

        itemEntity.setItemName(item.getItemName());
        itemEntity.setPrice(item.getPrice());
        itemEntity.setDescription(item.getDescription());
        itemEntity.setPictureUrl(item.getPictureUrl());
        itemEntity.setPreviewUrl(item.getPreviewUrl());
        repository.save(itemEntity);
    }

    @Override
    public void remove(Long itemId) throws Exception {
        repository.deleteById(itemId);
    }

    @Override
    public String getPicture(Long itemId) throws Exception {
        Item item = repository.getById(itemId);
        return item.getPictureUrl();
    }

    @Override
    public String getPreview(Long itemId) throws Exception {
        Item item = repository.getById(itemId);
        return item.getPreviewUrl();
    }
}
