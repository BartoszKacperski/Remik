package com.rolnik.remik.components;

import com.rolnik.remik.fragments.GameFragment;
import com.rolnik.remik.fragments.GameHistoryFragment;
import com.rolnik.remik.fragments.PlayerFragments;
import com.rolnik.remik.fragments.TopPlayersFragment;
import com.rolnik.remik.modules.DaosModule;
import com.rolnik.remik.modules.DatabaseModule;
import com.rolnik.remik.modules.RepositoriesModule;
import com.rolnik.remik.modules.RestDaoModule;
import com.rolnik.remik.modules.RetrofitModule;
import com.rolnik.remik.modules.SchedulersModule;
import com.rolnik.remik.modules.ServiceModule;

import javax.inject.Singleton;

import dagger.Component;
import io.reactivex.schedulers.Schedulers;

@Singleton
@Component(modules = {
        DatabaseModule.class,
        RepositoriesModule.class,
        DaosModule.class,
        RestDaoModule.class,
        RetrofitModule.class,
        SchedulersModule.class,
        ServiceModule.class
})
public interface FragmentComponent {
    void inject(PlayerFragments playerFragments);

    void inject(GameFragment gameFragment);

    void inject(TopPlayersFragment topPlayersFragment);

    void inject(GameHistoryFragment gameHistoryFragment);
}
