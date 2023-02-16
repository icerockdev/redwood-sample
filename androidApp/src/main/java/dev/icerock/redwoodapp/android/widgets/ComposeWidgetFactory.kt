package dev.icerock.redwoodapp.android.widgets

import androidx.compose.runtime.Composable
import dev.icerock.redwood.schema.widget.Banners
import dev.icerock.redwood.schema.widget.CounterButton
import dev.icerock.redwood.schema.widget.Divider
import dev.icerock.redwood.schema.widget.FooterColumn
import dev.icerock.redwood.schema.widget.ListItem
import dev.icerock.redwood.schema.widget.Onboarding
import dev.icerock.redwood.schema.widget.ProductCard
import dev.icerock.redwood.schema.widget.RedwoodAppSchemaWidgetFactory
import dev.icerock.redwood.schema.widget.RowWithWeight
import dev.icerock.redwood.schema.widget.ShortButton
import dev.icerock.redwood.schema.widget.Switch
import dev.icerock.redwood.schema.widget.Chip
import dev.icerock.redwood.schema.widget.Tabs

object ComposeWidgetFactory : RedwoodAppSchemaWidgetFactory<@Composable () -> Unit> {

    override fun Tabs(): Tabs<() -> Unit> = ComposeTabs()
    override fun Chip(): Chip<() -> Unit> = ComposeChip()
    override fun Divider(): Divider<() -> Unit> = ComposeDivider()
    override fun Onboarding(): Onboarding<() -> Unit> = ComposeOnboarding()

    override fun FooterColumn(): FooterColumn<() -> Unit> =
        ComposeFooterColumn()

    override fun ListItem(): ListItem<() -> Unit> =
        ComposeListItem()
    override fun Switch(): Switch<() -> Unit> =
        ComposeSwitch()

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
