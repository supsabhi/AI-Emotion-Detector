package test.bin.jetpackcompose_template.domain.network_util


import android.util.Log
import test.bin.jetpackcompose_template.domain.model.CommonResponse


class APICallWrapper {
    suspend fun <T> apiCallWrapper(apiCall: suspend () -> CommonResponse<T>): CommonResponse<T> {
        val response = CommonResponse<T>()
        try {
            val result = apiCall()
            response.status = result.status
            response.data = result.data
            response.message = result.message

            Log.d("TAG", "API call successful:" + result.data)
        } catch (e: Exception) {

            response.message = "Unexpected Error: ${e.message}"
            e.message?.let { Log.d("API call Failed: %s", it) }
        }
        return response
    }
}
