package com.rolnik.remik.components;

import com.rolnik.remik.modules.DaosModule;
import com.rolnik.remik.modules.DatabaseModule;
import com.rolnik.remik.modules.RepositoriesModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {DatabaseModule.class, RepositoriesModule.class, DaosModule.class})
public interface ServiceComponent {
}
