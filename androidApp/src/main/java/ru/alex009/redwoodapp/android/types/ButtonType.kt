package ru.alex009.redwoodapp.android.types

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun PrimaryButton(text: String, enabled: Boolean, onClick: () -> Unit) {
    Button(
        modifier = Modifier.fillMaxWidth().height(40.dp),
        onClick = onClick,
        enabled = enabled,
        shape = RoundedCornerShape(8.dp),
        elevation = ButtonDefaults.elevation(0.dp, 0.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color(0xFF0C7BC7),
            contentColor = Color.White,
            disabledBackgroundColor = Color(0x2828282E),
            disabledContentColor = Color(0x61282861),
        )
    ) {
        Text(
            text = text.uppercase(),
        )
    }
}

@Composable
fun SecondaryButton(text: String, enabled: Boolean, onClick: () -> Unit) {
    Button(
        modifier = Modifier.fillMaxWidth(),
        onClick = onClick,
        enabled = enabled,
        shape = RoundedCornerShape(8.dp),
        elevation = ButtonDefaults.elevation(0.dp, 0.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.White,
            contentColor = Color(0xFF0C7BC7),
            disabledBackgroundColor = Color.White,
            disabledContentColor = Color(0x2828282E),
        ),
        border = BorderStroke(1.dp, if (enabled) Color(0xFF0C7BC7) else Color(0x2828282E))
    ) {
        Text(
            text = text.uppercase(),
        )
    }
}

@Composable
fun ActionButton(text: String, enabled: Boolean, onClick: () -> Unit) {

}

@Preview
@Composable
fun PreviewButton() {
    Column(
        Modifier
            .background(Color.White)
            .padding(16.dp)
    ) {
        PrimaryButton("Text", true, {})
        SecondaryButton(text = "text", enabled = true, {})
    }
}