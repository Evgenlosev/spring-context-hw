package ru.geekbrains.context;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class ProductRepository {
    private List<Product> products;

    @PostConstruct
    public void init() {
        products = new ArrayList<>(Arrays.asList(
                new Product(1L, "Bread", 30L),
                new Product(2L, "Milk", 55L),
                new Product(3L, "Apples", 120L),
                new Product(4L, "Coffee", 300L),
                new Product(5L, "Tea", 50L)
        ));
    }

    public Product findById(Long id) {
        return products.stream().filter(p -> p.getId().equals(id)).findFirst().orElseThrow(() -> new RuntimeException());
    }

    public void showListOfProducts() {
        System.out.println("Список товаров:");
        for (Product p : products) {
            System.out.println(p.toString());
        }
    }
}
