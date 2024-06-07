package hr.tis.praksa.repository;

import hr.tis.praksa.file.FileSystemConfiguration;
import hr.tis.praksa.model.Product;
import hr.tis.praksa.model.ProductsMetadata;

import java.io.File;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class ProductRepositoryFile implements ProductRepository {



    @Override
    public Long insertProducts(ProductsMetadata ProductsMetadata) {

        Long productId = (long) fetchProductsMetadataCount() + 1 ;
        ProductsMetadata.setId(productId);
        return ProductsMetadata.getId();
    }

    @Override
    public BigDecimal fetchSumOfPrices(LocalDate createdDate) {
        return null;
    }

    @Override
    public BigDecimal fetchSumOfPrices(Long id) {
        return null;
    }

    @Override
    public ProductsMetadata fetchProductsMetadata(LocalDate createdDate) {
        return null;
    }

    @Override
    public ProductsMetadata fetchProductsMetadata(Long id) {
        return null;
    }

    @Override
    public Integer fetchProductsMetadataCount() {
        File directory =
                FileSystemConfiguration.PRODUCTS_FILES_FOLDER_PATH.toFile();

        int count = 0;
        if (directory.exists() && directory.isDirectory()) {
            File[] files = directory.listFiles();

            if (files == null){
                return 0;
            }

            for (File file : files) {
                if (file.isFile()){
                    count++;
                }
            }
        }

        return count;
    }

    @Override
    public BigDecimal calculateSumOfPrices(List<Product> products) {
        return null;
    }
}
