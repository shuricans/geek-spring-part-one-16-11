package ru.geekbrains;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.geekbrains.dao.ProductDao;
import ru.geekbrains.dao.UserDao;
import ru.geekbrains.persist.Customer;
import ru.geekbrains.persist.Product;
import ru.geekbrains.service.CustomerService;

import java.math.BigDecimal;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        final ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);

        final UserDao userDao = context.getBean(UserDao.class);
        final ProductDao productDao = context.getBean(ProductDao.class);
        final CustomerService customerService = context.getBean(CustomerService.class);

        List<Customer> customers = customerService.getCustomersByProductId(1L);
        System.out.println(customers);
        // assert equals:
        // Customer(id=1, user=User(id=1, name=Hendrika Bonde))
        // Customer(id=2, user=User(id=2, name=Had Fateley))
        // Customer(id=3, user=User(id=3, name=Mateo McCabe))

        // change price first product, named "Chicken - Whole Fryers"
        productDao.findById(1L)
                .ifPresent(product -> {
                    product.setPrice(new BigDecimal(100));
                    productDao.save(product);
                });
        productDao.findById(1L).ifPresent(System.out::println);
        // assert equals:
        // Product(id=1, name=Chicken - Whole Fryers, price=100.00)

        List<Product> products = customerService.getProductsByCustomerId(1L);
        System.out.println(products);
        // assert equals:
        // Product(id=1, name=Chicken - Whole Fryers, price=895.00)
        // Product(id=2, name=Grouper - Fresh, price=373.00)
        // Product(id=3, name=Wine - Ej Gallo Sonoma, price=269.00)
    }
}
