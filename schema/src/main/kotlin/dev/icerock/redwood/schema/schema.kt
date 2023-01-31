package dev.icerock.redwood.schema

import androidx.compose.runtime.Composable
import app.cash.redwood.layout.RedwoodLayout
import app.cash.redwood.schema.Children
import app.cash.redwood.schema.Default
import app.cash.redwood.schema.Property
import app.cash.redwood.schema.Schema
import app.cash.redwood.schema.Schema.Dependency
import app.cash.redwood.schema.Widget
import dev.icerock.moko.graphics.Color
import dev.icerock.moko.resources.ImageResource
import dev.icerock.moko.resources.desc.StringDesc

@Schema(
    members = [
        TextInput::class,
        Text::class,
        Image::class,
        Stack::class,
        Card::class,
        ImageButton::class,
        Button::class,
        Space::class,
        HalfWightContainer::class,
        SquareImage::class
    ],
    dependencies = [
        Dependency(1, RedwoodLayout::class),
    ],
)
interface RedwoodAppSchema

@Widget(9)
// fill max width
data class SquareImage(
    @Property(3) val url: String?,
    @Property(4) val placeholder: ImageResource?,
)

@Widget(1)
data class TextInput(
    @Property(1)
    @Default("\"\"")
    val state: String,
    @Property(2)
    val hint: StringDesc,
    @Property(3)
    @Default("null")
    val onChange: (String) -> Unit,
    @Property(4)
    @Default("null")
    val inputType: InputType?,
)

@Widget(2)
data class Text(
    @Property(1) val text: String,
    @Property(2)
    @Default("false") val isSingleLine: Boolean,
    @Property(3)
    @Default("TextType.Primary")
    val textType: TextType?
)

@Widget(3)
data class Image(
    @Property(1) val width: Int?,
    @Property(2) val height: Int?,
    @Property(3) val url: String?,
    @Property(4) val placeholder: ImageResource?,
    @Property(5)
    @Default("0")
    val cornierRadius: Int
)

@Widget(4)
data class ImageButton(
    @Property(1) val text: StringDesc,
    @Property(2)
    @Default("null") val icon: ImageResource?,
    @Property(3)
    @Default("true")
    val isClicked: Boolean,
    @Property(4)
    val onClick: () -> Unit,
    @Property(5)
    val type: ImageButtonType
)

@Widget(5)
data class Button(
    @Property(1) val text: StringDesc,
    @Property(2)
    val buttonType: ButtonType,
    @Property(3)
    @Default("true")
    val enabled: Boolean,
    @Property(4)
    val onClick: () -> Unit,

    )

@Widget(6)
data class Card(
    @Children(1) val child: @Composable () -> Unit,
    @Property(2)
    val onClick: () -> Unit,
)

//todo fix to normal list
@Widget(7)
data class Stack(
    @Children(1) val child1: @Composable () -> Unit,
    @Children(2) val child2: @Composable () -> Unit,
)

@Widget(8)
data class Space(
    @Property(1) val background: Color = Color(0x00000000),
    // -1 max size
    @Property(2) val width: Int,
    @Property(3) val height: Int,
)
@Widget(10)
data class HalfWightContainer(
    @Children(1) val child1: () -> Unit
)

data class BottomSheet(
    @Children(1) val body: () -> Unit,
    @Children(2) val bottomSheetContent: () -> Unit
)