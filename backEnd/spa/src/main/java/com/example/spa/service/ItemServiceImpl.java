package com.example.spa.service;

import com.example.spa.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService{
    private final ItemRepository repository;
}
