package com.contactdata.ui.contactlist;

public interface ContactListNavigator {
    void askContactPermission();
    void updateContactList();
    void showErrorAndFinish(int error);
}
