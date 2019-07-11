package com.contactdata.ui.contactlist.list;

import android.content.Context;
import androidx.recyclerview.widget.LinearLayoutManager;
import dagger.Module;
import dagger.Provides;

@Module
public class ContactListModule {

    @Provides
    LinearLayoutManager provideLinearLayoutManager(Context activity) {
        return new LinearLayoutManager(activity);
    }

    @Provides
    ContactAdapter provideOpenSourceAdapter() {
        return new ContactAdapter();
    }
}
