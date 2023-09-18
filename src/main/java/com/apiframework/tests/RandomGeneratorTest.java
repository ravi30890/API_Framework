package com.apiframework.tests;

import com.apiframework.utils.RandomGenerator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RandomGeneratorTest {

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAlphaNumberic() {
        String regex = "^(?=.*[a-zA-Z])(?=.*[0-9])[A-Za-z0-9]+$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(RandomGenerator.randomAlphaNumeric(4));
        Assert.assertTrue(m.matches());
    }

    @Test
    public void testAlphabets() {
        String regex = "^(?=.*[a-zA-Z])[A-Za-z]+$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(RandomGenerator.randomAlphabets(15));
        Assert.assertTrue(m.matches());
    }

    @Test
    public void testNumeric() {
        String regex = "^[0-9]*$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(RandomGenerator.randomNumeric(5));
        Assert.assertTrue(m.matches());
    }

    @Test
    public void testDecimalNumber() {
        String regex = "^(\\d)*(\\.)?([0-9]{2})?$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(RandomGenerator.randomDecimalNumber(3, 2));
        Assert.assertTrue(m.matches());
    }

    @Test
    public void testInteger() {
        int output = RandomGenerator.getRandomInteger(1, 10);
        Assert.assertTrue(output > 1 && output < 10);
    }
}
