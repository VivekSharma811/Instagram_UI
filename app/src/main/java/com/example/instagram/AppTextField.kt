package com.example.instagram

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.instagram.ui.theme.ColorBLUE

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTextField(
    text: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    trailingIcon: @Composable() (() -> Unit)? = null,
    hint: String = "",
    error: String? = null,
    isEnabled: Boolean = true,
    maxLines: Int = 1,
    singleLine: Boolean = true,
    imeAction: ImeAction = ImeAction.Done,
    keyboardType: KeyboardType = KeyboardType.Text,
    placeholder: String? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    isFocused: Boolean = false
) {
    var isTextFocused by remember { mutableStateOf(isFocused) }

    var focusColor by remember {
        mutableStateOf(if (isTextFocused) ColorBLUE else Color.Gray)
    }

    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.Start
    ) {
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Min)
                .clip(RoundedCornerShape(8.dp))
                .border(width = 1.dp, color = focusColor, shape = RoundedCornerShape(8.dp))
                .background(color = Color.Transparent)
                .onFocusChanged { isTextFocused = it.isFocused },
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                errorIndicatorColor = Color.Transparent,
            ),
            isError = error.isNullOrEmpty().not(),
            value = text,
            onValueChange = { onValueChange(it) },
            label = {
                Text(text = hint, color = ColorBLUE)
            },
            trailingIcon = trailingIcon,
            enabled = isEnabled,
            maxLines = maxLines,
            singleLine = singleLine,
            keyboardOptions = KeyboardOptions(
                imeAction = imeAction,
                keyboardType = keyboardType
            ),
            placeholder = {
                if (placeholder != null) {
                    Text(text = placeholder)
                }
            },
            visualTransformation = visualTransformation
        )

        if (error != null) {
            focusColor = Color.Red
            Text(
                text = error,
            )
        } else {
            focusColor = if (isTextFocused) ColorBLUE else Color.Gray
        }
    }
}