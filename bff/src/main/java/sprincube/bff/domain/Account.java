package sprincube.bff.domain;

public class Account {
    Long id;
    String name;
    Double number;
    Double ducats;

    public Account(){}

    public Account(Long id, String name, Double number, Double ducats){
        this.id = id;
        this.name = name;
        this.number = number;
        this.ducats = ducats;
    }

    public Long getId() {
        return id;
    }

    public Account setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Account setName(String name) {
        this.name = name;
        return this;
    }

    public Double getNumber() {
        return number;
    }

    public Account setNumber(Double number) {
        this.number = number;
        return this;
    }

    public Double getDucats() {
        return ducats;
    }

    public Account setDucats(Double ducats) {
        this.ducats = ducats;
        return this;
    }
}
