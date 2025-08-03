package com.jperez.lydia.feature.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.jperez.lydia.domain.usecase.GetContactsUseCase
import com.jperez.lydia.feature.mapper.ContactListItemUIMapper
import com.jperez.lydia.feature.model.ListUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.inject

/**
 * ViewModel for managing the list of contacts.
 *
 * This ViewModel interacts with the [GetContactsUseCase] to fetch contacts
 * and uses [ContactListItemUIMapper] to map domain models to UI models.
 */

class ContactListViewModel : ViewModel() {

    private val usecase: GetContactsUseCase by inject(GetContactsUseCase::class.java)

    private val _uiState = MutableStateFlow(ListUIState())
    val uiState: MutableStateFlow<ListUIState> = _uiState

    init {
        getContacts()
    }

    /**
     * Fetches the list of contacts from the use case and updates the UI state.
     */
    fun getContacts(seed : String = "default") {
        viewModelScope.launch {
            _uiState.value = uiState.value.copy(
                isLoading = true,
            )

            usecase.getContacts(seed = seed)
                .distinctUntilChanged()
                .cachedIn(viewModelScope)
                .collect { pagingData ->
                    _uiState.value = uiState.value.copy(
                        items = pagingData,
                        isLoading = false,
                    )
                }
        }
    }
}