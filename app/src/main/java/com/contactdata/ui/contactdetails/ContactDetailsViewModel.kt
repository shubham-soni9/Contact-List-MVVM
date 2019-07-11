package com.contactdata.ui.contactdetails

import android.app.Application
import android.content.ContentResolver
import android.content.ContentUris
import android.database.Cursor
import android.net.Uri
import android.provider.ContactsContract
import android.util.SparseArray
import com.contactdata.data.model.Email
import com.contactdata.ui.base.BaseViewModel
import com.contactdata.utils.AppConstants
import com.contactdata.utils.Log
import com.contactdata.utils.Utils

class ContactDetailsViewModel(private val contentResolver: ContentResolver, application: Application) :
    BaseViewModel<ContactDetailsNavigator>(application) {
    var contactItemViewModel: ContactDetails = ContactDetails()
    val TAG =ContactDetailsActivity::class.java.name

    fun loadContactDetails(contactId: Long) {
        Log.e(TAG, "CONTACT ID :: $contactId")
        val selection = ContactsContract.Data.CONTACT_ID + " = ?";
        val selection_args = arrayOf(contactId.toString())

        val cursor = contentResolver.query(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
            selection, selection_args, ContactsContract.Contacts.DISPLAY_NAME_PRIMARY
        )
        if (cursor != null) {
            cursor.moveToFirst()
            val name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME))
            val phoneNumber = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
            var photoUri = ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, contactId)
            photoUri = Uri.withAppendedPath(photoUri, ContactsContract.Contacts.Photo.DISPLAY_PHOTO)
            val email = getEmails()

            contactItemViewModel.setId(contactId)
            contactItemViewModel.setName(name)
            contactItemViewModel.setNumber(phoneNumber)
            contactItemViewModel.setImageUrl(photoUri)
            contactItemViewModel.setEmail(email)

            Log.e(TAG, "ID :: $contactId")
            Log.e(TAG, "NAME :: $name")
            Log.e(TAG, "PHONE NUMBER :: $phoneNumber")
            Log.e(TAG, "PHOTO URI :: $photoUri")
            Log.e(TAG, "EMAIL :: $email")

            cursor.close()
        }
        else {
            Log.e(TAG,"Cursor is Null")
        }
    }

    private fun getEmails(contactId: Int? = null): String {
        val emails = ArrayList<Email>()
        val uri = ContactsContract.CommonDataKinds.Email.CONTENT_URI
        val projection = arrayOf(
            ContactsContract.CommonDataKinds.Email.DATA,
            ContactsContract.CommonDataKinds.Email.TYPE,
            ContactsContract.CommonDataKinds.Email.LABEL
        )

        val SELECTION = ContactsContract.Data.CONTACT_ID + " = ?";
        val SELECTION_ARGS = arrayOf(contactId.toString())

        var cursor: Cursor? = null
        try {
            cursor = contentResolver.query(uri, projection, SELECTION, SELECTION_ARGS, null)
            if (cursor?.moveToFirst()==true) {
                do {
                    val email = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA)) ?: continue
                    val type = cursor.getInt(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Email.TYPE))
                    val label = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Email.LABEL)) ?: ""
                    emails.add(Email(email, type, label))
                    Log.e(TAG,"Emails :: ")
                    Log.e(TAG, "Value ::$email")
                    Log.e(TAG, "Type ::$type")
                    Log.e(TAG, "Label ::$label")

                    if(email.isNotEmpty()){
                        return email;
                    }
                } while (cursor.moveToNext())
            }
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            cursor?.close()
        }

        return if(Utils.hasData(emails)){
            emails[0].value
        } else{
            AppConstants.EMPTY;
        }
    }
}

