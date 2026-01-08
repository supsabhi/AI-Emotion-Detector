package test.bin.jetpackcompose_template.core

import android.content.Context
import androidx.annotation.StringRes

class ResourcesProvider(
     private val context: Context
) {
    fun getString(@StringRes stringResId: Int): String {
        return context.resources.getString(stringResId)
    }
}