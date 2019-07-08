package com.contactdata.data.local.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import com.contactdata.data.local.model.db.Contact;

import java.util.List;

@Dao
public interface ContactDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Contact option);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Contact> options);

    @Query("SELECT * FROM contact")
    List<Contact> loadAll();

    @Query("SELECT * FROM contact WHERE id = :questionId")
    List<Contact> loadAllByQuestionId(Long questionId);
}
