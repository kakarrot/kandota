package com.liuguangqiang.framework.utils;

import android.test.AndroidTestCase;

/**
 * Created by Eric on 14/12/18.
 */
public class PreferencesUtilsTest extends AndroidTestCase {

    private static final String PRE_NAME = "PRE_UNIT_TEST";

    private static final String KEY_INT = "TEST_INT";

    private static final String KEY_STRING = "TEST_STRING";

    private static final String KEY_BOOLEAN = "TEST_BOOLEAN";

    private static final String KEY_FLOAT = "TEST_FLOAT";

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    public void testPutAndGetInt() {
        int value = 1;
        PreferencesUtils.putInt(getContext(), PRE_NAME, KEY_INT, value);
        assertEquals(value, PreferencesUtils.getInt(getContext(), PRE_NAME, KEY_INT));
    }

    public void testPutAndGetString() {
        String value = "test";
        PreferencesUtils.putString(getContext(), PRE_NAME, KEY_STRING, value);
        assertEquals(value, PreferencesUtils.getString(getContext(), PRE_NAME, KEY_STRING));
    }

    public void testPutAndGetBoolean() {
        boolean value = true;
        PreferencesUtils.putBoolean(getContext(), PRE_NAME, KEY_BOOLEAN, value);
        assertEquals(value, PreferencesUtils.getBoolean(getContext(), PRE_NAME, KEY_BOOLEAN));
    }

    public void testPutAndGetFloat() {
        float value = 1.5f;
        PreferencesUtils.putFloat(getContext(), PRE_NAME, KEY_FLOAT, value);
        assertEquals(value, PreferencesUtils.getFloat(getContext(), PRE_NAME, KEY_FLOAT));
    }


}
