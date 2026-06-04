package xyz.malefic.guptarealty.pages

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.varabyte.kobweb.compose.css.Cursor
import com.varabyte.kobweb.compose.css.FontWeight
import com.varabyte.kobweb.compose.css.JustifyContent
import com.varabyte.kobweb.compose.css.ObjectFit
import com.varabyte.kobweb.compose.css.Overflow
import com.varabyte.kobweb.compose.css.TextDecorationLine
import com.varabyte.kobweb.compose.css.functions.blur
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.alignItems
import com.varabyte.kobweb.compose.ui.modifiers.backdropFilter
import com.varabyte.kobweb.compose.ui.modifiers.backgroundColor
import com.varabyte.kobweb.compose.ui.modifiers.border
import com.varabyte.kobweb.compose.ui.modifiers.borderBottom
import com.varabyte.kobweb.compose.ui.modifiers.borderRadius
import com.varabyte.kobweb.compose.ui.modifiers.borderTop
import com.varabyte.kobweb.compose.ui.modifiers.color
import com.varabyte.kobweb.compose.ui.modifiers.cursor
import com.varabyte.kobweb.compose.ui.modifiers.display
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxSize
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.flexWrap
import com.varabyte.kobweb.compose.ui.modifiers.fontSize
import com.varabyte.kobweb.compose.ui.modifiers.fontWeight
import com.varabyte.kobweb.compose.ui.modifiers.gap
import com.varabyte.kobweb.compose.ui.modifiers.height
import com.varabyte.kobweb.compose.ui.modifiers.justifyContent
import com.varabyte.kobweb.compose.ui.modifiers.left
import com.varabyte.kobweb.compose.ui.modifiers.margin
import com.varabyte.kobweb.compose.ui.modifiers.objectFit
import com.varabyte.kobweb.compose.ui.modifiers.overflow
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.compose.ui.modifiers.position
import com.varabyte.kobweb.compose.ui.modifiers.right
import com.varabyte.kobweb.compose.ui.modifiers.size
import com.varabyte.kobweb.compose.ui.modifiers.textDecorationLine
import com.varabyte.kobweb.compose.ui.modifiers.top
import com.varabyte.kobweb.compose.ui.modifiers.translateY
import com.varabyte.kobweb.compose.ui.modifiers.zIndex
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.silk.components.graphics.Image
import com.varabyte.kobweb.silk.components.icons.mdi.MdiBathtub
import com.varabyte.kobweb.silk.components.icons.mdi.MdiBed
import com.varabyte.kobweb.silk.components.icons.mdi.MdiExpandMore
import com.varabyte.kobweb.silk.components.icons.mdi.MdiFavorite
import com.varabyte.kobweb.silk.components.icons.mdi.MdiSearch
import com.varabyte.kobweb.silk.components.icons.mdi.MdiSmartToy
import com.varabyte.kobweb.silk.components.icons.mdi.MdiSquareFoot
import com.varabyte.kobweb.silk.components.icons.mdi.MdiTune
import com.varabyte.kobweb.silk.components.layout.SimpleGrid
import com.varabyte.kobweb.silk.components.layout.numColumns
import com.varabyte.kobweb.silk.components.navigation.Link
import com.varabyte.kobweb.silk.style.toModifier
import kotlinx.serialization.json.Json
import org.jetbrains.compose.web.attributes.InputType
import org.jetbrains.compose.web.attributes.placeholder
import org.jetbrains.compose.web.css.AlignItems
import org.jetbrains.compose.web.css.DisplayStyle
import org.jetbrains.compose.web.css.FlexWrap
import org.jetbrains.compose.web.css.LineStyle
import org.jetbrains.compose.web.css.Position
import org.jetbrains.compose.web.css.backgroundColor
import org.jetbrains.compose.web.css.border
import org.jetbrains.compose.web.css.borderRadius
import org.jetbrains.compose.web.css.outline
import org.jetbrains.compose.web.css.paddingBottom
import org.jetbrains.compose.web.css.paddingLeft
import org.jetbrains.compose.web.css.paddingRight
import org.jetbrains.compose.web.css.paddingTop
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.css.width
import org.jetbrains.compose.web.dom.Button
import org.jetbrains.compose.web.dom.H2
import org.jetbrains.compose.web.dom.H3
import org.jetbrains.compose.web.dom.Input
import org.jetbrains.compose.web.dom.P
import org.jetbrains.compose.web.dom.Span
import org.jetbrains.compose.web.dom.Text
import xyz.malefic.guptarealty.components.Center
import xyz.malefic.guptarealty.model.Property
import xyz.malefic.guptarealty.styles.AppColors
import xyz.malefic.guptarealty.styles.AppModifiers
import xyz.malefic.guptarealty.styles.AppSpacing
import xyz.malefic.guptarealty.styles.BodyMdStyle
import xyz.malefic.guptarealty.styles.ContainerStyle
import xyz.malefic.guptarealty.styles.HeadlineMdStyle
import xyz.malefic.guptarealty.styles.HeadlineSmStyle
import xyz.malefic.guptarealty.styles.LabelMdStyle
import xyz.malefic.guptarealty.styles.LabelSmStyle
import xyz.malefic.guptarealty.styles.SectionStyle
import xyz.malefic.guptarealty.styles.TertiaryButtonStyle
import xyz.malefic.kutint.rgba

@Page
@Composable
fun BuyPage() {
    Column(Modifier.fillMaxSize()) {
        SearchFilterBar()
        AlertBanner()
        PropertyResultsSection()
    }
}

@Composable
fun SearchFilterBar() {
    Box(
        Modifier
            .fillMaxWidth()
            .backgroundColor(AppColors.Surface.withAlpha(0.8f))
            .backdropFilter(blur(12.px))
            .position(Position.Sticky)
            .top(80.px)
            .zIndex(90)
            .borderBottom(1.px, LineStyle.Solid, AppColors.OutlineVariant.withAlpha(0.3f))
            .padding(topBottom = 24.px),
        contentAlignment = Alignment.Center,
    ) {
        Box(ContainerStyle.toModifier()) {
            SimpleGrid(numColumns(1, md = 2), Modifier.gap(16.px).alignItems(AlignItems.Center)) {
                Box(Modifier.fillMaxWidth().position(Position.Relative)) {
                    MdiSearch(
                        Modifier
                            .position(Position.Absolute)
                            .left(16.px)
                            .top(25.percent)
                            .color(AppColors.OnSurfaceVariant),
                    )
                    Input(
                        InputType.Text,
                    ) {
                        placeholder("Search by city, neighborhood, or ZIP...")
                        style {
                            width(100.percent)
                            paddingLeft(48.px)
                            paddingRight(16.px)
                            paddingTop(12.px)
                            paddingBottom(12.px)
                            backgroundColor(Colors.White)
                            border(1.px, LineStyle.Solid, AppColors.OutlineVariant)
                            borderRadius(12.px)
                            outline("none")
                        }
                    }
                }
                Row(Modifier.gap(12.px).flexWrap(FlexWrap.Wrap)) {
                    FilterButton("Price")
                    FilterButton("Beds/Baths")
                    FilterButton("Home Type")
                    Button(
                        TertiaryButtonStyle
                            .toModifier()
                            .padding(topBottom = 9.px, leftRight = 16.px)
                            .margin(1.5.px)
                            .color(AppColors.Primary)
                            .toAttrs(),
                    ) {
                        MdiTune()
                    }
                }
            }
        }
    }
}

@Composable
fun FilterButton(label: String) {
    Button(
        attrs =
            Modifier
                .backgroundColor(AppColors.SurfaceLow)
                .border(1.px, LineStyle.Solid, AppColors.OutlineVariant)
                .borderRadius(8.px)
                .padding(topBottom = 8.px, leftRight = 16.px)
                .display(DisplayStyle.Flex)
                .alignItems(AlignItems.Center)
                .gap(8.px)
                .cursor(Cursor.Pointer)
                .toAttrs(),
    ) {
        Text(label)
        MdiExpandMore()
    }
}

@Composable
fun AlertBanner() {
    Box(
        Modifier.fillMaxWidth().backgroundColor(AppColors.PrimaryFixed.withAlpha(0.4f)).padding(topBottom = 16.px),
        contentAlignment = Alignment.Center,
    ) {
        Row(
            ContainerStyle
                .toModifier()
                .justifyContent(JustifyContent.SpaceBetween)
                .alignItems(AlignItems.Center),
        ) {
            Row(Modifier.gap(12.px), verticalAlignment = Alignment.CenterVertically) {
                MdiSmartToy(Modifier.color(AppColors.Primary))
                P(LabelMdStyle.toModifier().toAttrs()) { Text("Search smarter with AI-powered listing alerts.") }
            }
            Link("#", Modifier.color(AppColors.Primary).fontWeight(FontWeight.Bold).textDecorationLine(TextDecorationLine.Underline)) {
                Text("Set up Alerts")
            }
        }
    }
}

@Composable
fun PropertyResultsSection() {
    val mockPropertiesJson =
        """
        [
            {
                "id": "1",
                "price": 2450000.0,
                "address": "1245 Serene Vista Way, Irvine, CA",
                "beds": 4,
                "baths": 3.0,
                "sqft": 3100,
                "imageUrl": "https://lh3.googleusercontent.com/aida-public/AB6AXuCNO9a3Wt73r7849fkQ0dVFYkS9HOTFWKJkt-l92NZLzjEi5RXsGoLkz7MnFZBq3SZnDrHuW7uPYC_AEIswWuB6TMxgK9c02FoE0-9P2JAWoJBKTdnYhAIMKCSYmfVlg091XbXsVkyT2vTeQSKc7ugwxUAe3StTJJOboqFHEOVABdta7dvS_JFN9ocw9mg3uuiu5XhsaxUVfI7Wh46b-MBnsxpDuoS4X36Bj1UWj_skCeMrjQvGjucjDyVFV_i4jtjEXkH18ite5NE",
                "tags": ["Just Listed"],
                "isFeatured": false
            },
            {
                "id": "2",
                "price": 1890000.0,
                "address": "882 Lavender Lane, Newport Beach, CA",
                "beds": 3,
                "baths": 2.5,
                "sqft": 2450,
                "imageUrl": "https://lh3.googleusercontent.com/aida-public/AB6AXuCqbK8Z-oOczW3GYNVvF-qb3BFGWt40kjzhI2agdr2CAcc0ICdVKoU-ggrXOKP0KvuNk8RiRPHMS7h-tK-GMRi8nXbbeDQiRLfBDXKInlPZKrK8vfbXINf6ZXxSxN-WO-L5C7rPCCy2wxDEVf3w0037cfZeM24x5fzK5tg3KCi5NVZNkrOpTv7tloklqYdjoBK_IQuc_0vrdfx2flASEzJ-4fugclrw1CuElcLqAofVvqTXDss26-hc47la85zBx45yzinxsePbjUE",
                "tags": ["Open House: Sun"],
                "isFeatured": false
            },
            {
                "id": "3",
                "price": 3125000.0,
                "address": "402 Pacific View Ct, Laguna Beach, CA",
                "beds": 5,
                "baths": 5.0,
                "sqft": 4200,
                "imageUrl": "https://lh3.googleusercontent.com/aida-public/AB6AXuB7AS53h9Y4YjTFOgiVNJm1GTTsuImynexRFV1f43FLPtBtMXqR-s-X51xwI-cZ8hsCdB3YrSBRauMVepzBPNkonzH4NlIMQw4lvbowy6UzCbvtr_5jqB--PIks3EhYpe3xpDn4zrhJ7r7ILzLl7afbBtU-QsMkb6ZUfh_s1KQdyz2xTz8kFRNcD8r-FucWZQT_j6MEx444KF4d8SFIANRF8PMY2OYB857dn6sPUIX53MjrcM7VikHnQ1QXwkPgMz0wgO0957pa10c",
                "tags": ["New Construction"],
                "isFeatured": false
            }
        ]
        """.trimIndent()
    val properties = remember { Json.decodeFromString<List<Property>>(mockPropertiesJson) }

    Box(SectionStyle.toModifier(), contentAlignment = Alignment.Center) {
        Box(ContainerStyle.toModifier()) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .justifyContent(JustifyContent.SpaceBetween)
                    .alignItems(AlignItems.End)
                    .margin(bottom = 32.px),
            ) {
                Column {
                    H2(HeadlineMdStyle.toModifier().margin(bottom = 4.px).toAttrs()) { Text("Homes for you") }
                    P(BodyMdStyle.toModifier().color(AppColors.OnSurfaceVariant).toAttrs()) {
                        Text("${properties.size} properties found in Southern California")
                    }
                }
            }

            SimpleGrid(numColumns(1, md = 2, lg = 3), Modifier.gap(AppSpacing.Gutter)) {
                properties.forEach { property ->
                    PropertyCard(property)
                }
            }
        }
    }
}

@Composable
fun PropertyCard(property: Property) {
    Column(
        Modifier
            .backgroundColor(Colors.White)
            .borderRadius(16.px)
            .overflow(Overflow.Hidden)
            .border(1.px, LineStyle.Solid, AppColors.OutlineVariant.withAlpha(0.2f))
            .then(AppModifiers.SoftShadow),
    ) {
        Box(
            Modifier
                .fillMaxWidth()
                .height(256.px)
                .overflow(Overflow.Hidden)
                .position(Position.Relative),
        ) {
            Image(property.imageUrl, property.address, Modifier.fillMaxSize().objectFit(ObjectFit.Cover))
            if (property.tags.isNotEmpty()) {
                Box(
                    Modifier
                        .position(Position.Absolute)
                        .top(16.px)
                        .left(16.px)
                        .backgroundColor(AppColors.Primary)
                        .padding(topBottom = 4.px, leftRight = 12.px)
                        .borderRadius(50.px),
                ) {
                    Span(LabelSmStyle.toModifier().color(Colors.White).toAttrs()) { Text(property.tags.first()) }
                }
            }
            Button(
                attrs =
                    Modifier
                        .position(Position.Absolute)
                        .top(16.px)
                        .right(16.px)
                        .size(40.px)
                        .borderRadius(50.percent)
                        .backgroundColor(rgba(255, 255, 255, 0.8f))
                        .backdropFilter(blur(12.px))
                        .border(0.px)
                        .cursor(Cursor.Pointer)
                        .toAttrs(),
            ) {
                Center {
                    MdiFavorite(Modifier.color(AppColors.Primary))
                }
            }
        }
        Column(Modifier.padding(24.px)) {
            H3(HeadlineSmStyle.toModifier().margin(bottom = 8.px).toAttrs()) {
                Text("$" + property.price.toInt().toString())
            }
            P(
                BodyMdStyle
                    .toModifier()
                    .color(AppColors.OnSurfaceVariant)
                    .margin(bottom = 16.px)
                    .toAttrs(),
            ) { Text(property.address) }
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(top = 16.px)
                    .borderTop(1.px, LineStyle.Solid, AppColors.OutlineVariant.withAlpha(0.1f))
                    .gap(16.px),
            ) {
                PropertyFeature("${property.beds} Beds") { MdiBed(it) }
                PropertyFeature("${property.baths} Baths") { MdiBathtub(it) }
                PropertyFeature("${property.sqft} sqft") { MdiSquareFoot(it) }
            }
        }
    }
}

@Composable
fun PropertyFeature(
    label: String,
    icon: @Composable (Modifier) -> Unit,
) {
    Row(Modifier.gap(6.px), verticalAlignment = Alignment.CenterVertically) {
        icon(Modifier.fontSize(20.px).color(AppColors.OnSurfaceVariant).translateY(5.percent))
        Span(LabelMdStyle.toModifier().color(AppColors.OnSurfaceVariant).toAttrs()) { Text(label) }
    }
}
