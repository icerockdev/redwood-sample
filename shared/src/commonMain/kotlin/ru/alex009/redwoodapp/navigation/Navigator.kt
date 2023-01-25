package ru.alex009.redwoodapp.navigation

interface Navigator {
    fun navigate(uri: String)

    fun popBackStack()
}
