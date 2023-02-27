package com.anet.chcrud.service;

import com.anet.chcrud.model.DataTable;
import com.anet.chcrud.repository.CustomDataTableRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DataTableService implements Serializable {

    public static DataTableService instance;

    @PostConstruct
    public void setup(){
        instance = this;
    }

    private final CustomDataTableRepositoryImpl customDataTableRepository;

    public List<DataTable> findAll() {
        return customDataTableRepository.findAll();
    }

    public List<DataTable> insertQuery() { return customDataTableRepository.insertQuery(); }

    public List<DataTable> materializedView() { return customDataTableRepository.materializedView(); }
}
