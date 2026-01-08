package test.bin.jetpackcompose_template.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class CommonResponse<T>(var status: Boolean? = null) {
    var data: T? = null
    var message: String? = null
}


