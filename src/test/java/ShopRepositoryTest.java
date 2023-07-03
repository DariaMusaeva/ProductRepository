import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.AlreadyExistsException;
import ru.netology.NotFoundException;
import ru.netology.Product;
import ru.netology.ShopRepository;

public class ShopRepositoryTest {

    ShopRepository repo = new ShopRepository();
    Product product1 = new Product(12, "Book", 499);
    Product product2 = new Product(22, "Chair", 23_199);
    Product product3 = new Product(55, "Skirt", 5_000);

    @Test
    public void removeWhenProductExist() {

        repo.add(product1);
        repo.add(product2);
        repo.add(product3);
        repo.remove(55);

        Assertions.assertArrayEquals(new Product[]{product1, product2}, repo.findAll());
    }

    @Test
    public void removeWhenProductIsNotExist() {

        repo.add(product1);
        repo.add(product2);
        repo.add(product3);

        Assertions.assertThrows(NotFoundException.class, () -> repo.remove(44));
    }

    @Test
    public void shouldAddNewProduct() {

        Product product4 = new Product(56, "Cup", 500);

        repo.add(product1);
        repo.add(product2);
        repo.add(product3);
        repo.add(product4);

        Assertions.assertArrayEquals(new Product[]{product1, product2, product3, product4}, repo.findAll());
    }

    @Test
    public void ShouldNotAddNewProduct() {

        Product product5 = new Product(12, "Fork", 166);

        repo.add(product1);
        repo.add(product2);
        repo.add(product3);

        Assertions.assertThrows(AlreadyExistsException.class, () -> repo.add(product5));
    }
}
