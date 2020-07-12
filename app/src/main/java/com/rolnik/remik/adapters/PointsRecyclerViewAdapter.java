package com.rolnik.remik.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableList;
import androidx.recyclerview.widget.RecyclerView;

import com.rolnik.remik.R;
import com.rolnik.remik.viewholders.PointViewHolder;



public class PointsRecyclerViewAdapter extends RecyclerView.Adapter<PointViewHolder> {
    private final ObservableList<Integer> points;
    private final Context context;

    private final ObservableList.OnListChangedCallback<ObservableList<Integer>> callback = new ObservableList.OnListChangedCallback<ObservableList<Integer>>() {
        @Override
        public void onChanged(ObservableList<Integer> sender) {
            notifyDataSetChanged();
        }

        @Override
        public void onItemRangeChanged(ObservableList<Integer> sender, int positionStart, int itemCount) {
            notifyItemRangeChanged(positionStart, itemCount);
        }

        @Override
        public void onItemRangeInserted(ObservableList<Integer> sender, int positionStart, int itemCount) {
            notifyItemRangeInserted(positionStart, itemCount);
        }

        @Override
        public void onItemRangeMoved(ObservableList<Integer> sender, int fromPosition, int toPosition, int itemCount) {
            notifyItemMoved(fromPosition, toPosition);
        }

        @Override
        public void onItemRangeRemoved(ObservableList<Integer> sender, int positionStart, int itemCount) {
            notifyItemRangeRemoved(positionStart, itemCount);
        }
    };

    public PointsRecyclerViewAdapter(ObservableList<Integer> points, Context context) {
        this.points = points;
        this.points.addOnListChangedCallback(callback);
        this.context = context;
    }

    @NonNull
    @Override
    public PointViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.point, parent, false);

        return new PointViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(@NonNull PointViewHolder holder, int position) {
        int point = points.get(position);

        holder.bindPoint(point);
    }

    @Override
    public int getItemCount() {
        return points.size();
    }
}
