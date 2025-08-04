package com.jperez.lydia.feature.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.jperez.lydia.domain.usecase.DeleteSavedSeedUseCase
import com.jperez.lydia.domain.usecase.GetContactsUseCase
import com.jperez.lydia.domain.usecase.GetSavedSeedUseCase
import com.jperez.lydia.feature.mapper.ContactListItemUIMapper
import com.jperez.lydia.feature.model.ListUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.inject

/**
 * ViewModel for managing the list of contacts.
 *
 * This ViewModel interacts with the [GetContactsUseCase] to fetch contacts,
 * [GetSavedSeedUseCase] to retrieve saved seeds.
 * and uses [ContactListItemUIMapper] to map domain models to UI models.
 */

class ContactListViewModel : ViewModel() {

    private val getContactsUseCase: GetContactsUseCase by inject(GetContactsUseCase::class.java)
    private val getSavedSeedUseCase: GetSavedSeedUseCase by inject(GetSavedSeedUseCase::class.java)
    private val deleteSavedSeedUseCase: DeleteSavedSeedUseCase by inject(DeleteSavedSeedUseCase::class.java)

    private val _uiState = MutableStateFlow(ListUIState())
    val uiState: StateFlow<ListUIState> = _uiState

    init {
        getContacts()
        getSavedSeeds()
    }

    /**
     * Fetches the list of contacts from the use case and updates the UI state.
     */
    fun getContacts(seed: String = "default") {
        if (!uiState.value.savedSeeds.contains(seed)) {
            _uiState.value = uiState.value.copy(
                savedSeeds = uiState.value.savedSeeds + seed
            )
        }
        viewModelScope.launch {
            getContactsUseCase.execute(seed = seed)
                .distinctUntilChanged()
                .cachedIn(viewModelScope)
                .collect { pagingData ->
                    _uiState.value = uiState.value.copy(
                        items = pagingData,
                    )
                }
        }
    }

    /**
     * Fetches the saved seeds from the use case and updates the UI state.
     */
    fun getSavedSeeds() {
        viewModelScope.launch {
            val seeds = getSavedSeedUseCase.execute()
            _uiState.value = uiState.value.copy(
                savedSeeds = seeds
            )
        }
    }

    /**
     * Fetches the saved seeds from the use case and updates the UI state.
     */
    fun deleteSavedSeed(seed: String) {
        viewModelScope.launch {
            _uiState.value = uiState.value.copy(
                savedSeeds = uiState.value.savedSeeds - seed
            )

            deleteSavedSeedUseCase.execute(seed)
        }
    }
}