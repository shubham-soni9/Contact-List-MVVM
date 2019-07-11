package com.contactdata.ui.contactlist;

import android.Manifest;
import android.os.Bundle;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.contactdata.BR;
import com.contactdata.R;
import com.contactdata.ViewModelProviderFactory;
import com.contactdata.databinding.ActivityContactListBinding;
import com.contactdata.ui.base.BaseActivity;
import com.contactdata.ui.contactlist.list.ContactAdapter;
import com.contactdata.ui.contactlist.list.ContactItemViewModel;
import com.contactdata.utils.AppManager;
import com.contactdata.utils.Codes;

import javax.inject.Inject;

public class ContactListActivity extends BaseActivity<ActivityContactListBinding, ContactListViewModel> implements ContactListNavigator {

    @Inject
    ViewModelProviderFactory factory;
    @Inject
    LinearLayoutManager mLayoutManager;
    @Inject
    ContactAdapter mContactAdapter;
    private ContactListViewModel mContactListViewModel;
    private ActivityContactListBinding mActivityContactBinding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityContactBinding = getViewDataBinding();
        mContactListViewModel.setNavigator(this);
        setUp();
    }

    private void setUp() {
        mActivityContactBinding.rvContactList.setLayoutManager(mLayoutManager);
        mActivityContactBinding.rvContactList.setItemAnimator(new DefaultItemAnimator());
        mActivityContactBinding.rvContactList.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        mActivityContactBinding.rvContactList.setAdapter(mContactAdapter);
        mContactListViewModel.checkPermission();
    }

    private void setAdapter(PagedList<ContactItemViewModel> contactItemViewModels) {
        mContactAdapter.addItems(contactItemViewModels);
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
        mContactListViewModel = ViewModelProviders.of(this, factory).get(ContactListViewModel.class);
        return mContactListViewModel;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        mContactListViewModel.onRequestPermissionResult(grantResults);
    }

    @Override
    public void askContactPermission() {
        String[] permissionsRequired = new String[]{Manifest.permission.READ_CONTACTS};
        AppManager.getInstance().askUserToGrantPermission(this, permissionsRequired, Codes.Permission.READ_CONTACT);
    }

    @Override
    public void updateContactList() {
        mContactListViewModel.contactsListLiveData.observe(this, this::setAdapter);
    }

    @Override
    public void showErrorAndFinish(int error) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show();
        finish();
    }
}
