package com.rolnik.remik;

import android.app.Application;

import com.rolnik.remik.components.DaggerFragmentComponent;
import com.rolnik.remik.components.FragmentComponent;
import com.rolnik.remik.modules.DaosModule;
import com.rolnik.remik.modules.DatabaseModule;
import com.rolnik.remik.modules.RepositoriesModule;
import com.rolnik.remik.modules.RestDaoModule;
import com.rolnik.remik.modules.RetrofitModule;
import com.rolnik.remik.modules.SchedulersModule;
import com.rolnik.remik.modules.ServiceModule;

public class MyApplication extends Application {
    private static MyApplication myApplication;
    private FragmentComponent fragmentComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        myApplication = this;
        fragmentComponent = DaggerFragmentComponent.builder()
                .databaseModule(new DatabaseModule(getApplicationContext()))
                .daosModule(new DaosModule())
                .repositoriesModule(new RepositoriesModule())
                .retrofitModule(new RetrofitModule(this))
                .restDaoModule(new RestDaoModule())
                .schedulersModule(new SchedulersModule())
                .serviceModule(new ServiceModule())
                .build();

    }

    public static MyApplication app(){
        return myApplication;
    }

    public FragmentComponent databaseComponent(){
        return fragmentComponent;
    }
}
