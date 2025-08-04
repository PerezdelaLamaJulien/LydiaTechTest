package com.jperez.lydia.feature.model

import androidx.paging.PagingData
import com.jperez.lydia.domain.model.Contact

data class ListUIState(
    val items: PagingData<Contact> = PagingData.empty(),
    val savedSeeds: List<String> = emptyList(),
    )