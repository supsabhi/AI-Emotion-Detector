package test.bin.jetpackcompose_template.di

import org.koin.dsl.module
import test.bin.jetpackcompose_template.core.ResourcesProvider

val appModule = module {
    single {
        ResourcesProvider(get())
    }
}
