package com.jperez.lydia.feature.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import com.composables.icons.lucide.PhoneCall
import com.jperez.lydia.feature.theme.LydiaTheme
import com.jperez.lydia.feature.theme.Purple40
import com.jperez.lydia.feature.theme.Purple80

@Composable
fun ContactDetailCard(
    title: String,
    contents: List<@Composable () -> Unit>,
) {
    Card(
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .fillMaxWidth(),
        colors = CardDefaults.cardColors().copy(
            containerColor = Purple80
        ),
        shape = RoundedCornerShape(corner = CornerSize(8.dp))
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 8.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                title,
                style = typography.titleMedium,
                modifier = Modifier.padding(8.dp)
            )
            contents.forEach { content ->
                content()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ContactDetailCardPreview() {
    LydiaTheme {
        Column {
            ContactDetailCard(
                title = "Contact Details",
                contents = listOf(
                    {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Icon(
                                Lucide.PhoneCall,
                                contentDescription = "Phone Icon",
                                tint = Purple40,
                                modifier = Modifier.width(24.dp)
                            )
                            Text(
                                text = "123-456-7890",
                                style = typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                                modifier = Modifier.padding(start = 8.dp)
                            )
                        }
                    })
            )

        }
    }
}