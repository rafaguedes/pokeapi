package br.com.yourapp.pokeapi.screens.details.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import br.com.yourapp.pokeapi.R;
import br.com.yourapp.pokeapi.models.AbilityDetails;

public class AbilityListAdapter extends RecyclerView.Adapter {

    public ArrayList<AbilityDetails> abilityDetails;
    private Context context;

    public AbilityListAdapter(ArrayList<AbilityDetails> abilityDetails, Context context) {
        this.abilityDetails = abilityDetails;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_ability, parent, false);
        AbilityHolder holder = new AbilityHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        AbilityHolder holder = (AbilityHolder) viewHolder;
        AbilityDetails ability  = abilityDetails.get(position);
        holder.txtName.setText(ability.name);
    }

    @Override
    public int getItemCount() {
        return abilityDetails.size();
    }

    public class AbilityHolder extends RecyclerView.ViewHolder {
        public final TextView txtName;
        public AbilityHolder(View itemView) {
            super(itemView);
            this.txtName = itemView.findViewById(R.id.txtName);
        }
    }

    public void add(AbilityDetails ability){
        abilityDetails.add(ability);
        notifyDataSetChanged();
    }
}
