package hr.tis.praksa.repository;

import hr.tis.praksa.model.Product;
import hr.tis.praksa.model.ProductsMetadata;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ProductRepositoryInMemory implements ProductRepository {

    static final List<ProductsMetadata> productsMetadataList = new ArrayList<>();

    @Override
    public Long insertProducts(ProductsMetadata ProductsMetadata) {
        Long productId = (long) productsMetadataList.size();
        productsMetadataList.add(ProductsMetadata);
        ProductsMetadata.setId(productId);
        return ProductsMetadata.getId();
    }

    @Override
    public BigDecimal fetchSumOfPrices(LocalDate createdDate) {
        BigDecimal sumOfPrices = new BigDecimal(0);
        ProductsMetadata metadata = fetchProductsMetadata(createdDate);
        for (Product product : metadata.getProducts()) {
            sumOfPrices = sumOfPrices.add(product.getCijena());
        }

        return sumOfPrices;
    }

    @Override
    public BigDecimal fetchSumOfPrices(Long id) {
        BigDecimal sumOfPrices = new BigDecimal(0);
        ProductsMetadata metadata = fetchProductsMetadata(id);
        for (Product product : metadata.getProducts()) {
            sumOfPrices = sumOfPrices.add(product.getCijena());
        }

        return sumOfPrices;    }

    @Override
    public ProductsMetadata fetchProductsMetadata(LocalDate createdDate) {
        for (ProductsMetadata data : productsMetadataList) {
            if (data.getDatumKreiranja()==createdDate) {
                return data;
            }
        }
        throw new RuntimeException("Record doesn't exist.");
    }

    @Override
    public ProductsMetadata fetchProductsMetadata(Long id) {
        for (ProductsMetadata data : productsMetadataList) {
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
        return null;
    }

    public static void main(String[] args) {
        ProductsMetadata productMetadata = new ProductsMetadata();
        Product product1 = new Product("jabuka", new BigDecimal("1.5"), "kom", 5);
        Product product2 = new Product("lizalica", new BigDecimal("0.6"), "kom", 2);

        productMetadata.getProducts().add(product1);
        productMetadata.getProducts().add(product2);
        LocalDate date = LocalDate.now();
        productMetadata.setDatumKreiranja(date);

        System.out.println("\n");
        ProductRepositoryInMemory repository = new ProductRepositoryInMemory();
        repository.insertProducts(productMetadata);
        BigDecimal sumOfPrices = repository.fetchSumOfPrices(date);
        System.out.println(sumOfPrices);
    }
}
