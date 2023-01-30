package dev.icerock.redwoodapp.navigation

interface Navigator {
    fun navigate(uri: String)

    fun popBackStack()
}
