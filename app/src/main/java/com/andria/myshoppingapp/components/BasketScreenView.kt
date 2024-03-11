package com.andria.myshoppingapp.components

import ProductListScreenView
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.andria.myshoppingapp.model.Product


@Composable
fun BasketScreenView(
    modifier: Modifier,
    products: List<Product>,
    removeFromBag: (Product) -> Unit,
    onCheckout: () -> Unit
) {
    val items = remember { mutableStateListOf(*products.toTypedArray()) }

    val groupedItems = items.groupBy { it.productId }
        .map { (_, productList) ->
            productList.first().apply { quantity = productList.size }
        }

    val totalPrice = groupedItems.sumByDouble { it.price * it.quantity }

    Column {
        ProductListScreenView(
            modifier = modifier.weight(1f),
            products = groupedItems,
            removeFromList = removeFromBag,
            addToBag = null
        )

        Row(
            modifier = Modifier.padding(20.dp),
            verticalAlignment = Alignment.Bottom) {
            Text(
                text = "Total: £${"%.2f".format(totalPrice)}",
                color = androidx.compose.ui.graphics.Color.Black,
                fontSize = 24.sp
            )

            Button(onClick = onCheckout, modifier = Modifier.padding(start = 16.dp)) {
                Text("Checkout")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewBasketScreenView() {
val product = Product( "1", "T-shirt", 10.0, 5, "t-shirt", 9.0, "t-shirt"   )
    BasketScreenView(
        modifier = Modifier,
        products = listOf(product),
        removeFromBag = {},
        onCheckout = {}
    )
}