package com.arcadio.domain.components.service;

import com.arcadio.domain.components.model.Components;
import com.arcadio.domain.components.repository.ComponentsRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class ComponentsService {

    private final ComponentsRepository componentsRepository;

    public ComponentsService(ComponentsRepository componentsRepository) {
        this.componentsRepository = componentsRepository;
    }
    public List<Components> getComponentsByCategory(String category) {
        return componentsRepository.findByCategory(category);
    }

    public List<Components> getAllComponents() {
        return componentsRepository.findAll();
    }

    public List<String> getAllCategories() {
        return componentsRepository.findAllCategories();
    }

    public boolean shouldShowVariants(String category) {
        List<String> excludedCategories = Arrays.asList("Rury", "Rolki", "Prowadnice", "Koła", "Akcesoria", "Śruby");
        return !excludedCategories.contains(category);
    }
}
