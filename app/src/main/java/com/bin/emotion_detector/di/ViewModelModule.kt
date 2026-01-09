package com.bin.emotion_detector.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import com.bin.emotion_detector.presentation.home.HomeViewModel


val viewModelModule = module {

           viewModel { HomeViewModel(get()) }


}