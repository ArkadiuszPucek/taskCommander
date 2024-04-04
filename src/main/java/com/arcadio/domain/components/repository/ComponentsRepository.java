package com.arcadio.domain.components.repository;

import com.arcadio.domain.components.model.Components;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComponentsRepository extends CrudRepository<Components, String> {
    List<Components> findByCategory(String category);
    List<Components> findAll();

    @Query("SELECT DISTINCT c.category FROM Components c")
    List<String> findAllCategories();
}
