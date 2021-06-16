package com.example.quiznation.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.quiznation.Model.CountryModel;
import com.example.quiznation.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CountryOptionsListAdapter extends RecyclerView.Adapter<CountryOptionsListAdapter.CountryOptionsViewHolder> {
    private CountryModel model;
    private LayoutInflater layoutInflater;
    private Context context;
    private ItemClickListener mClickListener;

    public CountryOptionsListAdapter(Context context,  ItemClickListener clickListener, CountryModel model) {
        this.model = model;
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.mClickListener = clickListener;
    }


    @NonNull
    @Override
    public CountryOptionsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = layoutInflater.inflate(R.layout.countries_list_item, parent, false);
        return new CountryOptionsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CountryOptionsViewHolder holder, int position) {
        //Get the required value
        holder.optionsText.setText(model.getOptionList().get(position));
    }

    @Override
    public int getItemCount() {
        return model.getOptionList().size();
    }



    public class CountryOptionsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView optionsText;

        public CountryOptionsViewHolder(@NonNull View itemView) {
            super(itemView);
            optionsText = itemView.findViewById(R.id.optionsButton);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(optionsText.getText().toString(), getAdapterPosition(), model);

        }
    }

    // Parent Activity Will Implement this Method to Respond to Click Events
    public interface ItemClickListener {
        void onItemClick(String optionChosen, int position, CountryModel model);
    }
}
