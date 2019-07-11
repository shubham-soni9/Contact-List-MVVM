package com.contactdata.ui.base;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModel;
import com.contactdata.data.DataManager;
import com.contactdata.utils.rx.SchedulerProvider;
import io.reactivex.disposables.CompositeDisposable;

import java.lang.ref.WeakReference;

public abstract class BaseViewModel<N> extends AndroidViewModel {
    private CompositeDisposable mCompositeDisposable;
    private WeakReference<N> mNavigator;

    public BaseViewModel(@NonNull Application application) {
        super(application);
        this.mCompositeDisposable = new CompositeDisposable();
    }

    @Override
    protected void onCleared() {
        mCompositeDisposable.dispose();
        super.onCleared();
    }

    public N getNavigator() {
        return mNavigator.get();
    }

    public void setNavigator(N navigator) {
        this.mNavigator = new WeakReference<>(navigator);
    }


}
