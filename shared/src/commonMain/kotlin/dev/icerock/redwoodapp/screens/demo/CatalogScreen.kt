package dev.icerock.redwoodapp.screens.demo

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import app.cash.redwood.LayoutModifier
import app.cash.redwood.layout.api.Constraint
import app.cash.redwood.layout.api.Overflow
import app.cash.redwood.layout.api.Padding
import app.cash.redwood.layout.compose.Column
import app.cash.redwood.layout.compose.Row
import dev.icerock.moko.graphics.Color
import dev.icerock.moko.graphics.parseColor
import dev.icerock.moko.resources.desc.desc
import dev.icerock.redwood.schema.Size
import dev.icerock.redwood.schema.TextType
import dev.icerock.redwood.schema.compose.Card
import dev.icerock.redwood.schema.compose.Chip
import dev.icerock.redwood.schema.compose.Divider
import dev.icerock.redwood.schema.compose.HeaderFooterContent
import dev.icerock.redwood.schema.compose.Image
import dev.icerock.redwood.schema.compose.ImageCardWithText
import dev.icerock.redwood.schema.compose.RowWithWeight
import dev.icerock.redwood.schema.compose.SearchRow
import dev.icerock.redwood.schema.compose.Space
import dev.icerock.redwood.schema.compose.Text
import dev.icerock.redwoodapp.CATEGORIES
import dev.icerock.redwoodapp.SERVICES
import dev.icerock.redwoodapp.ext.weight
import org.example.library.MR

@Composable
fun CatalogScreen() {
    HeaderFooterContent(
        header = {
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
                Divider(
                    layoutModifier = LayoutModifier.padding(Padding(top = 20))
                )
            }
        },
        child = {
            Column(
                overflow = Overflow.Scroll,
                width = Constraint.Fill,
            ) {
             /*   Row(
                    width = Constraint.Fill,
                    overflow = Overflow.Scroll,
                    padding = Padding(horizontal = 16, vertical = 8)
                ) {
                    var isCategoryClicked by remember { mutableStateOf(true) }
                    Chip(
                        text = CATEGORIES.get(0).desc(),
                        backgroundColor = if (isCategoryClicked) Color.parseColor("#FF684CDC") else null,
                        textColor = if (isCategoryClicked) Color.parseColor("#FFFFFFFF") else Color.parseColor(
                            "#FF605D62"
                        ),
                        border = if (isCategoryClicked) 0 else 1,
                        icon = null,
                        onClick = {
                            isCategoryClicked = !isCategoryClicked
                        },
                        layoutModifier = LayoutModifier.padding(Padding(end = 8))
                    )
                    var isCategoryClicked2 by remember { mutableStateOf(true) }
                    Chip(
                        text = CATEGORIES.get(1).desc(),
                        backgroundColor = if (isCategoryClicked2) MR.colors.primary.color else null,
                        textColor = if (isCategoryClicked2.not()) MR.colors.black.color else MR.colors.white.color,
                        border = if (isCategoryClicked2) 0 else 1,
                        icon = null,
                        onClick = {
                            isCategoryClicked2 = !isCategoryClicked2
                        },
                        layoutModifier = LayoutModifier.padding(Padding(end = 8))
                    )
                    var isCategoryClicked3 by remember { mutableStateOf(false) }
                    Chip(
                        text = CATEGORIES.get(2).desc(),
                        backgroundColor = if (isCategoryClicked3) Color.parseColor("#FF684CDC") else null,
                        textColor = if (isCategoryClicked3) Color.parseColor("#FFFFFFFF") else Color.parseColor(
                            "#FF605D62"
                        ),
                        border = if (isCategoryClicked3) 0 else 1,
                        icon = null,
                        onClick = {
                            isCategoryClicked3 = !isCategoryClicked3
                        },
                        layoutModifier = LayoutModifier.padding(Padding(end = 8))
                    )
                    var isCategoryClicked4 by remember { mutableStateOf(false) }
                    Chip(
                        text = CATEGORIES.get(3).desc(),
                        backgroundColor = if (isCategoryClicked4) Color.parseColor("#FF684CDC") else null,
                        textColor = if (isCategoryClicked4) Color.parseColor("#FFFFFFFF") else Color.parseColor(
                            "#FF000000"
                        ),
                        border = if (isCategoryClicked4) 0 else 1,
                        icon = null,
                        onClick = {
                            isCategoryClicked4 = !isCategoryClicked4
                        },
                        layoutModifier = LayoutModifier.padding(Padding(end = 8))
                    )
                }*/
                SERVICES.forEachIndexed { index, item ->
                    if ((index + 1) % 3 == 0) {
                        RowWithWeight(
                            layoutModifier = LayoutModifier.padding(
                                Padding(
                                    start = 16,
                                    end = 16,
                                    top = 16
                                )
                            ), childs = {
                                ImageCardWithText(
                                    height = Size.Const(100),
                                    text = SERVICES.get(index - 2).name.desc(),
                                    textBackgroundColor = Color.parseColor("#CC313033"),
                                    textColor = Color.parseColor("#FFFFFFFF"),
                                    url = SERVICES.get(index - 2).image,
                                    placeholder = MR.images.ava_preview,
                                    onClick = SERVICES.get(index - 2).onClick,
                                    layoutModifier = LayoutModifier.padding(Padding(end = 6))
                                        .weight(1f)
                                )
                                ImageCardWithText(
                                    height = Size.Const(100),
                                    text = SERVICES.get(index - 1).name.desc(),
                                    textBackgroundColor = Color.parseColor("#CC313033"),
                                    textColor = Color.parseColor("#FFFFFFFF"),
                                    url = SERVICES.get(index - 1).image,
                                    placeholder = MR.images.ava_preview,
                                    onClick = SERVICES.get(index - 1).onClick,
                                    layoutModifier = LayoutModifier.padding(Padding(start = 6))
                                        .weight(1f)
                                )
                            }
                        )
                        ImageCardWithText(
                            height = Size.Const(100),
                            text = item.name.desc(),
                            textBackgroundColor = Color.parseColor("#CC313033"),
                            textColor = Color.parseColor("#FFFFFFFF"),
                            url = item.image,
                            placeholder = MR.images.ava_preview,
                            onClick = item.onClick,
                            layoutModifier = LayoutModifier.padding(
                                Padding(
                                    start = 16,
                                    end = 16,
                                    top = 12
                                )
                            )
                        )
                    }
                }

                Space(
                    height = 150,
                    width = -1,
                    background = Color(0x0)
                )
            }
        },
        footer = {}
    )
}