package com.jperez.lydia.feature.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.jperez.lydia.domain.model.Contact
import com.jperez.lydia.feature.viewmodel.ContactListViewModel
import kotlinx.coroutines.flow.flow
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactListScreen(
    onShowDetails: (Contact) -> Unit,
    viewModel: ContactListViewModel = koinViewModel()
) {
    val state = viewModel.uiState.collectAsState()

    val pagingItems = remember(state.value.items) {
        flow {
            emit(state.value.items)
        }
    }.collectAsLazyPagingItems()

    Scaffold(modifier = Modifier.fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        "Contact List",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
            )
        }) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(
                    horizontal = 8.dp
                ),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            val textFieldState = remember { mutableStateOf("") }

            OutlinedTextField(
                value = textFieldState.value,
                onValueChange = { newText ->
                    textFieldState.value = newText
                },
                label = { Text("Search Contacts") },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                trailingIcon = {
                    if (textFieldState.value.isNotEmpty()) {
                        Row {
                            IconButton(onClick = {
                                viewModel.getContacts(seed = textFieldState.value)
                            }) {
                                Icon(Icons.Default.Search, contentDescription = "Search contacts")
                            }

                            IconButton(onClick = {
                                textFieldState.value = ""
                            }) {
                                Icon(Icons.Default.Clear, contentDescription = "Clear textfield")
                            }
                        }
                    }
                }
            )

            LazyColumn(
                modifier = Modifier.fillMaxHeight(),
            ) {
                items(pagingItems.itemCount) { index ->
                    ContactListItem(
                        contact = pagingItems[index]!!,
                        onCardClick = {
                            onShowDetails(pagingItems[index]!!)
                        }
                    )
                }
                pagingItems.apply {
                    when {
                        loadState.refresh is LoadState.Loading -> {
                            item {
                                LoadingView()
                            }
                        }
                        loadState.append is LoadState.Loading -> {
                            item {
                                LoadingView()
                            }
                        }
                    }
                }
            }

        }
    }
}