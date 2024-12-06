package com.demo.categoryapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.demo.categoryapp.videmodels.CategoryViewModel
import com.demo.categoryapp.R

@Composable
fun CategoryScreen(onClick: (category: String) -> Unit) {
    val categoryViewModel: CategoryViewModel = hiltViewModel()
    val categories: State<List<String>> = categoryViewModel.categories.collectAsState()

    if (categories.value.isEmpty()) {
        Box(
            modifier = Modifier.fillMaxSize(1f).background(Color(0xFFf8fafd)),
            contentAlignment = Alignment.Center,
        ) {
            Text(text = "Loading...",color = Color.Black, style = MaterialTheme.typography.headlineLarge)
        }
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize(1f)
                .background(Color(0xFFf8fafd))

        ) {
            Header()

            Spacer(modifier = Modifier.padding(8.dp, 0.dp))

            LazyVerticalGrid(
                columns = GridCells.Fixed(1),
                contentPadding = PaddingValues(8.dp),
                verticalArrangement = Arrangement.SpaceAround,
            ) {
                items(categories.value.distinct()) {
                    CategoryItem(category = it, onClick)
                }
            }

        }

    }

}
@Composable
fun Header() {
    Row(
        modifier = Modifier
            .fillMaxWidth(1f)
            .padding(15.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        BoxedImage {
            Image(
                imageVector = Icons.Default.Home, contentDescription = null,
                modifier = Modifier.padding(8.dp)
            )
        }

        Text(
            text = "Categories",
            fontWeight = FontWeight.SemiBold,
            fontSize = 20.sp,
            color = Color.Black,
            fontFamily = FontFamily(Font(R.font.montserrat)),
        )
        BoxedImage {
            Image(
                imageVector = Icons.Default.Search, contentDescription = null,
                modifier = Modifier.padding(8.dp)
            )
        }
    }

}

@Composable
fun CategoryItem(category: String, onClick: (category: String) -> Unit) {
    Box(
        modifier = Modifier
            .padding(10.dp)
            .shadow(
                elevation = 4.dp, // Add shadow with a defined elevation
                shape = RoundedCornerShape(10.dp) // Define the rounded corner shape
            )
            .clip(RoundedCornerShape(10.dp)) // Ensure corners are clipped to the radius
            .clickable {
                onClick(category)
            }
            .size(160.dp)
            .paint(
                painter = painterResource(id = R.drawable.bg),
                contentScale = ContentScale.Crop
            )
            .border(1.dp, Color(0xFFEEEEEE), RoundedCornerShape(10.dp)), // Ensure border follows the shape
        contentAlignment = Alignment.TopCenter
    ) {
        // Layer to position elements inside the Box
        Box(modifier = Modifier.fillMaxSize()) {
            // Text positioned at the top center
            Text(
                text = category,
                fontSize = 18.sp,
                color = Color.Black,
                modifier = Modifier
                    .align(Alignment.TopCenter) // Align text to the top center
                    .padding(top = 40.dp),
                style = MaterialTheme.typography.bodyMedium
            )

            // ArrowForward icon positioned at the bottom right
            Image(
                imageVector = Icons.Default.ArrowForward,
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.BottomEnd) // Align to the bottom-right corner
                    .padding(15.dp) // Add padding to prevent it from sticking to the edges
            )
        }
    }
}











