package com.example.quiznation.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.quiznation.Model.CapitalsModel;
import com.example.quiznation.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CapitalOptionsListAdapter extends RecyclerView.Adapter<CapitalOptionsListAdapter.CapitalOptionsViewHolder> {
    private CapitalsModel capitalsModel;
    private LayoutInflater layoutInflater;
    private Context context;
    private ItemClickListener mClickListener;

    public CapitalOptionsListAdapter(Context context,  ItemClickListener clickListener, CapitalsModel model) {
        this.capitalsModel = model;
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.mClickListener = clickListener;
    }


    @NonNull
    @Override
    public CapitalOptionsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = layoutInflater.inflate(R.layout.capitals_list_item, parent, false);
        return new CapitalOptionsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CapitalOptionsViewHolder holder, int position) {
        //Get the required value
        holder.optionsText.setText(capitalsModel.getOptionList().get(position));
    }

    @Override
    public int getItemCount() {
        return capitalsModel.getOptionList().size();
    }



    public class CapitalOptionsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView optionsText;

        public CapitalOptionsViewHolder(@NonNull View itemView) {
            super(itemView);
            optionsText = itemView.findViewById(R.id.optionsText);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(optionsText.getText().toString(), getAdapterPosition(), capitalsModel);

        }
    }

    // Parent Activity Will Implement this Method to Respond to Click Events
    public interface ItemClickListener {
        void onItemClick(String optionChosen, int position, CapitalsModel model);
    }
}

