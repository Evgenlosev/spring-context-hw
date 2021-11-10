package ru.geekbrains.context;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;
import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("ru.geekbrains.context");
        Cart cart = context.getBean(Cart.class);
        ProductRepository productRepository = context.getBean(ProductRepository.class);
        System.out.println("Для добавления товара в корзину введите команду add + id товара(пример: add 2)");
        System.out.println("Для удаления товара из корзины введите команду del + id товара(пример: del 3)");
        System.out.println("Для получения списка товаров в корзине введите команду show");
        System.out.println("Для выхода введите exit");
        productRepository.showListOfProducts();
        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine();
        String[] arrayCommand = new String[2];
        arrayCommand = command.split(" ");
        while (!arrayCommand[0].equals("exit")) {
            try {
                if (arrayCommand.length > 2) {
                    throw new ArrayIndexOutOfBoundsException();
                }
                switch (arrayCommand[0]) {
                    case "add":
                        cart.addProductToCartById((long) Integer.parseInt(arrayCommand[1]));
                        break;
                    case "del":
                        cart.removeProductFromCartById((long) Integer.parseInt(arrayCommand[1]));
                        break;
                    case "show":
                        cart.showCart();
                        break;
                    default:
                        System.out.println("Введена некорректная команда, повторите ввод:");
                        break;
                }
            } catch (ArrayIndexOutOfBoundsException | NumberFormatException  exception) {
                System.out.println("Введена некорректная команда, повторите ввод:");
            } catch (RuntimeException e) {
                System.out.println("Товара с id " + arrayCommand[1] + " не существует. Повторите ввод:");
            }
            command = scanner.nextLine();
            arrayCommand = command.split(" ");
        }
        scanner.close();
        context.close();
    }
}
