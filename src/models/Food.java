package models;

import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.DAYS;

public class Food implements Comparable{
    private String foodName,foodType,quantifying,expire,slotComboBox,firstDay;

    public Food(String foodName, String foodType, String quantifying, String expire, String slotComboBox,String firstDay) {
        this.foodName = foodName;
        this.foodType = foodType;
        this.quantifying = quantifying;
        this.expire = expire;
        this.slotComboBox = slotComboBox;
        this.firstDay = firstDay;
    }

    public Food(String foodName, String foodType, String quantifying, String expire, String slotComboBox) {
        this.foodName = foodName;
        this.foodType = foodType;
        this.quantifying = quantifying;
        this.expire = expire;
        this.slotComboBox = slotComboBox;
        this.firstDay = LocalDate.now().toString();
    }

    public String getFoodName() {
        return foodName;
    }

    public String getFoodType() {
        return foodType;
    }

    public String getQuantifying() {
        return quantifying;
    }

    public String getExpire() {
        return expire;
    }

    public String getSlotComboBox() {
        return slotComboBox;
    }

    public String getFirstDay() {
        return firstDay;
    }

    public void getMoreFresh(){
        this.expire = LocalDate.parse(expire).plusDays(14).toString();
    }

    public boolean isExpired(){
        if (LocalDate.now().isAfter(LocalDate.parse(expire))){
            return true;
        }
        return false;
    }

    public long dayInRefridgerator(){
        return DAYS.between(LocalDate.parse(firstDay), LocalDate.now());
    }

    public long dayExpired(){
        if (LocalDate.parse(expire).isBefore(LocalDate.now())){
            return DAYS.between(LocalDate.parse(expire),LocalDate.now());
        }
        return 0;
    }

    @Override
    public String toString() {
        return foodName+","+foodType+","+quantifying+","+expire+","+slotComboBox+","+firstDay;
    }

    @Override
    public int compareTo(Object o) {
        Food food = (Food)o;
        if (LocalDate.parse(expire).isAfter(LocalDate.parse(food.getExpire()))){
            return 1;
        }else if(LocalDate.parse(expire).isBefore(LocalDate.parse(food.getExpire()))){
            return -1;
        }else{
            return 0;
        }
    }
}
