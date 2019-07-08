package com.contactdata;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.contactdata.data.DataManager;
import com.contactdata.ui.contactdetails.ContactDetailsViewModel;
import com.contactdata.ui.contactlist.ContactListViewModel;
import com.contactdata.utils.rx.SchedulerProvider;
import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;

public class ViewModelProviderFactory extends ViewModelProvider.NewInstanceFactory {
    private final DataManager dataManager;
    private final SchedulerProvider schedulerProvider;

    @Inject
    public ViewModelProviderFactory(DataManager dataManager,
                                    SchedulerProvider schedulerProvider) {
        this.dataManager = dataManager;
        this.schedulerProvider = schedulerProvider;
    }


    @NotNull
    @Override
    public <T extends ViewModel> T create(@NotNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(ContactListViewModel.class)) {
            //noinspection unchecked
            return (T) new ContactListViewModel(dataManager,schedulerProvider);
        } else if (modelClass.isAssignableFrom(ContactDetailsViewModel.class)) {
            //noinspection unchecked
            return (T) new ContactDetailsViewModel(dataManager,schedulerProvider);
        }
        throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
    }
}
