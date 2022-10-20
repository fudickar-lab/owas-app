package com.example.owashaltungsanalyse.di;

import com.example.owashaltungsanalyse.model.observation.ISession;
import com.example.owashaltungsanalyse.model.observation.Session;
import dagger.Binds;
import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;


@Module
@InstallIn(ApplicationComponent.class)
public abstract class SessionModul {

    @Binds
    public abstract ISession bindISession(Session session);
}
