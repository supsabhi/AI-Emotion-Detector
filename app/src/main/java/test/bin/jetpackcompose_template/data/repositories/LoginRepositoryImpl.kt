package test.bin.jetpackcompose_template.data.repositories

import android.content.Context
import test.bin.jetpackcompose_template.domain.network_util.Connectivity
import test.bin.jetpackcompose_template.domain.network_util.LoginAPI
import test.bin.jetpackcompose_template.domain.repositories.LoginRepository


class LoginRepositoryImpl(
    private val loginApi: LoginAPI,
    private val connectivity: Connectivity,
    private val context: Context
) : LoginRepository {

    /* override suspend fun getDatabaseDetails(url: String): CommonResponse<ArrayList<DatabaseDetails>>? {
         val response = CommonResponse<ArrayList<DatabaseDetails>>()
         return if (connectivity.hasNetwork()) {
             try {
                 val result = loginApi.getDatabases(url)

                 if (result.status == true) {
                     result
                 } else {
                     response.message = result.message
                     response.status = result.status
                     response
                 }
             } catch (e: Exception) {
                 response.message = e.message
                 e.message?.let { Log.d("API call Failed: %s", it) }
                 response
             }
         } else {
             response.message = ("No internet Connection")
             response
         }

     }*/


}

