package com.bin.emotion_detector.di

import com.bin.emotion_detector.presentation.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val viewModelModule = module {

    viewModel { HomeViewModel(get()) }


}