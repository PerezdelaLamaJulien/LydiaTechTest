package com.jperez.lydia.feature.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.SubcomposeAsyncImage
import com.composables.icons.lucide.Cake
import com.composables.icons.lucide.Clock
import com.composables.icons.lucide.House
import com.composables.icons.lucide.Lucide
import com.composables.icons.lucide.Mail
import com.composables.icons.lucide.PhoneCall
import com.jperez.lydia.domain.model.Contact
import com.jperez.lydia.feature.mapper.ContactDetailUIMapper
import com.jperez.lydia.feature.model.ContactDetailUI
import com.jperez.lydia.feature.theme.LydiaTheme
import org.koin.compose.koinInject

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactDetailScreen(
    onBack: () -> Unit,
    contact: Contact,
    contactDetailUIMapper: ContactDetailUIMapper = koinInject<ContactDetailUIMapper>()
) {
    val itemUI: ContactDetailUI = contactDetailUIMapper.mapTo(contact)
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        "Contact Details",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { onBack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back button"
                        )
                    }
                },
            )
        }) { padding ->
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxSize()
                .padding(padding),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            SubcomposeAsyncImage(
                modifier = Modifier
                    .size(200.dp)
                    .clip(CircleShape)
                    .align(Alignment.CenterHorizontally),
                contentScale = ContentScale.Crop,
                model = itemUI.imageUrl,
                loading = {
                    Box(
                        modifier = Modifier
                            .size(200.dp)
                            .clip(CircleShape)
                            .shimmerBackground()
                    )
                },
                contentDescription = "contact image",
            )
            Text(
                text = itemUI.name,
                style = typography.titleLarge,
            )
            ContactDetailShortcut()

            ContactDetailCard(
                title = "Contact Info",
                contents = listOf(
                    {
                        ContactDetailCardItem(
                            icon = Lucide.PhoneCall,
                            iconDescription = "Phone Icon",
                            text = listOf(itemUI.phone),
                        )
                    },
                    {
                        ContactDetailCardItem(
                            icon = Lucide.Mail,
                            iconDescription = "Mail Icon",
                            text = listOf(itemUI.mail),
                        )
                    },
                )
            )

            ContactDetailCard(
                title = "Address",
                contents = listOf(
                    {
                        ContactDetailCardItem(
                            icon = Lucide.House,
                            iconDescription = "Home Icon",
                            text = listOf(
                                itemUI.address,
                                itemUI.city,
                                itemUI.state,
                                itemUI.country,
                            ),
                        )
                    },
                    {
                        ContactDetailCardItem(
                            icon = Lucide.Clock,
                            iconDescription = "Clock Icon",
                            text = listOf(itemUI.timezoneInfo),
                        )
                    },
                )
            )

            ContactDetailCard(
                title = "Birthday",
                contents = listOf(
                    {
                        ContactDetailCardItem(
                            icon = Lucide.Cake,
                            iconDescription = "Cake Icon",
                            text = listOf(itemUI.birthInfo),
                        )
                    },
                )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ContactDetailScreenPreview() {
    LydiaTheme {
        ContactDetailScreen(
            onBack = {},
            contact = PreviewConstant.contact,
            contactDetailUIMapper = ContactDetailUIMapper()
        )
    }
}
