package com.edu.uac.co.parcial_sqlite;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.UserViewHolder> implements Filterable {

    private UserListener userListener;
    private List<Usuario> dataUsers;
    private List<Usuario> dataUsersAll;

    public RecyclerViewAdapter(List<Usuario> dataUsers, UserListener userListener) {

        this.userListener = userListener;
        this.dataUsers = dataUsers;
        dataUsersAll = new ArrayList<>();
        dataUsersAll.addAll(dataUsers);

    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return  new UserViewHolder((view));
    }



    public class UserViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public CheckBox selectionCB;
        public TextView txtVIds;
        public TextView txtVNames;
        public TextView txtVStratums;
        public TextView txtVWage;
        public TextView txtVEduLvl;


        public UserViewHolder(@NonNull View itemView) {
            super(itemView);

            init(itemView);

            selectionCB.setOnClickListener(this);

        }

        public void init(View view){
            selectionCB = view.findViewById(R.id.selectCheckBox);
            txtVIds = view.findViewById(R.id.idTextV);
            txtVNames = view.findViewById(R.id.nameTxtV);
            txtVStratums =  view.findViewById(R.id.stratumTxtV);
            txtVWage =  view.findViewById(R.id.wageTxtV);
            txtVEduLvl =  view.findViewById(R.id.eduLvlTxtV);
        }

        @Override
        public void onClick(View v) {
            userListener.itemClicked(dataUsers.get(getLayoutPosition()));
        }
    }

    @Override
    public int getItemCount() {
        return dataUsers.size();
    }

    @Override
    public Filter getFilter() {
        return myFilter;
    }

    Filter myFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {

            List<Usuario> filteredList = new ArrayList<>();

            if (charSequence == null || charSequence.length() == 0) {
                filteredList.addAll(dataUsersAll);
            } else {
                for (Usuario usuario : dataUsersAll) {
                    if(usuario.documento.contains(charSequence.toString())) {
                        filteredList.add(usuario);
                    }
                }

            }

            FilterResults filterResults = new FilterResults();
            filterResults.values = filteredList;
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults results) {

            dataUsers.clear();
            dataUsers.addAll((Collection<? extends Usuario>) results.values);
            notifyDataSetChanged();
        }
    };

    public void deleteUser(Usuario usuario) {
        this.dataUsers.remove(usuario);
        notifyDataSetChanged();
    }

    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {

        Usuario user = dataUsers.get(position);

        holder.txtVIds.setText(user.documento);
        holder.txtVNames.setText(user.nombre);
        holder.txtVStratums.setText(user.estrato);
        holder.txtVWage.setText(user.salario);
        holder.txtVEduLvl.setText(user.nivelEducativo);

    }

    public void setFilter(ArrayList<Usuario> newList) {
        dataUsers = new ArrayList<>();
        dataUsers.addAll(newList);
        notifyDataSetChanged();

    }
}