package com.arcadio.domain.components.service;

import com.arcadio.domain.components.model.Components;
import com.arcadio.domain.components.repository.ComponentsRepository;
import org.springframework.stereotype.Service;

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
}
