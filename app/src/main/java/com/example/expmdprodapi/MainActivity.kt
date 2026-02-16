package com.example.expmdprodapi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Surface
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.expmdprodapi.presentation.viewmodel.ProductViewModel
import com.example.expmdprodapi.ui.screens.ProductScreen
import com.example.expmdprodapi.ui.theme.ExPMDProdApiTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ExPMDProdApiTheme() {
                Surface {
                    val vm: ProductViewModel = viewModel()
                    ProductScreen(vm)
                }
            }
        }
    }
}