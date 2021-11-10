package ru.geekbrains.context;

import org.springframework.stereotype.Component;

@Component
public interface ProductRepository {
    Product findById(Long id);
}
