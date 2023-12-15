package gg.quartzdev.qxpboosts.boost;

public class Boost {

    private String name;
    private double multiplier;

    public Boost(String name, double multiplier){
        this.name = name;
        this.multiplier = multiplier;
    }

    public String getName(){
        return this.name;
    }

    public double getMultiplier(){
        return this.multiplier;
    }


}
