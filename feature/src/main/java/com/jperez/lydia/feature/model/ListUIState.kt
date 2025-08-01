package com.jperez.lydia.feature.model

data class ListUIState(
    val items: List<ListContactItemUI> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
)