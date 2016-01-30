/*
 * Copyright 2015 Eric Liu
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.liuguangqiang.framework.utils;

import android.test.AndroidTestCase;

/**
 * Created by Eric on 15/4/2.
 */
public class TimeUtilsTest extends AndroidTestCase {

    public void testGetTimestamp() {
        long ts = TimeUtils.getTimestamp();
        assertEquals(true, String.valueOf(ts).length() == 13);
    }

    public void testGetTimestampSeconds() {
        long ts = TimeUtils.getTimestampSeconds();
        assertEquals(true, String.valueOf(ts).length() == 10);
    }

    public void testTimeStamp2Date() {
        long ts = TimeUtils.getTimestamp();
        long tsInSeconds = TimeUtils.getTimestampSeconds();

        String tsDate = TimeUtils.timeStamp2Date(ts, "yyyy-MM-dd", false);
        String tsDateInSeconds = TimeUtils.timeStamp2Date(tsInSeconds, "yyyy-MM-dd", true);

        assertEquals(true, tsDate.equals(tsDateInSeconds));
    }


}
