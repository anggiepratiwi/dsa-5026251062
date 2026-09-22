package lw01.prelab;

public class MonoPrint extends PrintJob {
    public MonoPrint(String id, int pages){
        super(id, pages);
    }

    @Override 
    public int calculateCharge(){
        return 500 * getPages();
    }

    @Override
    public String label(){
        return "Mono";
    }
}
