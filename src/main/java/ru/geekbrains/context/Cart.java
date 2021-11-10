package ru.geekbrains.context;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
@Scope("prototype")
public class Cart {
    private List<Product> cart;
    private ProductRepository productRepository;

    @PostConstruct
    public void init() {
        cart = new ArrayList<>();
    }

    public ProductRepository getProductRepository() {
        return productRepository;
    }

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getCart() {
        return cart;
    }

    public void addProductToCartById(Long id) {
        cart.add(productRepository.findById(id));
        System.out.println("Продукт " + productRepository.findById(id).getTitle() + " добавлен в корзину.");
    }

    public void removeProductFromCartById(Long id) {
        if (cart.contains(productRepository.findById(id))) {
            cart.remove(productRepository.findById(id));
            System.out.println("Продукт " + productRepository.findById(id).getTitle() + " удален из корзины.");
        } else {
            System.out.println("Продукта " + productRepository.findById(id).getTitle() + " нет в корзине.");
        }
    }

    public void showCart() {
        if (cart.size() > 0) {
            System.out.println("Товары в корзине:");
            for (Product p : cart) {
                System.out.println(p.toString());
            }
        } else {
            System.out.println("Корзина пуста");
        }
    }
}
