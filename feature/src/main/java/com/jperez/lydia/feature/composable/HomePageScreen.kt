package com.jperez.lydia.feature.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.jperez.lydia.feature.theme.LydiaTheme
import com.jperez.lydia.feature.viewmodel.ContactListViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomePageScreen(viewModel: ContactListViewModel = koinViewModel()) {
    val state = viewModel.uiState.collectAsState()
    LydiaTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(
                    onClick = {
                        viewModel.getContacts()
                    },
                ) {
                    Text(text = "Click Me")
                }

                if(state.value.isLoading){
                    CircularProgressIndicator()
                }
                else if(state.value.errorMessage != null){
                    Text(text = state.value.errorMessage ?: "Unknown error")
                }
                else if(state.value.items.isNotEmpty()){
                    LazyColumn(modifier = Modifier.fillMaxHeight()) {
                        items(state.value.items, itemContent = { contact ->
                            Greeting(contact.name)
                        })
                    }
                }
            }
        }
    }
}