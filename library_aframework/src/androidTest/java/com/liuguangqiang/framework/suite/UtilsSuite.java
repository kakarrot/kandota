package com.liuguangqiang.framework.suite;

import android.test.AndroidTestCase;

import com.liuguangqiang.framework.utils.HtmlUtilsTest;
import com.liuguangqiang.framework.utils.PreferencesUtilsTest;
import com.liuguangqiang.framework.utils.RandomUtilsTest;
import com.liuguangqiang.framework.utils.TimeUtilsTest;
import com.liuguangqiang.framework.utils.ValidateUtilsTest;

import junit.framework.TestSuite;

public class
        UtilsSuite extends AndroidTestCase {

    public static TestSuite suite() {
        TestSuite suite = new TestSuite();
        suite.addTestSuite(HtmlUtilsTest.class);
        suite.addTestSuite(ValidateUtilsTest.class);
        suite.addTestSuite(PreferencesUtilsTest.class);
        suite.addTestSuite(RandomUtilsTest.class);
        suite.addTestSuite(TimeUtilsTest.class);
        return suite;
    }
}
