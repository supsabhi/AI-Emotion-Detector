package test.bin.jetpackcompose_template.di


import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import test.bin.jetpackcompose_template.data.network_util.ConnectivityImpl
import test.bin.jetpackcompose_template.data.repositories.LoginRepositoryImpl
import test.bin.jetpackcompose_template.domain.network_util.Connectivity
import test.bin.jetpackcompose_template.domain.repositories.LoginRepository


val repoModule = module {
    factory<Connectivity> { ConnectivityImpl(androidContext()) }
    factory<LoginRepository> { LoginRepositoryImpl(get(), get(), get()) }
   /* factory<ServicesRepository> { ServicesRepositoryImpl(get(), get(), get(),get()) }
    factory<MqttRepository> { MqttRepositoryImpl(get(), get(), get()) }
    factory<LedgerRepository> { LedgerRepositoryImpl(get(), get(), get()) }
    factory<DepositRepository> { DepositRepositoryImpl(get(), get(), get()) }
    factory<PurchaseRepository> { PurchaseRepositoryImpl(get(), get(), get()) }*/
}