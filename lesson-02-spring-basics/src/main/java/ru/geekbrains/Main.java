package ru.geekbrains;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.geekbrains.persist.Product;
import ru.geekbrains.persist.ProductRepository;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        final ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        final ProductRepository productRepository = context.getBean("productRepository", ProductRepository.class);

        System.out.println("Welcome to our store.\nFirst time? Type help.");

        final Scanner scanner = new Scanner(System.in);
        CartService cart = context.getBean("cartService", CartService.class);
        loop:
        while (true) {
            final String input = scanner.nextLine();

            // handle events 'take' or 'remove'
            if (input.matches("^[tr] \\d*$")) {
                final long id = Long.parseLong(input.replaceFirst("\\w\\s", ""));
                final Product product = productRepository.findById(id);
                if (product == null) {
                    System.out.printf("Product with id = %d does not exist.%nTry again.%n", id);
                    continue;
                } else {
                    switch (input.charAt(0)) {
                        case 't':
                            if (cart.put(id)) {
                                System.out.print(cart);
                                System.out.println("added successfully");
                            } else {
                                System.out.println("could not be added...");
                            }
                            break;
                        case 'r':
                            if (cart.remove(id)) {
                                System.out.print(cart);
                                System.out.println("removed successfully");
                            } else {
                                System.out.println("could not be removed...");
                            }
                    }
                }
                continue;
            }
            // handle over inputs
            switch (input.toLowerCase()) {
                case "q":
                    System.out.println("Bye...");
                    break loop;
                case "help":
                    printHelp();
                    break;
                case "pl":
                    System.out.println("Available products:");
                    productRepository.findAll().forEach(System.out::println);
                    break;
                case "cl":
                    System.out.println(cart);
                    break;
                case "nc":
                    cart = context.getBean("cartService", CartService.class);
                    System.out.println("bye bye old cart, keep new :)");
                    System.out.println(cart);
                    break;
                default:
                    System.out.println("Confused? Type help.");
            }
        }
    }

    private static void printHelp() {
        System.out.println("Available commands list:");
        System.out.printf("[%25s] - q%n", "quit");
        System.out.printf("[%25s] - t [id]%n", "take product");
        System.out.printf("[%25s] - r [id]%n", "remove product");
        System.out.printf("[%25s] - nc%n", "take a new cart");
        System.out.printf("[%25s] - pl%n", "available products list");
        System.out.printf("[%25s] - cl%n", "cart list");
    }
}
