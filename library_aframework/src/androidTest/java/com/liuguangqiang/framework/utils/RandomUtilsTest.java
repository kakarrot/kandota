package com.liuguangqiang.framework.utils;

import android.test.AndroidTestCase;

/**
 * Created by Eric on 15/1/12.
 */
public class RandomUtilsTest extends AndroidTestCase {

    private static final int TEST_COUNT = 1000;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    public void testRandom() {
        boolean result = true;
        int randomInt;
        int min = 1;
        int max = 10;
        for (int i = 0; i < TEST_COUNT; i++) {
            randomInt = RandomUtils.random(min, max);
            if (randomInt > max || randomInt < min) result = false;
        }
        assertEquals(result, true);
    }

    public void testRandomAlphabet() {
        int length = 10;
        boolean result = true;
        String random;
        for (int i = 0; i < TEST_COUNT; i++) {
            random = RandomUtils.randomAlphabet(length);
            if (random.length() != length || !ValidateUtils.isAlphabet(random))
                result = false;
        }
        assertEquals(result, true);
    }

    public void testRandomAlphabetAndNumber() {
        int length = 10;
        boolean result = true;
        String random;
        for (int i = 0; i < TEST_COUNT; i++) {
            random = RandomUtils.randomAlphabetAndNumber(length);
            if (random.length() != length || !ValidateUtils.isNumAndAlphabet(random))
                result = false;
        }
        assertEquals(result, true);
    }

}
