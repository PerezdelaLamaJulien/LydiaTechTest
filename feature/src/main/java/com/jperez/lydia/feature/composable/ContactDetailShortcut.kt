package com.jperez.lydia.feature.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.composables.icons.lucide.Lucide
import com.composables.icons.lucide.Mail
import com.composables.icons.lucide.PhoneCall
import com.composables.icons.lucide.Share
import com.jperez.lydia.feature.theme.LydiaTheme
import com.jperez.lydia.feature.theme.Purple80

@Composable
fun ContactDetailShortcut() {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        HorizontalDivider(
            Modifier
                .fillMaxWidth()
                .width(1.dp), color = Purple80
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Icon(Lucide.PhoneCall, contentDescription = "Call Icon", tint = Purple80)
                Text(
                    text = "Call",
                    style = typography.bodyLarge.copy(
                        fontWeight = FontWeight.Bold
                    ),
                    color = Purple80,
                )
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Icon(
                    Lucide.Mail,
                    contentDescription = "Email Icon",
                    tint = Purple80
                )
                Text(
                    text = "Email",
                    style = typography.bodyLarge.copy(
                        fontWeight = FontWeight.Bold
                    ),
                    color = Purple80
                )
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Icon(
                    Lucide.Share,
                    contentDescription = "Share Icon",
                    tint = Purple80
                )
                Text(
                    text = "Share",
                    style = typography.bodyLarge.copy(
                        fontWeight = FontWeight.Bold
                    ),
                    color = Purple80
                )
            }
        }
        HorizontalDivider(
            Modifier
                .fillMaxWidth()
                .width(1.dp), color = Purple80
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ContactDetailShortcutPreview() {
    LydiaTheme {
        ContactDetailShortcut()
    }
}