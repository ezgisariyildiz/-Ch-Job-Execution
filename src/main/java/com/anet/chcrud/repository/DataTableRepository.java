package com.anet.chcrud.repository;

import com.anet.chcrud.model.DataTable;

import java.util.List;

public interface DataTableRepository {

    List<DataTable> findAll();
}
