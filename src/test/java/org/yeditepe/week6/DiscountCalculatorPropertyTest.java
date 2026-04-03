package org.yeditepe.week6;

import net.jqwik.api.*;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class DiscountCalculatorPropertyTest {

    private static DiscountCalculator instance;

    @Before
    public void startup(){
        instance= new DiscountCalculator();
    }

    @Property
    public  void testForAllDiscountAndPrice(@ForAll("getPrices") double p,
                                            @ForAll("getDiscountRates") double d){

        System.out.println(instance.applyDiscount(p,d));

        assertThat(instance.applyDiscount(p,d)).isFinite();
    }

    @Provide
    public Arbitrary<Double> getPrices(){
        return Arbitraries.doubles().between(0
        ,100);
    }

    @Provide
    public Arbitrary<Double> getDiscountRates(){
        return Arbitraries.doubles().between(0,1);
    }
}
