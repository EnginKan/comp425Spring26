package org.yeditepe.week1;

import static org.junit.jupiter.api.Assertions.*;



import net.jqwik.api.*;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PlannigPockerTest {

    @Test
    public void rejectNullList(){

        assertThrows(IllegalArgumentException.class,
                ()->PlannigPocker.identifyExtremes(null));
    }
    @Test
    public void rejectEmptyList(){
        assertThrows(IllegalArgumentException.class,
                ()->PlannigPocker.identifyExtremes(
                        new ArrayList<Estimate>()));

    }
    @Test
    public void rejectSingleEstimate(){
        assertThrows(IllegalArgumentException.class,
                ()->PlannigPocker.identifyExtremes(
                        Arrays.asList(new Estimate("Engin",2))));

    }

    @Test
    public void twoEstimates(){

        List<Estimate> input= Arrays.asList(new Estimate("Engin",10),
                new Estimate("Ahmet",12));
        List<String> extremes = PlannigPocker.identifyExtremes(input);
        assertThat(extremes).contains("Engin","Ahmet");
    }
    @Test
    public void threeEstimates(){

        List<Estimate> input= Arrays.asList(new Estimate("Engin",10),
                new Estimate("Ahmet",12),
                new Estimate("Ayse",8));
        List<String> extremes = PlannigPocker.identifyExtremes(input);
        assertThat(extremes).contains("Ayse","Ahmet");
    }
    /*Property Based Testing*/
    @Property
    public void manyEstimates(@ForAll("estimates") List<Estimate> estimates){

        estimates.add(new Estimate("MaxEstimateOwner",100));
        estimates.add(new Estimate("MinEstimateOwner",1));
        Collections.shuffle(estimates);
        List<String> extremes = PlannigPocker.identifyExtremes(estimates);
        assertThat(extremes).contains("MaxEstimateOwner","MinEstimateOwner");

    }
    @Provide
    public Arbitrary<Estimate> estimates(){

        Arbitrary<Integer> points = Arbitraries.integers().between(2,99);
        Arbitrary<String> names = Arbitraries.strings().withCharRange('a','z').ofMinLength(1);

        return  Combinators.combine(names,points)
                .as((name,point)->
                        new Estimate(name,point));



    }

}