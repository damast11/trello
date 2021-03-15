package com.gmail.dkylish.persistance;

import com.gmail.dkylish.entity.Columns;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ColumnRepository extends JpaRepository<Columns, String> {

    Columns findColumnsById(Long id);

}
