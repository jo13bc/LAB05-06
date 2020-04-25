package com.miker.login;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Luis Carrillo Rodriguez on 18/4/2018.
 */
public class AplicationAdapter extends RecyclerView.Adapter<AplicationAdapter.MyViewHolder> implements Filterable {

    private List<Aplication> aplicationList;
    private List<Aplication> aplicationListFiltered;
    private aplicationAdapterListener listener;
    private Aplication object;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView first_name, last_name;
        public RelativeLayout viewForeground, viewBackgroundDelete, viewBackgroundEdit;

        public MyViewHolder(View view) {
            super(view);
            first_name = (TextView) view.findViewById(R.id.first_name);
            last_name = (TextView) view.findViewById(R.id.last_name);
            viewBackgroundDelete = view.findViewById(R.id.view_background_delete);
            viewBackgroundEdit = view.findViewById(R.id.view_background_edit);
            viewForeground = view.findViewById(R.id.view_foreground);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // send selected contact in callback
                    listener.onSelected(aplicationListFiltered.get(getAdapterPosition()));
                }
            });
        }
    }

    public AplicationAdapter(List<Aplication> aplicationList, aplicationAdapterListener listener) {
        this.aplicationList = aplicationList;
        this.listener = listener;
        this.aplicationListFiltered = aplicationList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_form, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final Aplication aplication = aplicationListFiltered.get(position);
        holder.first_name.setText(aplication.getFirst_name());
        holder.last_name.setText(aplication.getLast_name());
    }

    @Override
    public int getItemCount() {
        return aplicationListFiltered.size();
    }

    public void removeItem(int position) {
        object = aplicationListFiltered.remove(position);
        Iterator<Aplication> iter = aplicationList.iterator();
        while (iter.hasNext()) {
            Aplication aux = iter.next();
            if (object.equals(aux))
                iter.remove();
        }
        // notify item removed
        notifyItemRemoved(position);
    }

    public void restoreItem(int position) {

        if (aplicationListFiltered.size() == aplicationList.size()) {
            aplicationListFiltered.add(position, object);
        } else {
            aplicationListFiltered.add(position, object);
            aplicationList.add(object);
        }
        notifyDataSetChanged();
        // notify item added by position
        notifyItemInserted(position);
    }

    public Aplication getSwipedItem(int index) {
        if (this.aplicationList.size() == this.aplicationListFiltered.size()) { //not filtered yet
            return aplicationList.get(index);
        } else {
            return aplicationListFiltered.get(index);
        }
    }

    public void onItemMove(int fromPosition, int toPosition) {
        if (aplicationList.size() == aplicationListFiltered.size()) { // without filter
            if (fromPosition < toPosition) {
                for (int i = fromPosition; i < toPosition; i++) {
                    Collections.swap(aplicationList, i, i + 1);
                }
            } else {
                for (int i = fromPosition; i > toPosition; i--) {
                    Collections.swap(aplicationList, i, i - 1);
                }
            }
        } else {
            if (fromPosition < toPosition) {
                for (int i = fromPosition; i < toPosition; i++) {
                    Collections.swap(aplicationListFiltered, i, i + 1);
                }
            } else {
                for (int i = fromPosition; i > toPosition; i--) {
                    Collections.swap(aplicationListFiltered, i, i - 1);
                }
            }
        }
        notifyItemMoved(fromPosition, toPosition);
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    aplicationListFiltered = aplicationList;
                } else {
                    List<Aplication> filteredList = new ArrayList<>();
                    for (Aplication row : aplicationList) {
                        // filter use two parameters
                        if (row.getFirst_name().toLowerCase().contains(charString.toLowerCase()) || row.getLast_name().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(row);
                        }
                    }

                    aplicationListFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = aplicationListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                aplicationListFiltered = (ArrayList<Aplication>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public interface aplicationAdapterListener {
        void onSelected(Aplication aplication);
    }
}

