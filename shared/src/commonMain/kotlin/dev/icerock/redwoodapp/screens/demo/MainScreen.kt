package dev.icerock.redwoodapp.screens.demo

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import app.cash.redwood.LayoutModifier
import app.cash.redwood.layout.api.Constraint
import app.cash.redwood.layout.api.MainAxisAlignment
import app.cash.redwood.layout.api.Overflow
import app.cash.redwood.layout.api.Padding
import app.cash.redwood.layout.compose.Column
import app.cash.redwood.layout.compose.Row
import dev.icerock.moko.graphics.Color
import dev.icerock.moko.resources.desc.desc
import dev.icerock.moko.resources.desc.image.ImageDesc
import dev.icerock.moko.resources.desc.image.Url
import dev.icerock.redwood.schema.BannerColumnData
import dev.icerock.redwood.schema.ButtonType
import dev.icerock.redwood.schema.Size
import dev.icerock.redwood.schema.TextType
import dev.icerock.redwood.schema.compose.BannersColumn
import dev.icerock.redwood.schema.compose.Button
import dev.icerock.redwood.schema.compose.Card
import dev.icerock.redwood.schema.compose.Divider
import dev.icerock.redwood.schema.compose.Image
import dev.icerock.redwood.schema.compose.RowWithWeight
import dev.icerock.redwood.schema.compose.SearchRow
import dev.icerock.redwood.schema.compose.Space
import dev.icerock.redwood.schema.compose.Text
import dev.icerock.redwood.schema.compose.TextInput
import dev.icerock.redwoodapp.PROMOTION_LIST
import dev.icerock.redwoodapp.USER_AVATAR
import org.example.library.MR

@Composable
fun MainScreen() {
    Column(
        height = Constraint.Wrap,
        width = Constraint.Fill
    ) {
        Column(
            padding = Padding(start = 16, top = 16, end = 16)
        ) {
            var search by remember { mutableStateOf("") }
            Row {
                Image(
                    placeholder = MR.images.location,
                    url = "",
                    width = Size.Const(20),
                    height = Size.Const(20)
                )
                Text(
                    text = "Москва",
                    textType = TextType.Link,
                    layoutModifier = LayoutModifier.padding(Padding(start = 8))
                )
            }
            SearchRow(
                state = search,
                hint = "Салон, название услуги".desc(),
                onChange = {
                    search = it
                },
                showMic = true,
                onMicClick = {

                },
                layoutModifier = LayoutModifier.padding(Padding(top = 16))
            )
        }
        Divider(
            layoutModifier = LayoutModifier.padding(Padding(top = 20))
        )
        Column(
            overflow = Overflow.Scroll
        ) {
            RowWithWeight(
                layoutModifier = LayoutModifier.padding(Padding(start = 16, top = 20, end = 16))
            ) {
                Text(
                    text = "Новости",
                    textType = TextType.PrimaryBold,
                    layoutModifier = LayoutModifier.padding(Padding(top = 8))
                )
                Row(
                    horizontalAlignment = MainAxisAlignment.End,
                    width = Constraint.Fill
                ) {
                    Button(
                        text = "Смотреть все".desc(),
                        buttonType = ButtonType.Text,
                        onClick = {

                        }
                    )
                }
            }
            BannersColumn(
                bannersList = listOf(
                    BannerColumnData(
                        placeholder = MR.images.ava_preview,
                        image = ImageDesc.Url(USER_AVATAR),
                        textTitle = "Дарим сертификаты к праздникам!",
                        data = "15.02.2023 ",
                        textDescription = "Удивите близких отдыхом в спа и получите массаж в подарок",
                        onClick = {}
                    ),
                    BannerColumnData(
                        placeholder = MR.images.ava_preview,
                        image = ImageDesc.Url(USER_AVATAR),
                        textTitle = "Дарим сертификаты к праздникам!",
                        data = "15.02.2023 ",
                        textDescription = "Удивите близких отдыхом в спа и получите массаж в подарок",
                        onClick = {}
                    ),
                    BannerColumnData(
                        placeholder = MR.images.ava_preview,
                        image = ImageDesc.Url(USER_AVATAR),
                        textTitle = "Дарим сертификаты к праздникам!",
                        data = "15.02.2023 ",
                        textDescription = "Удивите близких отдыхом в спа и получите массаж в подарок",
                        onClick = {}
                    )
                )
            )
            Text(
                text = "Акции",
                textType = TextType.PrimaryBold,
                layoutModifier = LayoutModifier.padding(Padding(start = 16, top = 16, end = 16))
            )

            PROMOTION_LIST.forEach { promotion ->
                Card(
                    layoutModifier = LayoutModifier.padding(Padding(start = 16, top = 8, end = 16)),
                    onClick = {},
                    child = {
                        Column(
                            padding = Padding(8)
                        ) {
                            Image(
                                width = Size.Fill,
                                height = Size.Const(150),
                                url = promotion.image,
                                placeholder = MR.images.dislike
                            )
                            Text(
                                text = promotion.labile,
                                textType = TextType.PrimarySmall,
                                layoutModifier = LayoutModifier.padding(Padding(top = 8))
                            )
                            Text(
                                text = promotion.description,
                                textType = TextType.SecondarySmall,
                                layoutModifier = LayoutModifier.padding(Padding(top = 4))
                            )
                            Text(
                                text = promotion.labile,
                                textType = TextType.Caption,
                                layoutModifier = LayoutModifier.padding(Padding(top = 4))
                            )
                        }
                    }
                )
            }
            Space(
                height = 150,
                width = -1,
                background = Color(0x0)
            )
        }
    }
}