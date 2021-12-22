package ru.geekbrains;

import org.springframework.beans.factory.annotation.Autowired;
import ru.geekbrains.persist.Product;
import ru.geekbrains.persist.ProductRepository;

import java.util.HashMap;
import java.util.Map;

public class CartService {

    @Autowired
    private ProductRepository productRepository;

    private final Map<Long, Long> products; // idProduct, amount

    public CartService() {
        this.products = new HashMap<>();
    }

    public boolean put(Long productId) {
        final Product product = productRepository.findById(productId);
        if (product != null) {
            products.merge(productId, 1L, Long::sum);
            return true;
        } else {
            return false;
        }
    }

    public boolean remove(Long productId) {
        final Product productFromRepo = productRepository.findById(productId);
        final Long productFromCart = products.get(productId);
        if (productFromRepo != null && productFromCart != null) {
            final Long amount = products.get(productId);
            if (amount == 1) {
                products.remove(productId);
            } else {
                products.put(productId, amount - 1);
            }
            return true;
        } else {
            return false;
        }
    }


    public boolean isEmpty() {
        return products.isEmpty();
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "Your cart is empty :(";
        }
        final StringBuilder sb = new StringBuilder();
        sb.append(String.format("%4s | %10s | %5s | %2s%n","id", "name", "price", "amount"));
        products.forEach((key, value) -> {
            final Product p = productRepository.findById(key);
            sb.append(String.format("%4s | %10s | %5s | %2s%n", key, p.getName(), p.getPrice(), value));
        });
        return sb.toString();
    }
}
