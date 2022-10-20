package com.example.owashaltungsanalyse.di;

import com.example.owashaltungsanalyse.model.observation.ISessionManager;
import com.example.owashaltungsanalyse.model.observation.SessionManager;
import dagger.Binds;
import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;

import javax.inject.Singleton;


@Module
@InstallIn(ApplicationComponent.class)
public abstract class ISessionManagerModule {

    @Singleton
    @Binds
    public abstract ISessionManager bindISessionManager(SessionManager sessionManager);

}


