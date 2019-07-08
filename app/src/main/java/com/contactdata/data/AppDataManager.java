package com.contactdata.data;

import android.content.Context;
import com.contactdata.data.local.db.DbHelper;
import com.contactdata.data.local.model.db.Contact;
import com.google.gson.Gson;
import io.reactivex.Observable;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

@Singleton
public class AppDataManager implements DataManager {
    private final DbHelper mDbHelper;

    private final Context mContext;

    @Inject
    public AppDataManager(Context context, DbHelper dbHelper) {
        mContext = context;
        mDbHelper = dbHelper;
    }

    @Override
    public Observable<Boolean> insertContact(Contact user) {
        return mDbHelper.insertContact(user);
    }

    @Override
    public Observable<List<Contact>> getAllContacts() {
        return mDbHelper.getAllContacts();
    }
}
