package dev.icerock.redwoodapp.android.types

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.icerock.moko.resources.ImageResource
import dev.icerock.redwoodapp.android.R
import dev.icerock.redwoodapp.android.theme.Colors
import okhttp3.internal.wait

@Composable
fun PrimaryButton(
    modifier: Modifier = Modifier,
    text: String,
    enabled: Boolean,
    icon: ImageResource?,
    onClick: () -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    Button(
        modifier = modifier
            .height(48.dp),
        onClick = onClick,
        enabled = enabled,
        interactionSource = interactionSource,
        shape = RoundedCornerShape(10.dp),
        elevation = ButtonDefaults.elevation(0.dp, 0.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = if (isPressed) Colors.primary88 else
                Colors.primary,
            contentColor = Color.White,
            disabledBackgroundColor = Colors.black18,
            disabledContentColor = Colors.black33,
        )
    ) {
        Row {
            icon?.let {
                Image(
                    modifier = Modifier
                        .padding(end = 8.dp)
                        .size(24.dp),
                    colorFilter = ColorFilter.tint(color = if (enabled) Color.White else Colors.black33),
                    contentScale = ContentScale.Inside,
                    painter = painterResource(id = it.drawableResId),
                    contentDescription = ""
                )
            }
            Text(
                modifier = Modifier.align(Alignment.CenterVertically),
                text = text,
            )
        }
    }
}

@Composable
fun SecondaryButton(
    modifier: Modifier = Modifier,
    text: String,
    enabled: Boolean,
    icon: ImageResource?,
    onClick: () -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    Button(
        modifier = modifier
            .height(48.dp),
        onClick = onClick,
        enabled = enabled,
        shape = RoundedCornerShape(10.dp),
        elevation = ButtonDefaults.elevation(0.dp, 0.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = if (isPressed) Colors.primary88 else Color.Transparent,
            contentColor = Colors.primary,
            disabledBackgroundColor = Color.Transparent,
            disabledContentColor = Colors.black33,
        ),
        border = BorderStroke(1.dp, if (enabled) Colors.primary else Colors.black33)
    ) {
        Row {
            icon?.let {
                Image(
                    modifier = Modifier
                        .padding(end = 8.dp)
                        .size(24.dp),
                    colorFilter = ColorFilter.tint(color = if (enabled) Colors.primary else Colors.black33),
                    contentScale = ContentScale.Inside,
                    painter = painterResource(id = it.drawableResId),
                    contentDescription = ""
                )
            }
            Text(
                modifier = Modifier.align(Alignment.CenterVertically),
                text = text,
            )
        }
    }
}

@Composable
fun TonnalButton(
    modifier: Modifier = Modifier,
    text: String,
    enabled: Boolean,
    icon: ImageResource?,
    onClick: () -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    Button(
        modifier = modifier
            .height(48.dp),
        onClick = onClick,
        enabled = enabled,
        shape = RoundedCornerShape(24.dp),
        elevation = ButtonDefaults.elevation(0.dp, 0.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = if (isPressed) Colors.primary88 else Colors.primary12,
            contentColor = Colors.black,
            disabledBackgroundColor = Colors.black18,
            disabledContentColor = Colors.black33,
        )
    ) {
        Row {
            icon?.let {
                Image(
                    modifier = Modifier
                        .padding(end = 8.dp)
                        .size(24.dp),
                    colorFilter = ColorFilter.tint(color = if (enabled) Colors.black else Colors.black33),
                    contentScale = ContentScale.Inside,
                    painter = painterResource(id = it.drawableResId),
                    contentDescription = ""
                )
            }
            Text(
                text = text,
            )
        }
    }
}

@Composable
fun ActionButton(
    modifier: Modifier = Modifier,
    text: String,
    enabled: Boolean,
    icon: ImageResource?,
    onClick: () -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    Button(
        modifier = modifier
            .height(48.dp),
        onClick = onClick,
        enabled = enabled,
        shape = RoundedCornerShape(10.dp),
        elevation = ButtonDefaults.elevation(0.dp, 0.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = if (isPressed) Colors.primary88 else Color.Transparent,
            contentColor = Colors.primary,
            disabledBackgroundColor = Color.Transparent,
            disabledContentColor = Colors.black33,
        )
    ) {
        Row {
            icon?.let {
                Image(
                    modifier = Modifier
                        .padding(end = 8.dp)
                        .size(24.dp),
                    colorFilter = ColorFilter.tint(color = if (enabled) Colors.primary else Colors.black33),
                    contentScale = ContentScale.Inside,
                    painter = painterResource(id = it.drawableResId),
                    contentDescription = ""
                )
            }
            Text(
                modifier = Modifier.align(Alignment.CenterVertically),
                text = text,
            )
        }
    }
}

@Preview
@Composable
fun PreviewButton() {
    Column(
        Modifier
            .background(Color.White)
            .padding(16.dp)
    ) {
        PrimaryButton(text = "Text", enabled = true, icon = null) {}
        SecondaryButton(text = "text", enabled = true, icon = null) {}
        TonnalButton(text = "cdc", enabled = true, icon = null) {}
    }
}