package com.rolnik.remik;

import android.app.Application;

import com.rolnik.remik.components.DaggerFragmentComponent;
import com.rolnik.remik.components.DaggerServiceComponent;
import com.rolnik.remik.components.FragmentComponent;
import com.rolnik.remik.components.ServiceComponent;
import com.rolnik.remik.modules.DaosModule;
import com.rolnik.remik.modules.DatabaseModule;
import com.rolnik.remik.modules.RepositoriesModule;

public class MyApplication extends Application {
    private static MyApplication myApplication;
    private FragmentComponent fragmentComponent;
    private ServiceComponent serviceComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        myApplication = this;
        fragmentComponent = DaggerFragmentComponent.builder()
                .databaseModule(new DatabaseModule(getApplicationContext()))
                .daosModule(new DaosModule())
                .repositoriesModule(new RepositoriesModule())
                .build();

        serviceComponent = DaggerServiceComponent.builder()
                .databaseModule(new DatabaseModule(getApplicationContext()))
                .daosModule(new DaosModule())
                .repositoriesModule(new RepositoriesModule())
                .build();
    }

    public static MyApplication app(){
        return myApplication;
    }

    public FragmentComponent databaseComponent(){
        return fragmentComponent;
    }

    public ServiceComponent getServiceComponent() {
        return serviceComponent;
    }
}
