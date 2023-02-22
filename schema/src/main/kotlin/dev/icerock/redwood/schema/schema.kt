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
        Banners::class,
        ShortButton::class,
        RowWithWeight::class,
        ProductCard::class,
        CounterButton::class,
        Switch::class,
        ListItem::class,
        Divider::class,
        Onboarding::class,
        FooterColumn::class,
        Chip::class,
        Tabs::class,
        ImageCardWithText::class,
        BannersColumn::class,
        SearchRow::class,
        Box::class
    ],
    dependencies = [
        Dependency(1, RedwoodLayout::class),
    ],
)
interface RedwoodAppSchema

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
    val textType: TextType?,
    @Property(4)
    @Default("Size.Wrap")
    val width: Size,
)

@Widget(3)
data class Image(
    @Property(1)
    @Default("null")
    val width: Size?,
    @Property(2)
    @Default("null")
    val height: Size?,
    @Property(3) val url: String?,
    @Property(4) val placeholder: ImageResource?,
    @Property(5)
    @Default("null")
    val aspectRatio: Float?,
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
    val onClick: () -> Unit
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
    @Default("null")
    val icon: ImageResource?,
    @Property(6)
    @Default("Size.Wrap")
    val width: Size,
    @Property(5)
    val onClick: () -> Unit,
)

@Widget(10)
data class ShortButton(
    @Property(1) val text: StringDesc,
    @Property(2)
    val buttonType: ButtonType,
    @Property(3)
    @Default("true")
    val enabled: Boolean,
    @Property(4)
    @Default("null")
    val icon: ImageResource?,
    @Property(5)
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

@Widget(9)
data class Banners(
    @Property(1) val bannersList: List<BannerData>
)

@Widget(11)
data class RowWithWeight(
    @Children(1)
    val childs: () -> Unit
)

@Widget(12)
data class ProductCard(
    @Property(1)
    val title: StringDesc,
    @Property(2)
    val image: String,
    @Property(3)
    val isLiked: Boolean,
    @Property(4)
    val badge: StringDesc?,
    @Property(5)
    val price: StringDesc,
    @Property(6)
    val oldPrice: StringDesc?,
    @Property(7)
    val subtitle: StringDesc?,
    @Property(8)
    val footer: StringDesc?,
    @Property(9)
    val onLikeClick: () -> Unit,
    @Children(1)
    val action: () -> Unit,
)

@Widget(13)
data class CounterButton(
    @Property(1)
    val count: StringDesc,
    @Property(2)
    val onAddClick: () -> Unit,
    @Property(3)
    val onRemoveClick: () -> Unit,
    @Property(4)
    @Default("Size.Wrap")
    val width: Size,
)

@Widget(14)
data class Switch(
    @Property(1)
    val isActive: Boolean,
    @Property(2)
    val onChangeState: (Boolean) -> Unit,
    @Property(3)
    val isEnabled: Boolean
)

@Widget(15)
data class ListItem(
    @Property(1)
    val title: StringDesc,
    @Property(2)
    @Default("null")
    val subtitle: StringDesc?,
    @Property(3)
    @Default("null")
    val icon: ImageResource?,
    @Property(4)
    @Default("null")
    val height: Int?,
    @Property(5)
    @Default("null")
    val onClick: (() -> Unit)?,
    @Property(6)
    @Default("null")
    val tintColor: Color?,
    @Children(1)
    val child: () -> Unit,
)

@Widget(16)
data class Divider(
    @Property(1)
    @Default("true")
    val isVertical: Boolean
)

@Widget(17)
data class Onboarding(
    @Property(1)
    val onFinishClick: () -> Unit,
    @Children(1)
    val childs: () -> Unit,
)

@Widget(18)
data class FooterColumn(
    @Children(1)
    val child: () -> Unit,
    @Children(2)
    val footer: () -> Unit,
)

@Widget(19)
data class Chip(
    @Property(1)
    val text: StringDesc,
    @Property(2)
    val icon: ImageResource?,
    @Property(3)
    val backgroundColor: Color?,
    @Property(4)
    val textColor: Color?,
    @Property(5)
    @Default("null")
    val border: Int?,
    @Property(6)
    val onClick: () -> Unit,
)

@Widget(20)
data class Tabs(
    @Property(1)
    val texts: List<StringDesc>,
    @Property(2)
    val onChange: (Int)->Unit,
    @Property(3)
    val selectedTab: Int
)

@Widget(21)
data class ImageCardWithText(
    @Default("null")
    @Property(1)
    val width: Size?,
    @Default("null")
    @Property(2)
    val height: Size?,
    @Property(3)
    val text: StringDesc,
    @Property(4)
    val textBackgroundColor: Color?,
    @Property(5)
    val textColor: Color?,
    @Property(6)
    val url: String?,
    @Property(7)
    val placeholder: ImageResource?,
    @Property(8)
    val onClick: (()->Unit)?,
)

@Widget(22)
data class BannersColumn(
    @Property(1) val bannersList: List<BannerColumnData>
)

@Widget(23)
data class SearchRow(
    @Property(1)
    val state: String,
    @Property(2)
    val hint: StringDesc,
    @Property(3)
    @Default("null")
    val onChange: (String) -> Unit,
    @Property(4)
    val showMic: Boolean,
    @Property(5)
    val onMicClick: () -> Unit
)


@Widget(24)
data class Box(
    @Children(1) val child: @Composable () -> Unit,
    @Property(2)
    val background: Color
)