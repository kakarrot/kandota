package com.xulee.kandota.module;

import com.xulee.kandota.app.DotaApplication;

import dagger.Module;

/**
 * Created by Eric on 15/5/12.
 */
@Module(
        injects = {
                DotaApplication.class
        }
)
public class AppModule {
}
