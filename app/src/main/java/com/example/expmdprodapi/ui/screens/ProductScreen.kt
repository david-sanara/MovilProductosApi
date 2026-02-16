package com.example.expmdprodapi.ui.screens


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import com.example.expmdprodapi.data.model.ProductDto
import com.example.expmdprodapi.presentation.viewmodel.UiState
import com.example.expmdprodapi.presentation.viewmodel.ProductViewModel

@Composable
fun ProductScreen(vm: ProductViewModel) {
    when (val state = vm.state) {

        UiState.Loading -> Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }

        is UiState.Error -> Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text("Error: ${state.message}")
                Spacer(Modifier.height(12.dp))
                Button(onClick = { vm.loadProducts() }) {
                    Text("Reintentar")
                }
            }
        }

        is UiState.Success -> LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF00529F)),
            contentPadding = PaddingValues(top = 65.dp, start = 12.dp, end = 12.dp, bottom = 12.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(state.data, key = { it.id }) { product ->
                ProductCard(product)
            }
        }
    }
}

private fun buildImageUrl(imagePath: String?): String? {
    if (imagePath.isNullOrBlank()) return null
    return if (imagePath.startsWith("http")) {
        imagePath
    } else {
        "https://peticiones.online/api$imagePath"
    }
}

@Composable
private fun ProductCard(p: ProductDto) {

    val imageUrl = buildImageUrl(p.image)
    val painter = rememberAsyncImagePainter(model = imageUrl)

    Card(
        colors = CardDefaults.cardColors(containerColor = Color.White),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {

            // Imagen con estados
            Box(
                modifier = Modifier
                    .size(72.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color.LightGray),
                contentAlignment = Alignment.Center
            ) {
                when (painter.state) {
                    is AsyncImagePainter.State.Loading -> {
                        CircularProgressIndicator(strokeWidth = 2.dp)
                    }
                    is AsyncImagePainter.State.Error -> {
                        Text("IMG ", style = MaterialTheme.typography.labelSmall)
                    }
                    else -> Unit
                }

                Image(
                    painter = painter,
                    contentDescription = p.name,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }

            Spacer(Modifier.width(12.dp))

            Column(Modifier.weight(1f)) {
                Text(
                    text = p.name,
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.Black
                )
                Spacer(Modifier.height(4.dp))
                Text(
                    text = "${p.price} €",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.DarkGray
                )
            }
        }
    }
}