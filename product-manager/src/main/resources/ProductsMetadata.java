import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ProductsMetadata {
    private Long id;
    private LocalDate datumKreiranja;
    private String naslov;
    private List<Product> products;

    public ProductsMetadata() {
        products = new ArrayList<>();
    }

    public ProductsMetadata(Long id, LocalDate datumKreiranja, String naslov, List<Product> products) {
        this.id = id;
        this.datumKreiranja = datumKreiranja;
        this.naslov = naslov;
        this.products = products;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDatumKreiranja() {
        return datumKreiranja;
    }

    public void setDatumKreiranja(LocalDate datumKreiranja) {
        this.datumKreiranja = datumKreiranja;
    }

    public String getNaslov() {
        return naslov;
    }

    public void setNaslov(String naslov) {
        this.naslov = naslov;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "ProductsMetadata{" +
                "id=" + id +
                ", datumKreiranja=" + datumKreiranja +
                ", naslov='" + naslov + '\'' +
                ", products=" + products +
                '}';
    }

    public static void main(String[] args) {
        ProductsMetadata productMetadata = new ProductsMetadata();
        Product product1 = new Product();
        Product product2 = new Product();
        productMetadata.products.add(product1);
        productMetadata.products.add(product2);

    }
}