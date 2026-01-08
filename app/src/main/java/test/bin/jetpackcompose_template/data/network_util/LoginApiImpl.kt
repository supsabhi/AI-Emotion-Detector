package test.bin.jetpackcompose_template.data.network_util


import android.util.Log
import io.ktor.client.HttpClient
import io.ktor.client.features.HttpRedirect
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logger
import io.ktor.client.features.logging.Logging
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.contentType
import test.bin.jetpackcompose_template.domain.model.CommonResponse
import test.bin.jetpackcompose_template.domain.network_util.APICallWrapper
import test.bin.jetpackcompose_template.domain.network_util.LoginAPI
class LoginAPIImpl(
    private val client: HttpClient = HttpClient {
        install(HttpRedirect) {
            checkHttpMethod = false // allows redirect on all HTTP methods
        }
        install(Logging) {
            level = LogLevel.ALL
            logger = object : Logger {
                override fun log(message: String) {
                    Log.d("HTTP_CLIENT", message)
                }
            }
        }
    }
) : LoginAPI {
    private val apiCallWrapperClass = APICallWrapper()


/*
    override suspend fun getDatabases(url: String): CommonResponse<ArrayList<DatabaseDetails>> {
        return apiCallWrapperClass.apiCallWrapper {
            // client.get(BASE_URL_WITHOUTDB) {
            client.get(url) {
                header(HttpHeaders.Accept, "application/json")
            }
        }
    }

    override suspend fun auhenticate(
        authenticationRequest: HashMap<String, Any>, url: String
    ): CommonResponse<AuthenticationResponse> {
        return apiCallWrapperClass.apiCallWrapper {
            // client.post<CommonResponse<AuthenticationResponse>>(BASE_URL_API+"users/authenticate")
            client.post<CommonResponse<AuthenticationResponse>>(url)
            {
                contentType(ContentType.Application.Json)
                header(HttpHeaders.Accept, "application/json")
                body = authenticationRequest
            }
        }
    }*/
















}
