package dev.icerock.redwoodapp.android.widgets

import androidx.compose.runtime.Composable
import dev.icerock.redwood.schema.widget.Banners
import dev.icerock.redwood.schema.widget.CounterButton
import dev.icerock.redwood.schema.widget.ProductCard
import dev.icerock.redwood.schema.widget.RedwoodAppSchemaWidgetFactory
import dev.icerock.redwood.schema.widget.RowWithWeight
import dev.icerock.redwood.schema.widget.ShortButton

object ComposeWidgetFactory : RedwoodAppSchemaWidgetFactory<@Composable () -> Unit> {
    override fun TextInput() = ComposeTextInput()

    override fun Text() = ComposeText()

    override fun Image() = ComposeImage()

    override fun Card() = ComposeCard()

    override fun Stack() = ComposeStack()

    override fun ImageButton() = ComposeImageButton()

    override fun Button() = ComposeButton()

    override fun Space() = ComposeSpace()
    override fun Banners(): Banners<() -> Unit> = ComposeBanners()
    override fun ShortButton(): ShortButton<() -> Unit> = ComposeShortButton()

    override fun RowWithWeight(): RowWithWeight<() -> Unit> =
        ComposeRowWithWeight()

    override fun ProductCard(): ProductCard<() -> Unit> =
        ComposeProductCard()

    override fun CounterButton(): CounterButton<() -> Unit> = ComposeCounterButton()
}
