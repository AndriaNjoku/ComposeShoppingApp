import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.DismissDirection
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FractionalThreshold
import androidx.compose.material.SwipeToDismiss
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.rememberDismissState
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.andria.myshoppingapp.model.Product


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ProductListScreenView(
    modifier: Modifier,
    products: List<Product>,
    addToBag: ((Product) -> Unit)?,
    removeFromList: (Product) -> Unit
) {
    val items = remember { mutableStateListOf(*products.toTypedArray()) }

        LazyColumn(
            modifier = modifier,
           verticalArrangement = Arrangement.spacedBy(30.dp)
        ) {
            items(items.size) { index ->
                val item = items[index]
                val dismissState = rememberDismissState()

                SwipeToDismiss(
                    state = dismissState,
                    directions = setOf(DismissDirection.EndToStart),
                    dismissThresholds = { direction ->
                        FractionalThreshold(if (direction == DismissDirection.StartToEnd) 0.25f else 0.5f)
                    },
                    background = {

                                 Box(
                                     Modifier.fillMaxSize()
                                         .background(Color.Red)
                                         .padding(10.dp)
                                 ){

                                 }

                    },
                    dismissContent = {
                        Row(
                            modifier = Modifier.background(Color.LightGray),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(5.dp)
                        ) {
                            Image(
                                painter = rememberImagePainter(data = item.image),
                                contentDescription = "Product Image",
                                modifier = Modifier
                                    .size(85 .dp)
                                    .clip(CircleShape)
                                    .weight(1f),
                                contentScale = androidx.compose.ui.layout.ContentScale.Crop
                            )
                            Column(
                                modifier = Modifier
                                    .padding(start = 8.dp)
                                    .weight(3f)
                            ) {
                                Text(text = item.name, fontWeight = FontWeight.Bold)
                                Text(text = "$${item.price}")
                                if (addToBag == null) {
                                    Text(text = "Quantity: ${item.quantity}") // Add this line
                                }
                            }

                            if (addToBag != null) {
                                IconButton(onClick = {
                                    addToBag(item)
                                }
                                ) {
                                    Icon(Icons.Default.ShoppingCart, contentDescription = "Close")
                                }
                            }
                        }
                    }
                )

                if (dismissState.isDismissed(DismissDirection.EndToStart)) {
                    items.removeAt(index)
                    removeFromList(item)
                }
        }
    }
}