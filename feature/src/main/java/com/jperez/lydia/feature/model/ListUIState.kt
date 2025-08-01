package com.jperez.lydia.feature.model

import androidx.paging.PagingData

data class ListUIState(
    val items: PagingData<ListContactItemUI> = PagingData.empty(),
    val isLoading: Boolean = false,
    )