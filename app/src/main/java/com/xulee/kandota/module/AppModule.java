package com.xulee.kandota.module;

import com.xulee.kandota.app.MyApplication;

import dagger.Module;

/**
 * Created by Eric on 15/5/12.
 */
@Module(
        injects = {
                MyApplication.class
        }
)
public class AppModule {
}
