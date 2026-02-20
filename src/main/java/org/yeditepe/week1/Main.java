package org.yeditepe.week1;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<Estimate> estimates=new ArrayList<Estimate>();

        estimates.add((new Estimate("Engin",2)));
        // estimates.add((new Estimate("Elif",4)));
        // estimates.add(new Estimate("Ayse",5));

        List<String> names=PlannigPocker.identifyExtremes(estimates);

        System.out.println(names);

    }
}
