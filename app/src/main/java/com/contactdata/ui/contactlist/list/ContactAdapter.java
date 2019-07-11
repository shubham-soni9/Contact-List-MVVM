package com.contactdata.ui.contactlist.list;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.contactdata.R;
import com.contactdata.databinding.ItemContactBinding;
import com.contactdata.ui.base.BaseViewHolder;
import com.contactdata.ui.contactdetails.ContactDetailsActivity;
import com.contactdata.utils.AppConstants;

import java.util.ArrayList;
import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<BaseViewHolder> {
    private List<ContactItemViewModel> contactList;

    public ContactAdapter() {
        this.contactList = new ArrayList<>();
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemContactBinding openSourceViewBinding = ItemContactBinding
                .inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ContactViewHolder(openSourceViewBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.onBind(position);

    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }

    public void clearItems() {
        contactList.clear();
    }

    public void addItems(List<ContactItemViewModel> repoList) {
        contactList.addAll(repoList);
        notifyDataSetChanged();
    }

    public class ContactViewHolder extends BaseViewHolder implements View.OnClickListener {

        private final ItemContactBinding mBinding;

        public ContactViewHolder(ItemContactBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
        }

        @Override
        public void onBind(int position) {
            final ContactItemViewModel mContactItemViewModel = contactList.get(position);
            mBinding.setViewModel(mContactItemViewModel);

            Glide.with(mBinding.ivProfile.getContext()).load(contactList.get(position).imageUrl.get())
                    .apply(new RequestOptions().fitCenter().diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                            .placeholder(R.drawable.ic_profile).circleCrop()).into(mBinding.ivProfile);

            mBinding.executePendingBindings();
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(itemView.getContext(), ContactDetailsActivity.class);
            intent.putExtra(AppConstants.Extras.CONTACT_ID, contactList.get(getAdapterPosition()).id.get());
            itemView.getContext().startActivity(intent);
        }
    }
}
