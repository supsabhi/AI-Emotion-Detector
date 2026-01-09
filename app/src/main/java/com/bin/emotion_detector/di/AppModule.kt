package com.bin.emotion_detector.di

import com.bin.emotion_detector.core.ResourcesProvider
import org.koin.dsl.module

val appModule = module {
    single {
        ResourcesProvider(get())
    }
}
