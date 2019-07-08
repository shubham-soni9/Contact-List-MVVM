package com.contactdata.ui.contactlist;

import com.contactdata.data.DataManager;
import com.contactdata.ui.base.BaseViewModel;
import com.contactdata.utils.rx.SchedulerProvider;

public class ContactListViewModel extends BaseViewModel<ContactListNavigator> {

    public ContactListViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }
}
