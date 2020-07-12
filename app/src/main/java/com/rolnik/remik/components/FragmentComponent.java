package com.rolnik.remik.components;

import com.rolnik.remik.fragments.GameFragment;
import com.rolnik.remik.fragments.GameHistoryFragment;
import com.rolnik.remik.fragments.PlayerFragments;
import com.rolnik.remik.fragments.TopPlayersFragment;
import com.rolnik.remik.modules.DaosModule;
import com.rolnik.remik.modules.DatabaseModule;
import com.rolnik.remik.modules.RepositoriesModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {DatabaseModule.class, RepositoriesModule.class, DaosModule.class})
public interface FragmentComponent {
    void inject(PlayerFragments playerFragments);
    void inject(GameFragment gameFragment);
    void inject(TopPlayersFragment topPlayersFragment);
    void inject(GameHistoryFragment gameHistoryFragment);
}
