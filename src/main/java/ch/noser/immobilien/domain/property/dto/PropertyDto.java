package ch.noser.immobilien.domain.property.dto;

import ch.noser.immobilien.core.AlphabetHyphen.AlphabetHyphen;
import ch.noser.immobilien.core.AlphabetNumb.AlphabetNumb;
import jakarta.validation.constraints.Min;
import org.hibernate.validator.constraints.Range;

public class PropertyDto {

    @AlphabetHyphen
    private String canton;

    @AlphabetNumb
    private String name;
    @Range(min = 500, max = 4500)
    private int price;

    @Min(1)
    private int size;
    @ch.noser.immobilien.core.Url.Url
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

