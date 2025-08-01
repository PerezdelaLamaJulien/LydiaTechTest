package com.jperez.lydia.feature.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jperez.lydia.domain.usecase.GetContactsUseCase
import com.jperez.lydia.feature.mapper.ListContactItemUIMapper
import com.jperez.lydia.feature.model.ListUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.inject

/**
 * ViewModel for managing the list of contacts.
 *
 * This ViewModel interacts with the [GetContactsUseCase] to fetch contacts
 * and uses [ListContactItemUIMapper] to map domain models to UI models.
 */

class ContactListViewModel : ViewModel() {

    private val usecase: GetContactsUseCase by inject(GetContactsUseCase::class.java)
    private val uiMapper: ListContactItemUIMapper by inject(ListContactItemUIMapper::class.java)

    private val _uiState = MutableStateFlow(ListUIState())
    val uiState: StateFlow<ListUIState> = _uiState

    /**
     * Fetches the list of contacts from the use case and updates the UI state.
     */
    fun getContacts() {
        viewModelScope.launch {
            _uiState.value = uiState.value.copy(
                isLoading = true,
            )

            val result = usecase.getContacts(seed = "default")

            if (result.isNotEmpty()) {
                _uiState.value = uiState.value.copy(
                    items = result.map { uiMapper.mapTo(it) },
                    isLoading = false,
                    errorMessage = null
                )
            } else {
                _uiState.value = uiState.value.copy(
                    isLoading = false,
                    errorMessage = "No contacts found"
                )
            }
        }
    }
}