package test.bin.jetpackcompose_template.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import test.bin.jetpackcompose_template.presentation.homeSettings.HomeSettingsViewModel


val viewModelModule = module {
   // viewModel { ScanViewModel(get()) }
           viewModel { HomeSettingsViewModel(get()) }


}