package hr.tis.praksa.file;

import hr.tis.praksa.model.Product;
import hr.tis.praksa.model.ProductsMetadata;

import java.io.BufferedWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.time.LocalDate;
import java.util.ArrayList;

public class ProductWriter {
    public static void writeProducts(ProductsMetadata productsMetadata){
        String fileName = "";
        fileName = String.format("%s_%d_%s.txt",
                productsMetadata.getDatumKreiranja().toString(),
                productsMetadata.getId(),
                productsMetadata.getNaslov());

        try (BufferedWriter writer = Files.newBufferedWriter(
                FileSystemConfiguration.PRODUCTS_FILES_FOLDER_PATH.resolve(fileName))
        ) {
            StringBuilder outputString = new StringBuilder();

            for (Product product : productsMetadata.getProducts()) {
                outputString.append(
                String.format("%-100s%10s%-10s%1d\n",
                        product.getName(),
                        product.getCijena().toString(),
                        product.getMjernaJedinica(),
                        product.getOcjena())
                );
            }
            writer.write(outputString.toString());

            // TODO pisanje proizvoda u datoteku
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    };

    public static void main(String[] args) {
        ProductsMetadata productMetadata = new ProductsMetadata(1L, LocalDate.now(), "title", new ArrayList<>());
        Product product1 = new Product("jabuka", new BigDecimal("1.5"), "kom", 5);
        Product product2 = new Product("lizalica", new BigDecimal("0.6"), "kom", 2);

        productMetadata.getProducts().add(product1);
        productMetadata.getProducts().add(product2);
        productMetadata.setDatumKreiranja(LocalDate.now());

        System.out.println("\n");

        writeProducts(productMetadata);

    }


}
