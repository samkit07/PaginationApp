package com.example.paginationapp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.paginationapp.data.remote.PostModel

class DetailActivity : ComponentActivity() {

    private val post by lazy {
        intent?.getSerializableExtra(ID) as PostModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Column(modifier = Modifier.fillMaxSize().padding(15.dp)) {
                    Text(text = "Detailed View", fontSize = 40.sp, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(text = "UserId - ${post.userId}")
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(text = "Id - ${post.id}")
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(text = "Title - ${post.title}",
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black)
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(text = "Description - ${post.body}",
                        fontStyle = FontStyle.Italic,
                        modifier = Modifier.fillMaxWidth(),
                        color = Color.Gray)
                }
            }
        }
    }

    companion object {
        private const val ID = "post_id"
        fun getInstance(context: Context, postModel: PostModel?) = Intent(context, DetailActivity::class.java).apply {
            putExtra(ID, postModel)
        }
    }
}