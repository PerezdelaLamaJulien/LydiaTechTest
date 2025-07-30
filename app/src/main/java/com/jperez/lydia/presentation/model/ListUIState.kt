package com.jperez.lydia.presentation.model

data class ListUIState(
    val items: List<ListContactItemUI> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null,

    )