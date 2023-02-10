package dev.icerock.redwoodapp.screens.market

import dev.icerock.moko.fields.flow.FormField
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import dev.icerock.moko.resources.desc.StringDesc
import dev.icerock.moko.resources.desc.desc
import dev.icerock.redwoodapp.PRODUCTS_MOCK
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class MarketViewModel() : ViewModel() {
    val searchField = FormField<String, StringDesc?>(
        viewModelScope,
        "",
    ) { input -> input.map { null } }

    private val favoriteList = MutableStateFlow(listOf<String>())
    private val fullProductList = MutableStateFlow(PRODUCTS_MOCK)

    private val cart = MutableStateFlow<Map<String, Int>>(mapOf())

    val productList = fullProductList.combine(searchField.data) { list, search ->
        list.filter { search.isEmpty() || it.title.lowercase().contains(search.lowercase()) }
    }.combine(favoriteList) { list, favoriteList ->
        list.map { it.copy(isFavorite = favoriteList.contains(it.id)) }
    }.combine(cart) { list, cart ->
        list.map { it.copy(countInCart = cart.get(it.id) ?: 0) }
    }

    val isBannerVisible : StateFlow<Boolean> =
        searchField.data.map { it.isEmpty() }.stateIn(viewModelScope, SharingStarted.Lazily, true)

    val badge: StateFlow<StringDesc?> =
        cart.map {
            var count = 0
            it.values.forEach { count += it }
            if (count != 0) {
                count.toString().desc()
            } else {
                null
            }
        }.stateIn(viewModelScope, SharingStarted.Lazily, null)

    fun onNootificationClick() {
        // do something
    }

    fun onFavoriteClick(id: String) {
        if (favoriteList.value.contains(id)) {
            favoriteList.value = favoriteList.value.filter { it != id }
        } else {
            favoriteList.value = favoriteList.value.plus(id)
        }
    }

    fun addToCart(id: String) {
        val map = mutableMapOf<String, Int>()
        map.putAll(cart.value)
        map[id] = (map[id] ?: 0) + 1
        cart.value = map
    }

    fun removeFromCard(id: String) {
        val map = mutableMapOf<String, Int>()
        map.putAll(cart.value)
        map[id] = minOf(0, (map[id] ?: 0) - 1)
        cart.value = map
    }
}

data class Product(
    val id: String,
    val title: String,
    val imageUrl: String,
    val cost: String,
    val badge: String?,
    val oldCost: String?,
    val subtitle: String?,
    val date: String?,
    val isFavorite: Boolean = false,
    val countInCart: Int = 0,
)