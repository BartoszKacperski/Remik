package com.rolnik.remik.viewholders;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rolnik.remik.R;
import com.vstechlab.easyfonts.EasyFonts;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PointViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.point)
    TextView pointText;

    public PointViewHolder(@NonNull View itemView, Context context) {
        super(itemView);
        ButterKnife.bind(this, itemView);

        pointText.setTypeface(EasyFonts.walkwayObliqueUltraBold(context));
    }

    public void bindPoint(final int point){
        pointText.setText(String.valueOf(point));
    }
}
