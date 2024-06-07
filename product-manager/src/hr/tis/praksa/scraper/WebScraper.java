package hr.tis.praksa.scraper;

import hr.tis.praksa.model.Product;
import hr.tis.praksa.model.ProductsMetadata;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class WebScraper {
    private String BASE_WEBSITE = "https://www.konzum.hr";
    private String PRODUCT_WEBSITE = BASE_WEBSITE + "/web/posebne-ponude";
    private String EMULATED_USER_AGENT = "Mozilla";

    public ProductsMetadata fetchProducts() {
        ProductsMetadata ret = new ProductsMetadata();
        ret.setDatumKreiranja(LocalDate.now());
        try {
            Document doc = Jsoup.connect(PRODUCT_WEBSITE).userAgent(EMULATED_USER_AGENT).get();
            ret.setNaslov(doc.title());

            Elements articles = doc.select("article");
            for( Element article : articles ){
                Element linkElement = article.select(".link-to-product").first();

                if( linkElement == null )
                    continue;

                Attribute href = linkElement.attribute("href");
                String link = BASE_WEBSITE + href.getValue();
                Product product = this.fetchProduct(link);

                if( product == null )
                    continue;

                ret.getProducts().add(product);
            }
        }
        catch (java.io.IOException e) {

        }

        return ret;
    }

    private Product fetchProduct(String link){
        Product ret = new Product();
        try {
            Document doc = Jsoup.connect(link).userAgent(EMULATED_USER_AGENT).get();

            ret.setName(doc.title());
            Element priceContainer = doc.select("price").first();
            if( priceContainer != null )
            {
                Integer eurPrice = Integer.valueOf(GetPriceValue(priceContainer, "price--kn"));
                Integer centPrice = Integer.valueOf(GetPriceValue(priceContainer, "price--li"));
                String kom = GetPriceValue(priceContainer, "price--c");

                String fullPrice = String.format("%s.%s", eurPrice, centPrice);

                ret.setCijena( new BigDecimal(fullPrice) );
                ret.setMjernaJedinica(kom);
            }

            Element ratingElement = doc.select("rating").first();
            Integer rating = Integer.valueOf("0");
            if( ratingElement != null )
            {
                String unformatedRating = ratingElement.select("span").last().html();
                unformatedRating = unformatedRating.substring(1, unformatedRating.length()-2);
                rating = Integer.valueOf(unformatedRating);
            }

            ret.setOcjena(rating);

            return ret;
        }
        catch (java.io.IOException e) {

        }

        return null;
    }

    private String GetPriceValue(Element priceContainer, String name) {
        Element eurElem = priceContainer.select(name).first();
        if( eurElem != null )
        {
            return eurElem.html();
        }

        return null;
    }

    public static void main(String[] args) {
        System.out.println(new WebScraper().fetchProducts());
    }
}
