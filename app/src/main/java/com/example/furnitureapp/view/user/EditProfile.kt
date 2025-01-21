import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.furnitureapp.model.User
import com.example.furnitureapp.viewmodel.AuthState
import com.example.furnitureapp.viewmodel.UserViewModel
import com.google.android.gms.auth.api.Auth

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditProfileScreen(userId : Int, navController: NavController,userViewModel: UserViewModel) {
    val userState by userViewModel::userState
    userViewModel.getUserAddress(userId)
   val listOfAddress = userViewModel.listAddress
    val defaultAddress = listOfAddress.find { it.mac_dinh == 1 }
        Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Sửa Hồ sơ") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack()}) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {

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
           if(userState is AuthState.XacThuc){
               val user = (userState as AuthState.XacThuc).user

               ProfileHeaderCard(user)
               Spacer(modifier = Modifier.height(16.dp))
               ProfileInfoCard("Tên", user.ten_khach_hang, isEditable = true)
               ProfileInfoCard("Username/Email", user.tai_khoan, isEditable = false)
               ProfileInfoCard("Ngày sinh", "Thiết lập ngay", isEditable = true)
               if (defaultAddress != null) {
                   ProfileInfoCard("Điện thoại", defaultAddress.so_dien_thoai, isEditable = true)
                   ProfileInfoCard("Địa chỉ", defaultAddress.dia_chi, isEditable = true)
               }

           }
            Button(
                modifier = Modifier.width(160.dp).height(50.dp).align(Alignment.CenterHorizontally),
                onClick = {
                   navController.navigate("change_password/$userId")
                },
                shape = RoundedCornerShape(50),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF896de7),
                    contentColor = Color.White
                )
            ) {
                Text(text = "Đổi mật khẩu", fontWeight = FontWeight.W400, fontSize = 18.sp)
            }
        }
    }
}

@Composable
fun ProfileHeaderCard(user : User) {
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
                AsyncImage(
                    modifier = Modifier
                        .fillMaxSize(),
                    model =user.avatar,
                    contentDescription = "avatar",
                    contentScale = ContentScale.Crop
                )
            }
            }
            Spacer(modifier = Modifier.height(8.dp))
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