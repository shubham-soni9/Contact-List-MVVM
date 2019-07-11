package com.contactdata.ui.contactdetails;

import android.net.Uri;
import androidx.databinding.ObservableField;

public class ContactDetails {
    private final ObservableField<Long> id = new ObservableField<>();
    private final ObservableField<String> name = new ObservableField<>();
    private final ObservableField<String> number = new ObservableField<>();
    private final ObservableField<String> email = new ObservableField<>();
    private final ObservableField<Uri> imageUrl = new ObservableField<>();

    public ObservableField<Long> getId() {
        return id;
    }

    public ObservableField<String> getName() {
        return name;
    }

    public ObservableField<String> getEmail() {
        return email;
    }

    public ObservableField<String> getNumber() {
        return number;
    }

    public ObservableField<Uri> getImageUrl() {
        return imageUrl;
    }

    public void setNumber(String number) {
        this.number.set(number);
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public void setImageUrl(Uri uri) {
        this.imageUrl.set(uri);
    }

    public void setId(Long id) {
        this.id.set(id);
    }

    public void setEmail(String email) {
        this.email.set(email);
    }
}
