import java.math.BigDecimal;
import java.util.Currency;

public class Product {
    private String name;
    private BigDecimal cijena;
    private String mjernaJedinica;
    private int ocjena; //u zvjezdicama


    public Product() {

    }

    public Product(String name, BigDecimal cijena, String mjernaJedinica, int ocjena) {
        this.name = name;
        this.cijena = cijena;
        this.mjernaJedinica = mjernaJedinica;
        this.ocjena = ocjena;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getCijena() {
        return cijena;
    }

    public void setCijena(BigDecimal cijena) {
        this.cijena = cijena;
    }

    public String getMjernaJedinica() {
        return mjernaJedinica;
    }

    public void setMjernaJedinica(String mjernaJedinica) {
        this.mjernaJedinica = mjernaJedinica;
    }

    public int getOcjena() {
        return ocjena;
    }

    public void setOcjena(int ocjena) {
        this.ocjena = ocjena;
    }

}