package test.bin.jetpackcompose_template.presentation.navigation

sealed class Screen(val route: String) {
    fun withNonNullArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }

    fun withOptionalArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("?$arg=$arg")
            }
        }
    }

    object Splash : Screen(SPLASH)
    object HomeSetting : Screen(HOMESETTING)
}