package com.jperez.lydia.presentation.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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
import androidx.compose.ui.tooling.preview.Preview
import com.jperez.lydia.presentation.theme.LydiaTheme
import com.jperez.lydia.presentation.viewmodel.ListViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListActivity : ComponentActivity() {

    private val viewModel by viewModel<ListViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
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
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LydiaTheme {
        Greeting("Android")
    }
}