package com.jperez.lydia.feature.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.InputChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jperez.lydia.feature.theme.LydiaTheme

@Composable
fun SeedChip(
    seed: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    onDelete: () -> Unit,
) {
    FilterChip(
        modifier = Modifier.wrapContentHeight(
            align = Alignment.CenterVertically
        ),
        onClick = {
            onClick()
        },
        label = {
            Text(
                text = seed,
            )
        },
        selected = isSelected,
        trailingIcon = {
            Icon(
                Icons.Default.Close,
                contentDescription = "Close icon",
                Modifier
                    .size(InputChipDefaults.AvatarSize)
                    .clickable {
                        onDelete()
                    }
            )
        },
    )
}

@Preview(showBackground = true)
@Composable
fun SeedChipPreview() {
    LydiaTheme {
        FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            SeedChip(
                seed = "Sample Seed",
                isSelected = true,
                onClick = {},
                onDelete = {}
            )
            SeedChip(
                seed = "Test",
                isSelected = false,
                onClick = {},
                onDelete = {}
            )
        }
    }
}