package test.bin.jetpackcompose_template.data.network_util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import test.bin.jetpackcompose_template.domain.network_util.Connectivity

class ConnectivityImpl(private val context: Context) : Connectivity {
    override fun hasNetwork(): Boolean {
        var result = false
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkCapabilities = connectivityManager.activeNetwork ?: return false
        val actNw =
            connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false
        result = when {
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }
        return result
    }
}