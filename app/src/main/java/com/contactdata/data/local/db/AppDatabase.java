package com.contactdata.data.local.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import com.contactdata.data.local.db.dao.ContactDao;
import com.contactdata.data.local.model.db.Contact;

@Database(entities = {Contact.class,}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract ContactDao contactDao();

}

