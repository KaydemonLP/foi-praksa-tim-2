package hr.tis.praksa.repository;

import hr.tis.praksa.model.Product;
import hr.tis.praksa.model.ProductsMetadata;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ProductRepositoryInMemory implements ProductRepository {

    private static final List<ProductsMetadata> productsMetadataList = new ArrayList<>();

    @Override
    public Long insertProducts(ProductsMetadata ProductsMetadata) {
        Long productId = (long) productsMetadataList.size();
        productsMetadataList.add(ProductsMetadata);
        ProductsMetadata.setId(productId);
        return ProductsMetadata.getId();
    }

    @Override
    public BigDecimal fetchSumOfPrices(LocalDate createdDate) {
        BigDecimal sumOfPrices = BigDecimal.ZERO;
        ProductsMetadata metadata = fetchProductsMetadata(createdDate);
        for (Product product : metadata.getProducts()) {
            sumOfPrices = sumOfPrices.add(product.getCijena());
        }

        return sumOfPrices;
    }

    @Override
    public BigDecimal fetchSumOfPrices(Long id) {
        BigDecimal sumOfPrices = BigDecimal.ZERO;
        ProductsMetadata metadata = fetchProductsMetadata(id);
        for (Product product : metadata.getProducts()) {
            sumOfPrices = sumOfPrices.add(product.getCijena());
        }

        return sumOfPrices;    }

    @Override
    public ProductsMetadata fetchProductsMetadata(LocalDate createdDate) {
        for (int i = productsMetadataList.size()-1; i >= 0; i--) {
            ProductsMetadata data = productsMetadataList.get(i);
            if (data.getDatumKreiranja().equals(createdDate)) {
                return data;
            }
        }
        throw new RuntimeException("Record doesn't exist.");
    }

    @Override
    public ProductsMetadata fetchProductsMetadata(Long id) {
        for (int i = productsMetadataList.size()-1; i >= 0; i--) {
            ProductsMetadata data = productsMetadataList.get(i);
            if (data.getId().equals(id)) {
                return data;
            }
        }
        throw new RuntimeException("Record doesn't exist.");
    }

    @Override
    public Integer fetchProductsMetadataCount() {
        return productsMetadataList.size();
    }

    @Override
    public BigDecimal calculateSumOfPrices(List<Product> products) {
        BigDecimal sumOfPrices = new BigDecimal(0);
        for (Product product : products) {
           sumOfPrices = sumOfPrices.add(product.getCijena());

        }
        return sumOfPrices;
    }

    public static void main(String[] args) {
        ProductsMetadata productMetadata = new ProductsMetadata();
        Product product1 = new Product("jabuka", new BigDecimal("1.5"), "kom", 5);
        Product product2 = new Product("lizalica", new BigDecimal("0.6"), "kom", 2);

        productMetadata.getProducts().add(product1);
        productMetadata.getProducts().add(product2);
        productMetadata.setDatumKreiranja(LocalDate.now());

        System.out.println("\n");
        ProductRepositoryInMemory repository = new ProductRepositoryInMemory();
        repository.insertProducts(productMetadata);
        BigDecimal sumOfPrices = repository.fetchSumOfPrices(LocalDate.now());
        System.out.println(sumOfPrices);
    }
}
