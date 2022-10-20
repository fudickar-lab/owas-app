package com.example.owashaltungsanalyse.di;


import android.content.Context;
import androidx.room.Room;
import com.example.owashaltungsanalyse.database.AppDatabase;
import com.example.owashaltungsanalyse.model.repository.DatabaseModel;
import com.example.owashaltungsanalyse.model.repository.IDatabase;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;
import dagger.hilt.android.qualifiers.ApplicationContext;

import javax.inject.Singleton;


@Module
@InstallIn(ApplicationComponent.class)
public abstract class DatabaseModule {
    @Provides
    @Singleton
    public static AppDatabase provideAppDatabase(@ApplicationContext Context context) {
        return Room.databaseBuilder(context, AppDatabase.class, "App.db").allowMainThreadQueries().build();
    }

    @Singleton
    @Binds
    public abstract IDatabase bindIDatabase(DatabaseModel databaseModel);

}
