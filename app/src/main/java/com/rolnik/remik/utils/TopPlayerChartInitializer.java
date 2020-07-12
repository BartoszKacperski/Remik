package com.rolnik.remik.utils;

import android.content.Context;

import androidx.core.content.ContextCompat;

import com.db.chart.animation.Animation;
import com.db.chart.model.LineSet;
import com.db.chart.model.Point;
import com.db.chart.util.Tools;
import com.db.chart.view.LineChartView;
import com.rolnik.remik.R;
import com.rolnik.remik.model.GameHistory;

import java.util.List;

import lombok.AllArgsConstructor;

@AllArgsConstructor

public class TopPlayerChartInitializer {
    private final Context context;
    private final LineChartView lineChartView;

    public void init(final List<GameHistory> gameHistories){
        if(!gameHistories.isEmpty()){
            LineSet chartSet = new LineSet();
            chartSet.setColor(getColor(R.color.tangarine));
            chartSet.setSmooth(true);

            lineChartView.setAxisColor(getColor(R.color.darkNavy));

            for (int i = 0; i < gameHistories.size(); i++) {
                GameHistory gameHistory = gameHistories.get(i);

                String label = String.valueOf(i);
                float value = gameHistory.getPoints();

                Point point = getChartPoint(label, value);

                chartSet.addPoint(point);
            }

            adjustBorderValues(lineChartView, chartSet.getMin().getValue(), chartSet.getMax().getValue());

            lineChartView.getData().clear();
            lineChartView.addData(chartSet);
        }
    }

    public Animation getShowAnimation() {
        return new Animation()
                .fromAlpha(0)
                .setDuration(3000)
                .fromXY(0, .5f);
    }

    private int getColor(int resId) {
        return ContextCompat.getColor(context, resId);
    }

    private Point getChartPoint(String label, float value) {
        Point point = new Point(label, value);

        point.setColor(getColor(R.color.tangarine));
        point.setStrokeColor(getColor(R.color.darkNavy));

        if (value > 0.f) {
            point.setRadius(Tools.fromDpToPx(4.f));
            point.setStrokeThickness(Tools.fromDpToPx(1.f));
        } else {
            point.setRadius(Tools.fromDpToPx(6.f));
            point.setStrokeThickness(Tools.fromDpToPx(2.f));
        }

        return point;
    }

    private void adjustBorderValues(LineChartView lineChartView, float min, float max) {
        int minBorder = (int) min;
        int maxBorder = (int) max;

        maxBorder = maxBorder == minBorder ? maxBorder + 1 : maxBorder;

        lineChartView.setAxisBorderValues(minBorder, maxBorder);
    }
}
