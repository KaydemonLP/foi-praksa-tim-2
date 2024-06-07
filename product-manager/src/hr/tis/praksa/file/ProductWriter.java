package hr.tis.praksa.file;

import hr.tis.praksa.model.Product;
import hr.tis.praksa.model.ProductsMetadata;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;

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
                String.format("%s %s %s %d\n",
                        product.getName(),
                        product.getCijena().toString(),
                        product.getMjernaJedinica(),
                        product.getOcjena());
            }

            // TODO pisanje proizvoda u datoteku
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    };


}
