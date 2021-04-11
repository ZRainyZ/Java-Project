package models;

public class FreezeSlot extends NormalSlot{

    public boolean addFood(Food food){
        food.getMoreFresh();
        return super.addFood(food);
    }
}
