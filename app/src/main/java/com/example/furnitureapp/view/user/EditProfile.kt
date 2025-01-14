package com.example.furnitureapp.view.user

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class EditProfileActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EditProfileScreenCardBased()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditProfileScreenCardBased() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Sửa Hồ sơ") },
                navigationIcon = {
                    IconButton(onClick = { /* Handle back action */ }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    Text(
                        text = "Lưu",
                        color = Color.LightGray,
                        modifier = Modifier
                            .padding(16.dp)
                            .clickable { /* Handle save action */ }
                    )
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .background(Color.White)
        ) {
            ProfileHeaderCard()
            Spacer(modifier = Modifier.height(16.dp))
            ProfileInfoCard("Tên", "Thiết lập ngay", isEditable = true)
            ProfileInfoCard("Giới tính", "Thiết lập ngay", isEditable = true)
            ProfileInfoCard("Ngày sinh", "Thiết lập ngay", isEditable = true)
            ProfileInfoCard("Điện thoại", "*******87", isEditable = false)
            ProfileInfoCard("Email", "Thiết lập ngay", isEditable = true)
            ProfileInfoCard("Tài khoản liên kết", "", isEditable = false)
        }
    }
}

@Composable
fun ProfileHeaderCard() {
    Card(
        shape = RoundedCornerShape(bottomStart = 32.dp, bottomEnd = 32.dp),
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFFF5722)),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .background(Color.LightGray, shape = CircleShape)
                    .clickable { /* Handle profile picture change */ },
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Sửa", color = Color.Gray, fontSize = 14.sp)
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Chạm để thay đổi",
                color = Color.White,
                fontSize = 14.sp
            )
        }
    }
}

@Composable
fun ProfileInfoCard(title: String, value: String, isEditable: Boolean) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clickable(enabled = isEditable) { /* Handle item click */ },
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = title,
                modifier = Modifier.weight(1f),
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            )
            Text(
                text = value,
                color = if (isEditable) Color(0xFFFF5722) else Color.Gray,
                textAlign = TextAlign.End
            )
        }
    }
}
