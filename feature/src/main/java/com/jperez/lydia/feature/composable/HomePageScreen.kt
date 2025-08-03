package com.jperez.lydia.feature.composable

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.jperez.lydia.domain.model.Contact
import com.jperez.lydia.feature.theme.LydiaTheme

@Composable
fun HomePageScreen() {
    LydiaTheme {
        var contactDetail : Contact?  by remember {
            mutableStateOf(null)
        }
            AnimatedContent(
                contactDetail,
                transitionSpec = {
                    if (targetState != null) {
                        (fadeIn() + slideInHorizontally(initialOffsetX = { it })).togetherWith(
                            fadeOut() + slideOutHorizontally(targetOffsetX = { -it })
                        )
                    } else {
                        (fadeIn() + slideInHorizontally(initialOffsetX = { -it })).togetherWith(
                            fadeOut() + slideOutHorizontally(targetOffsetX = { it })
                        )
                    }
                }
            ) { targetState ->
                if (targetState != null) {
                    ContactDetailScreen(
                        onBack = {
                            contactDetail = null
                        },
                        contact = targetState,
                    )
                } else {
                    ContactListScreen(
                        onShowDetails = { selectedContact ->
                            contactDetail = selectedContact
                        },
                    )
                }
        }
    }
}