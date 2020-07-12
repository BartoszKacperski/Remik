package com.rolnik.remik;

import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.eftimoff.viewpagertransformers.CubeOutTransformer;
import com.eftimoff.viewpagertransformers.FlipVerticalTransformer;
import com.eftimoff.viewpagertransformers.StackTransformer;
import com.google.android.material.tabs.TabLayout;
import com.rolnik.remik.adapters.PagerAdapter;
import com.rolnik.remik.fragments.GameFragment;
import com.rolnik.remik.fragments.GameHistoryFragment;
import com.rolnik.remik.fragments.PlayerFragments;
import com.rolnik.remik.fragments.TopPlayersFragment;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setUpFragments();
        setUpTabLayoutIcons();
        setUpViewPagerTransform();
    }

    private void setUpFragments() {
        PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager());

        pagerAdapter.addFragment(getString(R.string.game), new GameFragment());
        pagerAdapter.addFragment(getString(R.string.players), new PlayerFragments());
        pagerAdapter.addFragment(getString(R.string.top_players), new TopPlayersFragment());
        pagerAdapter.addFragment(getString(R.string.history), new GameHistoryFragment());

        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setUpTabLayoutIcons() {
        TabLayout.Tab gameTab = tabLayout.getTabAt(0);

        if(gameTab != null){
            gameTab.setIcon(R.drawable.playing_cards);
        }

        TabLayout.Tab playersTab = tabLayout.getTabAt(1);

        if(playersTab != null){
            playersTab.setIcon(R.drawable.users);
        }

        TabLayout.Tab topPlayersTab = tabLayout.getTabAt(2);

        if(topPlayersTab != null){
            topPlayersTab.setIcon(R.drawable.podium);
        }

        TabLayout.Tab historyTab = tabLayout.getTabAt(3);

        if(historyTab != null){
            historyTab.setIcon(R.drawable.history);
        }

        for(TabLayout.Tab tab : Arrays.asList(gameTab, playersTab, topPlayersTab, historyTab)){
            Drawable icon = tab.getIcon();

            if(icon != null){
                icon.setColorFilter(getIconColor(), PorterDuff.Mode.SRC_ATOP);
            }
        }


    }

    private void setUpViewPagerTransform() {
        viewPager.setPageTransformer(true, new StackTransformer());
    }

    private int getIconColor(){
        return this.getResources()
                .getColor(R.color.tangarine);
    }
}
