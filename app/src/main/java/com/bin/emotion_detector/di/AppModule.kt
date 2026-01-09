package com.bin.emotion_detector.di

import org.koin.dsl.module
import com.bin.emotion_detector.core.ResourcesProvider

val appModule = module {
    single {
        ResourcesProvider(get())
    }
}
