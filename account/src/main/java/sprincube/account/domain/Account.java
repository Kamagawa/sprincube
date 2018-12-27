package sprincube.account.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.io.Serializable;

/**
 * The type Account.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Account implements Serializable {
    /**
     * The Id.
     */
    Long id;
    /**
     * The Name.
     */
    String name;
    /**
     * The Number.
     */
    Double number;
    /**
     * The Ducats.
     */
    Double ducats;

    /**
     * Instantiates a new Account.
     */
    public Account(){}

    /**
     * Instantiates a new Account.
     *
     * @param id     the id
     * @param name   the name
     * @param number the number
     * @param ducats the ducats
     */
    public Account(Long id, String name, Double number, Double ducats){
        this.id = id;
        this.name = name;
        this.number = number;
        this.ducats = ducats;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     * @return the id
     */
    public Account setId(Long id) {
        this.id = id;
        return this;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     * @return the name
     */
    public Account setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Gets number.
     *
     * @return the number
     */
    public Double getNumber() {
        return number;
    }

    /**
     * Sets number.
     *
     * @param number the number
     * @return the number
     */
    public Account setNumber(Double number) {
        this.number = number;
        return this;
    }

    /**
     * Gets ducats.
     *
     * @return the ducats
     */
    public Double getDucats() {
        return ducats;
    }

    /**
     * Sets ducats.
     *
     * @param ducats the ducats
     * @return the ducats
     */
    public Account setDucats(Double ducats) {
        this.ducats = ducats;
        return this;
    }

    @Override
    public String toString() {
        return "\nAccount{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", number=" + number +
                ", ducats=" + ducats +
                "}";
    }
}
