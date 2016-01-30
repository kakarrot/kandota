package com.liuguangqiang.framework.utils;

import android.test.AndroidTestCase;

public class HtmlUtilsTest extends AndroidTestCase {

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    public void testGetHostName() { 
        assertEquals("www.36kr.com", HtmlUtils.getHostName("http://www.36kr.com/feed/"));
    }

}
