package com.contactdata.ui.contactdetails;

import com.contactdata.data.DataManager;
import com.contactdata.ui.base.BaseViewModel;
import com.contactdata.utils.rx.SchedulerProvider;

public class ContactDetailsViewModel extends BaseViewModel<ContactDetailsNavigator> {

    public ContactDetailsViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }
}
