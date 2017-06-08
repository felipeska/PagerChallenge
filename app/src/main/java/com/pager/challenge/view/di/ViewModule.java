package com.pager.challenge.view.di;

import com.pager.challenge.domain.di.UseCasesModule;
import com.pager.challenge.presentation.di.PresentersModule;
import dagger.Module;

@Module(includes = {
    PresentersModule.class,
    UseCasesModule.class
}) public class ViewModule {
}
