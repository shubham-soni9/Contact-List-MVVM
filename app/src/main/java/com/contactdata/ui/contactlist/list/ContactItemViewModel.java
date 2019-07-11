/*
 *  Copyright (C) 2017 MINDORKS NEXTGEN PRIVATE LIMITED
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      https://mindorks.com/license/apache-v2
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License
 */

package com.contactdata.ui.contactlist.list;

import android.net.Uri;
import androidx.databinding.ObservableField;

/**
 * Created by amitshekhar on 10/07/17.
 */

public class ContactItemViewModel {

    public final ObservableField<Long> id = new ObservableField<>();

    public final ObservableField<String> name = new ObservableField<>();

    public final ObservableField<String> number = new ObservableField<>();

    public final ObservableField<Uri> imageUrl=new ObservableField<>();

    public ContactItemViewModel(Long id, String name, String number,Uri image) {
        this.id.set(id);
        this.name.set(name);
        this.number.set(number);
        this.imageUrl.set(image);
    }


}
