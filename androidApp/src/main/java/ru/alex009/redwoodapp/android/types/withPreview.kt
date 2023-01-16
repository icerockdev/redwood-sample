package ru.alex009.redwoodapp.android.types

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.alex009.redwoodapp.android.R

@Composable
fun Fuuuu(
    _text: String,
    _icon: Int,
    _onClick: () -> Unit,
    _isClicked: Boolean
) {
    Button(
        onClick = _onClick,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.Transparent
        ),
        elevation = ButtonDefaults.elevation(0.dp, 0.dp)
    ) {
        Row {
            Icon(
                modifier = Modifier.padding(end = 8.dp),
                painter = painterResource(_icon),
                contentDescription = null
            )
            Text(
                text = _text,
                color = if (_isClicked) Color(0xFF0C7BC7) else Color(0xFFA9A9A9)
            )
        }
    }
}

@Preview
@Composable
fun Preview() {
    Column(
        Modifier
            .background(Color.White)
            .padding(16.dp)
    ) {
        Fuuuu(
            _text = "Text",
            _icon = R.drawable.dislike_clicked,
            _onClick = {},
            _isClicked = false,
        )
    }
}