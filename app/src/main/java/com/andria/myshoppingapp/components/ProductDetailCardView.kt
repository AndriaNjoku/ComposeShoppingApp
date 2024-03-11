package com.andria.myshoppingapp.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.andria.myshoppingapp.model.Product
import com.andria.myshoppingapp.ui.theme.MyShoppingAppTheme

@Composable
fun LabelWithText(
    color: Color = Color.Black,
    label: String,
    text: String,
    hideLabel: Boolean = false
) {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 30.dp)
    ) {
        if (!hideLabel) {
            Text(text = label, fontWeight = FontWeight.Bold, textAlign = TextAlign.Start)
        }
        Text(text = text, color = color, textAlign = TextAlign.Start)
    }
}

@Composable
fun CloseButton(onClick: () -> Unit) {
    IconButton(onClick = onClick){
        Icon(Icons.Default.Close, contentDescription = "Close", modifier = Modifier)
    }
}

@Composable
fun ProductDetailCardView(
    modifier: Modifier = Modifier,
    item: Product,
    onItemClicked: (Product) -> Unit,
    close: () -> Unit = {},
    addToWishlist: (Product) -> Unit = {},
    addToBasket: (Product) -> Unit = {},
    detailed: Boolean = false
) {
        ElevatedCard(
        ) {
            Column(
                modifier
                    .fillMaxSize()
                    .background(Color.White),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly,
            ) {

                if (detailed)
                    Row(
                        Modifier.fillMaxWidth(0.8f),
                        horizontalArrangement = Arrangement.Start
                    ) {
                        CloseButton() {
                            close.invoke()
                        }
                        Text(
                            text = "Product Detail",
                            modifier = Modifier.padding(start = 35.dp, top = 13.dp),
                            color = Color.Black,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Start
                        )
                    }

                Box(
                    modifier = Modifier
                        .clickable { onItemClicked(item) }

                ) {
                    Image(
                        painter = rememberImagePainter(data = item.image),
                        contentDescription = null,
                    )
                }

                if (detailed) {
                    LabelWithText(color = Color.Gray, label = "Name", text = item.name)
                    LabelWithText(label = "Category", text = item.category)
                    LabelWithText(label = "Price", text = "£${item.price}")
                    LabelWithText(label = "Stock", text = "${item.stock}")
                } else {
                    LabelWithText(
                        color = Color.Gray,
                        label = item.name,
                        text = item.name,
                        hideLabel = true
                    )
                    LabelWithText(label = "Price", text = "£${item.price}", hideLabel = true)
                }

                if (detailed) {
                    Box(contentAlignment = Alignment.BottomEnd, modifier = Modifier.fillMaxWidth()) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(0.5f),
                            horizontalArrangement = Arrangement.SpaceEvenly

                        ){
                            FloatingActionButton(onClick = { addToWishlist(item) }) {
                                Icon(Icons.Default.Favorite, contentDescription = "Add to Wishlist")
                            }
                            FloatingActionButton(onClick = { addToBasket(item) }) {
                                Icon(Icons.Default.ShoppingCart, contentDescription = "Add to Basket")
                            }
                        }
                    }
                }
            }
        }
}

@Preview(showBackground = true)
@Composable
fun ProductDetailCardSurfacePreview() {
    val mockProduct = Product(
        name = "Test Product",
        image = "https://test.com/image.jpg",
        price = 9.99,
        stock = 10,
        category = "Test Category",
        oldPrice = null,
        productId = "1"
    )
    MyShoppingAppTheme {
        Box(Modifier.size(200.dp)) {


            ProductDetailCardView(item = mockProduct, onItemClicked = {})
        }
    }
}