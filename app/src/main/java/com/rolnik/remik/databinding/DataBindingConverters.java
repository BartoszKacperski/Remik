package com.rolnik.remik.databinding;

import android.widget.TextView;

import androidx.databinding.BindingAdapter;
import androidx.databinding.ObservableList;

import com.annimon.stream.Stream;
import com.rolnik.remik.R;
import com.rolnik.remik.model.GameHistory;
import com.rolnik.remik.model.PlayerWithGameHistory;
import com.rolnik.remik.model.PlayerWithPoints;
import com.rolnik.remik.utils.OnListChanged;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DataBindingConverters {
    private final static String DATE_PATTERN = "MM/dd/yyyy HH:mm:ss";

    private DataBindingConverters() {

    }

    @BindingAdapter("bindPoints")
    public static void bindPoints(final TextView textView, final PlayerWithGameHistory playerWithGameHistory) {
        int sumOfPoints = Stream.of(playerWithGameHistory.getGameHistories())
                .mapToInt(GameHistory::getPoints)
                .sum();

        String text = sumOfPoints + " " + textView.getContext().getString(R.string.points);

        textView.setText(text);
    }

    @BindingAdapter("bindPoints")
    public static void bindPoints(final TextView textView, final PlayerWithPoints playerWithPoints) {
        String text = playerWithPoints.getCurrentPoints() + " " + textView.getContext().getString(R.string.points);

        textView.setText(text);
    }

    @BindingAdapter("bindGamesAmount")
    public static void bindGamesAmount(final TextView textView, final PlayerWithGameHistory playerWithGameHistory) {
        String text = playerWithGameHistory.getGameHistories().size() + " " + textView.getContext().getString(R.string.number_of_games);

        textView.setText(text);
    }

    @BindingAdapter("bindAvgPoints")
    public static void bindAvgPoints(final TextView textView, final PlayerWithGameHistory playerWithGameHistory) {
        int sumOfPoints = Stream.of(playerWithGameHistory.getGameHistories())
                .mapToInt(GameHistory::getPoints)
                .sum();

        int numberOfGames = playerWithGameHistory.getGameHistories().size();

        float avgPoints = numberOfGames == 0 ? 0 : (float) sumOfPoints / numberOfGames;

        String text = String.format(Locale.getDefault(), "%.2f ", avgPoints) + textView.getContext().getString(R.string.avg_points);

        textView.setText(text);
    }

    @BindingAdapter("bindPoints")
    public static void bindPoints(final TextView textView, final ObservableList<Integer> points) {
        String pointText = textView.getContext().getString(R.string.points);
        points.addOnListChangedCallback(new OnListChanged() {
            @Override
            public void onChange(ObservableList<Integer> sender) {
                textView.setText(getSumOfPointsText(sender, pointText));
            }
        });

        textView.setText(getSumOfPointsText(points, pointText));
    }

    @BindingAdapter("bindDate")
    public static void bindDate(final TextView textView, final Date date) {
        DateFormat df = new SimpleDateFormat(DATE_PATTERN);

        textView.setText(df.format(date));
    }

    private static String getSumOfPointsText(final ObservableList<Integer> points, String pointText) {
        return String.valueOf(sumOfPoints(points));
    }

    private static int sumOfPoints(final ObservableList<Integer> points) {
        return Stream.of(points)
                .mapToInt(i -> i)
                .sum();
    }
}
