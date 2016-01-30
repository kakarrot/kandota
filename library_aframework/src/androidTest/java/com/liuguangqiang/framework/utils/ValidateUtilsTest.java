package com.liuguangqiang.framework.utils;

import android.test.AndroidTestCase;

public class ValidateUtilsTest extends AndroidTestCase {

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    public void testIsMobileNumber() {
        assertEquals(true, ValidateUtils.isMobileNumber("13838318082"));
    }

    public void testIsEmail() {
        assertEquals(true, ValidateUtils.isEmail("test@gmail.com"));
    }

    public void testIsNumeric() {
        assertEquals(true, ValidateUtils.isNumeric("123456789"));
    }

    public void testIsNumAndAlphabet() {
        assertEquals(true, ValidateUtils.isNumAndAlphabet("abc123"));
    }

    public void testIsAlphabet() {
        assertEquals(true, ValidateUtils.isAlphabet("abc"));
    }

    public void testIsUrl() {
        assertEquals(true, ValidateUtils.isUrl("www.google.com"));
    }

    public void testContainChinese() {
        assertEquals(true, ValidateUtils.containChinese("abc你好"));
    }

}
