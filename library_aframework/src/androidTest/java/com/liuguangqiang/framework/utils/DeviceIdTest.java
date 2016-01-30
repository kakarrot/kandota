package com.liuguangqiang.framework.utils;

import android.test.AndroidTestCase;

/**
 * Created by Eric on 15/4/2.
 */
public class DeviceIdTest extends AndroidTestCase {

    public void getGetDeviceId() {
        String deviceId = DeviceId.getInstance(getContext()).getDeviceId();
        assertEquals(true, deviceId.length() == 16 && ValidateUtils.isNumAndAlphabet(deviceId));
    }

}
