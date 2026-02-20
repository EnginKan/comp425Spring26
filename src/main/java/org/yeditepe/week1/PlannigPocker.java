package org.yeditepe.week1;


import java.util.Arrays;
import java.util.List;

public class PlannigPocker {


    public static List<String> identifyExtremes(
            List<Estimate> estimates
    ){

        if(estimates==null)
            throw new IllegalArgumentException(("Input cannot be null"));
        else if(estimates.size()==0 || estimates.size()==1)
            throw new IllegalArgumentException("Size of input must be at least 2");

        Estimate bestEstimate=estimates.get(0);//which has biggest point
        Estimate worstEstimate=estimates.get(0);//which has smallest point

        for(Estimate estimate: estimates){
            if(bestEstimate.getPoint()<estimate.getPoint())
                bestEstimate=estimate;
            if(worstEstimate.getPoint()> estimate.getPoint())
                worstEstimate=estimate;
        }
        if(worstEstimate.getPoint()==bestEstimate.getPoint())
            throw new IllegalArgumentException("Best and worst estimates have the same value:"+bestEstimate.getPoint());

        return Arrays.asList(bestEstimate.getName(),worstEstimate.getName());


    }
}
