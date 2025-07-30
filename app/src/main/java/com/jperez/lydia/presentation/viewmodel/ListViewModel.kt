package com.jperez.lydia.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jperez.lydia.domain.usecase.GetContactsUseCase
import com.jperez.lydia.presentation.mapper.ListContactItemUIMapper
import com.jperez.lydia.presentation.model.ListUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.inject

class ListViewModel : ViewModel() {

    private val usecase: GetContactsUseCase by inject(GetContactsUseCase::class.java)
    private val uiMapper: ListContactItemUIMapper by inject(ListContactItemUIMapper::class.java)

    private val _uiState = MutableStateFlow(ListUIState())
    val uiState: StateFlow<ListUIState> = _uiState

    fun getContacts() {
        viewModelScope.launch {
            _uiState.value = uiState.value.copy(
                isLoading = true,
            )

            val result = usecase.getContacts(seed = "default")

            _uiState.value = if (result.isNotEmpty()) {
                ListUIState(
                    items = result.map { uiMapper.mapTo(it) },
                    isLoading = false,
                    errorMessage = null
                )
            } else {
                ListUIState(
                    isLoading = false,
                    errorMessage = "No contacts found"
                )
            }
        }
    }
}