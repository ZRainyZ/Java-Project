package models;

import javafx.beans.property.SimpleStringProperty;

public class FoodBean {
    private SimpleStringProperty name;
    private SimpleStringProperty type;
    private SimpleStringProperty quantifying;
    private SimpleStringProperty exp;
    private SimpleStringProperty dayin;
    private SimpleStringProperty expired;

    public FoodBean(Food food){
        this.name = new SimpleStringProperty(food.getFoodName());
        this.type = new SimpleStringProperty(food.getFoodType());
        this.quantifying = new SimpleStringProperty(food.getQuantifying());
        this.dayin = new SimpleStringProperty(""+food.dayInRefridgerator());
        this.exp = new SimpleStringProperty(food.getExpire());
        this.expired = new SimpleStringProperty(""+food.dayExpired());
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public String getType() {
        return type.get();
    }

    public SimpleStringProperty typeProperty() {
        return type;
    }

    public String getQuantifying() {
        return quantifying.get();
    }

    public SimpleStringProperty quantifyingProperty() {
        return quantifying;
    }

    public String getExp() {
        return exp.get();
    }

    public SimpleStringProperty expProperty() {
        return exp;
    }

    public String getDayin() {
        return dayin.get();
    }

    public SimpleStringProperty dayinProperty() {
        return dayin;
    }

    public String getExpired() {
        return expired.get();
    }
}
