package com.example.spa.service;

import com.example.spa.domain.Pds;
import com.example.spa.domain.PdsFile;
import com.example.spa.repository.PdsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PdsServiceImpl implements PdsService{
    private final PdsRepository repository;

    @Override
    public void register(Pds pds) throws Exception {
        Pds pdsEntity = new Pds();

        pdsEntity.setItemName(pds.getItemName());
        pdsEntity.setDescription(pds.getDescription());

        String[] files = pdsEntity.getFiles();

        if(files == null){
            return;
        }

        for(String fileName : files){
            PdsFile pdsFile = new PdsFile();
            pdsFile.setFullName(fileName);

            pdsEntity.addItemFile(pdsFile);
        }

        repository.save(pdsEntity);

        pds.setItemId(pdsEntity.getItemId());
    }

    @Override
    public List<Pds> list() throws Exception {
     return repository.findAll(Sort.by(Sort.Direction.DESC, "itemId"));
    }
}
