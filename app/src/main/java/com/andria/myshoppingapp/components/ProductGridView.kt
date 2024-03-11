package com.andria.myshoppingapp.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.andria.myshoppingapp.model.ViewState
import com.andria.myshoppingapp.model.Product

/**
 * Catalogue screen composable : The PLP
 */
@Composable
fun CatalogueSurfaceView(
    modifier: Modifier = Modifier,
    state: ViewState<List<Product>>,
    onItemClicked: (Product) -> Unit
) {
    when (state) {
        is ViewState.Loading -> {
            LoadingView()
        }

        is ViewState.Error -> {
            Text(
                text = "Oops! No products found. Please try again later.",
                fontSize = 20.sp,
                modifier = Modifier.padding(10.dp)
            )
        }

        is ViewState.Success -> {
            //main surface
            Surface(
                modifier = modifier.fillMaxSize(),
                color = Color.Gray
            ) {
                Column(
                    Modifier.padding(horizontal = 20.dp)
                ) {

                    Text(text = "Catalogue", fontSize = 30.sp, modifier = Modifier.padding(10.dp))

                    if (state.data.isEmpty()) {
                        Text(
                            text = "Oops! No products found. Please try again later.",
                            fontSize = 20.sp,
                            modifier = Modifier.padding(10.dp)
                        )
                    } else {
                        LazyVerticalGrid(
                            columns = GridCells.Fixed(2),
                            verticalArrangement = Arrangement.spacedBy(30.dp),
                            horizontalArrangement = Arrangement.spacedBy(30.dp),
                        ) {
                            items(state.data.size) { index ->
                                val item = state.data[index]

                                ProductDetailCardView(
                                    modifier = modifier.height(220.dp)
                                        .background(color = Color.White).padding(0.dp),
                                    item = item,
                                    onItemClicked = onItemClicked
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CatalogueScreenPreview() {
    CatalogueSurfaceView(
        state = ViewState.Success(
            listOf(
                Product(
                    "name",
                    "image",
                    10.0,
                    10,
                    "category",
                    10.0,
                    "productId"
                ),
                Product(
                    "name",
                    "image",
                    10.0,
                    10,
                    "category",
                    10.0,
                    "productId"
                ),
                Product(
                    "name",
                    "image",
                    10.0,
                    10,
                    "category",
                    10.0,
                    "productId"
                ),
                Product(
                    "name",
                    "image",
                    10.0,
                    10,
                    "category",
                    10.0,
                    "productId"
                ),
                Product(
                    "name",
                    "image",
                    10.0,
                    10,
                    "category",
                    10.0,
                    "productId"
                ),
            ),


            ),
        onItemClicked = {

        }
    )
}

@Preview(showBackground = true)
@Composable
fun CatalogueScreenPreviewNoProducts() {
    CatalogueSurfaceView(
        state = ViewState.Success(
            emptyList()

            ),
        onItemClicked = {

        }
    )
}