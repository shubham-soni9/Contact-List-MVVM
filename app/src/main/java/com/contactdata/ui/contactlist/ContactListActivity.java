package com.contactdata.ui.contactlist;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.lifecycle.ViewModelProviders;
import com.contactdata.BR;
import com.contactdata.R;
import com.contactdata.ViewModelProviderFactory;
import com.contactdata.databinding.ActivityContactListBinding;
import com.contactdata.ui.base.BaseActivity;
import com.contactdata.ui.contactdetails.ContactDetailsActivity;


import javax.inject.Inject;

public class ContactListActivity extends BaseActivity<ActivityContactListBinding, ContactListViewModel> implements ContactListNavigator {

    @Inject
    ViewModelProviderFactory factory;
    private ContactListViewModel mContactListViewModel;
    private ActivityContactListBinding mActivityContactBinding;

    public static Intent newIntent(Context context) {
        return new Intent(context, ContactDetailsActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityContactBinding = getViewDataBinding();
        mContactListViewModel.setNavigator(this);
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_contact_list;
    }

    @Override
    public ContactListViewModel getViewModel() {
        mContactListViewModel = ViewModelProviders.of(this,factory).get(ContactListViewModel.class);
        return mContactListViewModel;
    }

    @Override
    public void handleError(Throwable throwable) {
    }

    @Override
    public void openDetailsActivity() {

    }
}
