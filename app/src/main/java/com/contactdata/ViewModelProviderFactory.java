package com.contactdata;

import android.app.Application;
import android.content.ContentResolver;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.contactdata.ui.contactdetails.ContactDetailsViewModel;
import com.contactdata.ui.contactlist.ContactListViewModel;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by jyotidubey on 22/02/19.
 */
@Singleton
public class ViewModelProviderFactory extends ViewModelProvider.NewInstanceFactory {

  private final ContentResolver contentResolver ;
  private final Application application;

  @Inject
  public ViewModelProviderFactory(ContentResolver contentResolver,Application application) {
    this.contentResolver = contentResolver;
    this.application = application;
  }


  @Override
  public <T extends ViewModel> T create(Class<T> modelClass) {
    if (modelClass.isAssignableFrom(ContactListViewModel.class)) {
      //noinspection unchecked
      return (T) new ContactListViewModel(contentResolver,application);
    }
    else if (modelClass.isAssignableFrom(ContactDetailsViewModel.class)) {
      //noinspection unchecked
      return (T) new ContactDetailsViewModel(contentResolver,application);
    }
    throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
  }
}