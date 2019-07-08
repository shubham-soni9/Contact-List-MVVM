package com.contactdata.ui.contactdetails;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.lifecycle.ViewModelProviders;
import com.contactdata.BR;
import com.contactdata.R;
import com.contactdata.ViewModelProviderFactory;
import com.contactdata.databinding.ActivityContactListBinding;
import com.contactdata.ui.base.BaseActivity;

import javax.inject.Inject;

public class ContactDetailsActivity extends BaseActivity<ActivityContactListBinding, ContactDetailsViewModel> implements ContactDetailsNavigator {

    @Inject
    ViewModelProviderFactory factory;
    private ContactDetailsViewModel mContactDetailsViewModel;
    private ActivityContactListBinding mActivityContactBinding;

    public static Intent newIntent(Context context) {
        return new Intent(context, ContactDetailsActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityContactBinding = getViewDataBinding();
        mContactDetailsViewModel.setNavigator(this);
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
    public ContactDetailsViewModel getViewModel() {
        mContactDetailsViewModel = ViewModelProviders.of(this,factory).get(ContactDetailsViewModel.class);
        return mContactDetailsViewModel;
    }

    @Override
    public void handleError(Throwable throwable) {
    }

    @Override
    public void openDetailsActivity() {

    }
}
