package com.nescafe.nescafecompose.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.Dp

@Composable
fun CommonTextField(
    textPlaceholder: String,
    textError: String,
    value: String,
    onTextChanged: (String) -> Unit,
    layoutPadding: Dp,
    isError: Boolean,
    leadingIcon: Painter,
    shouldShowLeadingIcon : Boolean,
) {
    Column {

        OutlinedTextField(
            modifier = Modifier
                .padding(vertical = layoutPadding)
                .fillMaxWidth()
                .clip(shape = CircleShape),
            value = value,
            onValueChange = {
                onTextChanged(it)
            },
            isError = isError,
            placeholder = {
                Text(text = textPlaceholder)
            },
            shape = CircleShape,
            leadingIcon = {
                if (shouldShowLeadingIcon) {
                    Icon(
                        painter = leadingIcon,
                        contentDescription = "leading icon",
                        tint = MaterialTheme.colors.onSurface.copy(alpha = 0.3f)
                    )
                }

            }
        )
        if (isError) {
            Text(text = textError, color = Color.Red)
        }
    }
}