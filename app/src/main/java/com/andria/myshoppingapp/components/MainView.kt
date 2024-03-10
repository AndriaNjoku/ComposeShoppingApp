package com.andria.myshoppingapp.components

import ProductListScreenView
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.andria.myshoppingapp.components.Screen.Basket.title
import com.andria.myshoppingapp.model.Product
import com.andria.myshoppingapp.model.ViewState

const val KEY_ROUTE = "route"

@Composable
fun MainScreen(
    state: ViewState<List<Product>>,
) {
    val navController = rememberNavController()

    val rememberProduct: MutableState<Product?> = remember {
        mutableStateOf(null)
    }

    val rememberWishList by remember {
        mutableStateOf(mutableListOf<List<Product>>())
    }

    val rememberAddToBag by remember {
        mutableStateOf(mutableListOf<List<Product>>())
    }

    Scaffold(
        bottomBar = { BottomNavigationView(navController) }
    ) {
        NavHost(
            navController,
            startDestination = Screen.Catalogue.route,
            modifier = Modifier.padding(it)
        ) {
            composable(Screen.Catalogue.route) {
                CatalogueSurfaceView(state = state) { catalogItem ->
                    rememberProduct.value = catalogItem
                    navController.navigate(Screen.ProductDetail.route)
                }
            }
            composable(Screen.ProductDetail.route) {
                Surface {
                    Column(
                        Modifier.padding(30.dp)
                    ) {

                        ProductDetailCardView(
                            item = rememberProduct.value!!,
                            detailed = true,
                            onItemClicked = {

                            },
                            close = {
                                navController.popBackStack()
                            },
                            addToBasket = {
                                rememberAddToBag.add(listOf(it))
                            },
                            addToWishlist = {
                                rememberWishList.add(listOf(it))
                            }
                        )
                    }
                }
            }
            composable(Screen.WishList.route) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.LightGray
                ) {
                    Column(
                        Modifier.padding(vertical = 10.dp)
                    ) {

                        Text(
                            text = title,
                            fontSize = 30.sp,
                            modifier = Modifier.padding(10.dp)
                        )

                        ProductListScreenView(
                            modifier = Modifier.padding(horizontal = 20.dp),
                            addToBag = {
                                rememberAddToBag.add(listOf(it))
                            },
                            removeFromList = {
                                rememberWishList.remove<List<Product>>(listOf(it))
                            },
                            products = rememberWishList.flatten()
                        )
                    }
                }
            }
            composable(Screen.Basket.route) {
                Surface(
                    modifier = Modifier.fillMaxSize().padding(),
                    color = Color.LightGray
                ){
                    Column(
                        Modifier.padding(10.dp)
                    ) {

                        Text(
                            text = title,
                            fontSize = 30.sp,
                            modifier = Modifier.padding(10.dp)
                        )

                        BasketScreenView(
                            modifier = Modifier,
                            products = rememberAddToBag.flatten(),
                            removeFromBag = {
                                rememberAddToBag.remove<List<Product>>(listOf(it))
                            },
                            onCheckout = {
                                rememberAddToBag.clear()
                            }
                        )
                    }
                }
            }
        }
    }
}

sealed class Screen(val route: String, val title: String, val icon: ImageVector?) {
    data object Catalogue : Screen("catalogue", "Catalogue", Icons.Filled.Home)
    data object WishList : Screen("wishlist", "Wish List", Icons.Default.Favorite)
    data object Basket : Screen("basket", "Basket", Icons.Default.ShoppingCart)
    data object ProductDetail : Screen("product/{productId}", "Product Detail", null)
}

@Composable
fun BottomNavigationView(navController: NavController) {
    val items = listOf(Screen.Catalogue, Screen.WishList, Screen.Basket)
    NavigationBar {
        val navBackStackEntry by navController.currentBackStackEntryAsState()

        val currentRoute = navBackStackEntry?.arguments?.getString(KEY_ROUTE)

        items.forEach { screen ->
            NavigationBarItem(
                icon = { Icon(screen.icon!!, contentDescription = null) }, // Replace with your icon
                label = { Text(screen.route) },
                selected = currentRoute == screen.route,
                onClick = {
                    navController.navigate(screen.route) {
                        launchSingleTop = true
                    }
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    val mockProduct = Product(
        name = "Test Product",
        image = "https://test.com/image.jpg",
        price = 9.99,
        stock = 10,
        category = "Test Category",
        oldPrice = null,
        productId = "1"
    )
    val mockState = ViewState.Success(listOf(mockProduct))
    MainScreen(state = mockState)
}