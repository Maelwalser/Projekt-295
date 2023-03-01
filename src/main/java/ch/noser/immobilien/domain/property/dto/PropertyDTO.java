package ch.noser.immobilien.domain.property.dto;
import ch.noser.immobilien.core.AlphabetHyphen.AlphabetHyphen;
import ch.noser.immobilien.core.AlphabetNumb.AlphabetNumb;
import ch.noser.immobilien.core.URL.URL;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Range;

public class PropertyDTO {

    @AlphabetHyphen
    private String canton;

    @AlphabetNumb
    private String name;
    @Range(min = 500, max = 4500)
    private int price;

    @NotNull
    private int size;
    @URL
    private String Url;



    public String getCanton() {
        return canton;
    }

    public void setCanton(String canton) {
        this.canton = canton;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }


    }

