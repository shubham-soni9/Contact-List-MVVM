package com.contactdata.ui.contactlist

import android.Manifest
import android.app.Application
import android.content.ContentResolver
import android.content.ContentUris
import android.content.ContentValues.TAG
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.ContactsContract
import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import androidx.paging.PositionalDataSource
import com.contactdata.R
import com.contactdata.ui.base.BaseViewModel
import com.contactdata.ui.contactlist.list.ContactItemViewModel
import com.contactdata.utils.AppManager
import com.contactdata.utils.Log
import com.contactdata.utils.Utils
import java.util.*

class ContactListViewModel(private val contentResolver: ContentResolver, application: Application) :
    BaseViewModel<ContactListNavigator>(application) {
    lateinit var contactsListLiveData: LiveData<PagedList<ContactItemViewModel>>

    fun loadContacts() {
        val config = PagedList.Config.Builder()
            .setPageSize(20)
            .setEnablePlaceholders(false)
            .build()
        contactsListLiveData = LivePagedListBuilder(ContactsDataSourceFactory(contentResolver), config).build()
    }

    fun checkPermission() {
        if (AppManager.getInstance().isPermissionGranted(
                getApplication<Application>().applicationContext,
                Manifest.permission.READ_CONTACTS
            )) {
            Log.e(TAG,"Permission Available")
            loadContacts()
            navigator.updateContactList()
        } else {
            Log.e(TAG,"Permission Asking")
            navigator.askContactPermission()
        }
    }

    fun onRequestPermissionResult(grantResults: IntArray) {
        if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Log.e(TAG,"Permission Granted")
            checkPermission()
        }
        else {
            navigator.showErrorAndFinish(R.string.permission_required)
        }
    }
}

private class ContactsDataSourceFactory(private val contentResolver: ContentResolver) :
    DataSource.Factory<Int, ContactItemViewModel>() {

    override fun create(): DataSource<Int, ContactItemViewModel> {
        return ContactsDataSource(contentResolver)
    }
}

private class ContactsDataSource(private val contentResolver: ContentResolver) :
    PositionalDataSource<ContactItemViewModel>() {

    override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<ContactItemViewModel>) {
        callback.onResult(getContacts(), 0)
    }

    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<ContactItemViewModel>) {
        callback.onResult(getContacts())
    }

    private fun getContacts(): MutableList<ContactItemViewModel> {
        val cursor = contentResolver.query(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
            null, null, ContactsContract.Contacts.DISPLAY_NAME_PRIMARY
        )
        val contacts: MutableList<ContactItemViewModel> = mutableListOf()
        if (cursor != null) {
            cursor.moveToFirst()
            while (!cursor.isAfterLast) {
                val name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME))
                val phoneNumber =
                    cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
                val contactId = cursor.getLong(cursor.getColumnIndex(ContactsContract.Data.CONTACT_ID))
                var photoUri = ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, contactId)
                photoUri = Uri.withAppendedPath(photoUri, ContactsContract.Contacts.Photo.CONTENT_DIRECTORY)
                contacts.add(ContactItemViewModel(contactId, name, phoneNumber, photoUri))

                Log.e(TAG, "ID :: $contactId")
                Log.e(TAG, "NAME :: $name")
                Log.e(TAG, "PHONE NUMBER :: $phoneNumber")
                Log.e(TAG, "PHOTO URI :: $photoUri")

                cursor.moveToNext()

            }
            cursor.close()

            contacts.sortWith(Comparator { text1, text2 ->
                if (Utils.hasData(text1.name.get()) && Utils.hasData(text2.name.get())) {
                    text1.name.get()!!.compareTo(text2.name.get()!!, ignoreCase = true)
                } else 0
            })
        }
        return contacts
    }
}

