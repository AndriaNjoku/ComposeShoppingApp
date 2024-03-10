package com.andria.myshoppingapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import com.andria.myshoppingapp.components.CatalogueSurfaceView
import com.andria.myshoppingapp.components.MainScreen
import com.andria.myshoppingapp.ui.theme.MyShoppingAppTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var viewModelFactory: CatalogueViewModelFactory

    private val viewModel: CatalogueViewModel by viewModels {
        viewModelFactory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyShoppingAppTheme {
                viewModel.viewState.collectAsState().let { state ->
                    MainScreen(state = state.value)
                }
            }
        }
    }

}