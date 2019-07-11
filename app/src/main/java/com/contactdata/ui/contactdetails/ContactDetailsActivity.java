package com.contactdata.ui.contactdetails;

import android.os.Bundle;
import androidx.lifecycle.ViewModelProviders;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.contactdata.BR;
import com.contactdata.R;
import com.contactdata.ViewModelProviderFactory;
import com.contactdata.databinding.ActivityContactDetailsBinding;
import com.contactdata.ui.base.BaseActivity;
import com.contactdata.utils.AppConstants;

import javax.inject.Inject;

public class ContactDetailsActivity extends BaseActivity<ActivityContactDetailsBinding, ContactDetailsViewModel> implements ContactDetailsNavigator {

    @Inject
    ViewModelProviderFactory factory;
    private ContactDetailsViewModel mContactDetailsViewModel;
    private ActivityContactDetailsBinding mActivityContactDetailsBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityContactDetailsBinding = getViewDataBinding();
        mContactDetailsViewModel.setNavigator(this);
        setUp();
    }

    private void setUp() {
        mContactDetailsViewModel.loadContactDetails(getIntent().getLongExtra(AppConstants.Extras.CONTACT_ID, 0));
        mActivityContactDetailsBinding.toolbar.setTitle(mContactDetailsViewModel.getContactItemViewModel().getName().get());
        setSupportActionBar(mActivityContactDetailsBinding.toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(true);
        }

        Glide.with(mActivityContactDetailsBinding.ivProfile.getContext())
                .load(mContactDetailsViewModel.getContactItemViewModel().getImageUrl().get())
                .apply(new RequestOptions().fitCenter().diskCacheStrategy(DiskCacheStrategy.RESOURCE))
                .into(mActivityContactDetailsBinding.ivProfile);

        mActivityContactDetailsBinding.executePendingBindings();

    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_contact_details;
    }

    @Override
    public ContactDetailsViewModel getViewModel() {
        mContactDetailsViewModel = ViewModelProviders.of(this, factory).get(ContactDetailsViewModel.class);
        return mContactDetailsViewModel;
    }

    @Override
    public void handleError(Throwable throwable) {
    }

}
