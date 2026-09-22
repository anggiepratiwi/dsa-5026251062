package lw01.unguided;

public class CarWash extends WashService{
    public CarWash(String id, int days, int units){
        super(id, days, units);
    }

    @Override 
    public int calculateCharge(){
        int days = getDays();
        int total = 0;

        if(days <= 3){
            total = 35000 * days;
        } else{
            total = (35000 * 3) + ((days - 3) * 25000);
        }
        return total + 15000;
    }

    @Override 
    public String label(){
        return "Car";
    }
}
