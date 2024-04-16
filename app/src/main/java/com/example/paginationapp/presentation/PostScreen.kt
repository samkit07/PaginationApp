package com.example.paginationapp.presentation

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.paginationapp.data.remote.PostModel

@Composable
fun PostScreen(
    modifier: Modifier = Modifier,
    viewModel: PostViewModel = hiltViewModel(),
    navigateDetail: (PostModel?) -> Unit
){
    val context = LocalContext.current
    val response = viewModel.postResponse.collectAsLazyPagingItems()
    LaunchedEffect(key1 = response.loadState){
        if (response.loadState.refresh is LoadState.Error || response.loadState.append is LoadState.Error){
            Toast.makeText(context, "Error: occurs", Toast.LENGTH_LONG).show()
        }
    }
    LazyColumn(modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally){
        items(response.itemCount){
            Card(
                modifier = modifier.wrapContentHeight(),
                elevation = CardDefaults.cardElevation(defaultElevation = 5.dp),
            ) {
                Column(modifier = modifier
                    .fillMaxWidth().wrapContentHeight()
                    .padding(16.dp)
                    .clickable { navigateDetail(response[it]) }) {
                    Text(text = response[it]?.id.toString()?:"-",
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = modifier.fillMaxWidth(),
                        color = Color.Gray)
                    Spacer(modifier = modifier.height(10.dp))
                    Text(text = response[it]?.title?:"-",
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Bold,
                        modifier = modifier.fillMaxWidth(),
                        color = Color.Black)
                }
            }
        }
        item {
            if (response.loadState.append is LoadState.Loading) {
                CircularProgressIndicator(modifier = modifier)
            }
        }
    }
    response.apply {
        when {
            loadState.refresh is LoadState.Loading -> {
                Box(modifier = modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
            }
        }
    }
}