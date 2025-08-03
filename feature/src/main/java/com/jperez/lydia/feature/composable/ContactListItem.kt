package com.jperez.lydia.feature.composable

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.SubcomposeAsyncImage
import com.jperez.lydia.domain.model.Contact
import com.jperez.lydia.feature.mapper.ContactListItemUIMapper
import com.jperez.lydia.feature.model.ContactListItemUI
import com.jperez.lydia.feature.theme.LydiaTheme
import org.koin.compose.koinInject

@Composable
fun ContactListItem(
    onCardClick: () -> Unit,
    contact: Contact,
    modifier: Modifier = Modifier,
    contactListItemUIMapper: ContactListItemUIMapper = koinInject<ContactListItemUIMapper>()
) {
    val itemUI: ContactListItemUI = contactListItemUIMapper.mapTo(contact)
    Card(
        modifier = modifier
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .fillMaxWidth()
            .clickable {
                onCardClick()
            },
        colors = CardDefaults.cardColors().copy(
            containerColor = MaterialTheme.colorScheme.surfaceContainer
        ),
        shape = RoundedCornerShape(corner = CornerSize(16.dp))
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            SubcomposeAsyncImage(
                modifier = Modifier
                    .size(120.dp),
                model = itemUI.imageUrl,
                contentScale = ContentScale.Crop,
                loading = {
                    Box(
                        modifier = Modifier
                            .size(120.dp)
                            .shimmerBackground()
                    )
                },
                contentDescription = "contact image",
            )

            Column(
                verticalArrangement = Arrangement.spacedBy(5.dp),
                modifier = Modifier
                    .padding(top = 16.dp, bottom = 16.dp, start = 16.dp, end = 0.dp)
                    .fillMaxWidth()
                    .align(Alignment.CenterVertically)
            ) {
                Text(text = itemUI.name, style = typography.titleLarge)
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(Icons.Default.Call, "call icon")
                    Text(text = itemUI.phone, style = typography.bodySmall)
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(Icons.Default.Email, "email icon")
                    Text(
                        text = itemUI.mail, style = typography.bodySmall,
                        overflow = TextOverflow.Visible
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ContactListItemPreview() {
    LydiaTheme {
        Column {
            ContactListItem(
                contact = PreviewConstant.contact,
                onCardClick = {},
                modifier = Modifier.fillMaxWidth(),
            )
            ContactListItem(
                contact = PreviewConstant.contact,
                onCardClick = {},
                modifier = Modifier.fillMaxWidth(),
            )
            ContactListItem(
                contact = PreviewConstant.contact,
                onCardClick = {},
                modifier = Modifier.fillMaxWidth(),
            )
        }
    }
}