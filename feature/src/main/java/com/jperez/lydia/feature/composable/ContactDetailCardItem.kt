package com.jperez.lydia.feature.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.composables.icons.lucide.Lucide
import com.composables.icons.lucide.Mail
import com.jperez.lydia.feature.theme.LydiaTheme
import com.jperez.lydia.feature.theme.Purple40

@Composable
fun ContactDetailCardItem(
    icon: ImageVector,
    iconDescription: String,
    text: List<String>,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 4.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            icon,
            contentDescription = iconDescription,
            tint = Purple40
        )
        Column {
            text.forEach {
                Text(
                    text = it,
                    style = typography.bodyLarge,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ContactDetailCardItemPreview() {
    LydiaTheme {
        ContactDetailCardItem(
            icon = Lucide.Mail,
            iconDescription = "Mail Icon",
            text = listOf("marin.meunier@example.com"),
        )
    }
}