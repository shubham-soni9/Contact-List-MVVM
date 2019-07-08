package com.contactdata.data.local.db;

import com.contactdata.data.local.model.db.Contact;
import io.reactivex.Observable;

import java.util.List;

public interface DbHelper {

    Observable<Boolean> insertContact(final Contact user);

    Observable<List<Contact>> getAllContacts();
}
