package com.contactdata.di.module;

import android.app.Application;
import android.content.Context;
import androidx.room.Room;
import com.contactdata.data.AppDataManager;
import com.contactdata.data.DataManager;
import com.contactdata.data.local.db.AppDatabase;
import com.contactdata.data.local.db.AppDbHelper;
import com.contactdata.data.local.db.DbHelper;
import com.contactdata.di.DatabaseInfo;
import com.contactdata.utils.AppConstants;
import com.contactdata.utils.rx.AppSchedulerProvider;
import com.contactdata.utils.rx.SchedulerProvider;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module
public class AppModule {

    @Provides
    @Singleton
    AppDatabase provideAppDatabase(@DatabaseInfo String dbName, Context context) {
        return Room.databaseBuilder(context, AppDatabase.class, dbName).fallbackToDestructiveMigration()
                .build();
    }

    @Provides
    @Singleton
    Context provideContext(Application application) {
        return application;
    }

    @Provides
    @Singleton
    DataManager provideDataManager(AppDataManager appDataManager) {
        return appDataManager;
    }

    @Provides
    @Singleton
    DbHelper provideDbHelper(AppDbHelper appDbHelper) {
        return appDbHelper;
    }

    @Provides
    SchedulerProvider provideSchedulerProvider() {
        return new AppSchedulerProvider();
    }

    @Provides
    @DatabaseInfo
    String provideDatabaseName() {
        return AppConstants.DB_NAME;
    }


}
