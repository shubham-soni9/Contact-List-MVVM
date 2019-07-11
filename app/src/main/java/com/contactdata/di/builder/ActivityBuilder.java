package com.contactdata.di.builder;

import com.contactdata.ui.contactdetails.ContactDetailsActivity;
import com.contactdata.ui.contactlist.ContactListActivity;
import com.contactdata.ui.contactlist.list.ContactListModule;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = ContactListModule.class)
    abstract ContactListActivity bindContactListActivity();

    @ContributesAndroidInjector
    abstract ContactDetailsActivity bindContactDetailsActivity();
}
