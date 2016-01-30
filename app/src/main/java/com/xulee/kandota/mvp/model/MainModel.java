/*
 * Copyright 2015 qiwenge
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
 */

package com.xulee.kandota.mvp.model;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;

import com.liuguangqiang.framework.utils.Logs;
import com.xulee.kandota.R;
import com.xulee.kandota.async.AsyncCheckUpdate;
import com.xulee.kandota.constant.Constants;
import com.xulee.kandota.entity.MainMenuItem;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by Eric on 15/5/25.
 */
public class MainModel {

    @Inject
    public MainModel() {
    }

    public List<MainMenuItem> getMenu(Context context) {
        String[] titles = context.getResources().getStringArray(R.array.main_menu_titles);
        TypedArray icons = context.getResources().obtainTypedArray(R.array.main_menu_icon_n);
        TypedArray iconsSeleted = context.getResources().obtainTypedArray(R.array.main_menu_icon_s);

        List<MainMenuItem> list = new ArrayList<>();
        MainMenuItem item;
        for (int i = 0; i < titles.length; i++) {
            item = new MainMenuItem();
            item.title = titles[i];
            item.icon = icons.getResourceId(i, 0);
            item.iconSelected = iconsSeleted.getResourceId(i, 0);
            list.add(item);
        }
        list.get(0).selected = true;

        return list;
    }

    public void checkUpdate(Activity context) {
        if (!Constants.DISABLE_UPDATE) {
            Logs.i("MainModel", "checkUpdate");
            AsyncCheckUpdate update = new AsyncCheckUpdate(context);
            update.setOnlyCheck();
            update.checkUpdate();
        }
    }

}
