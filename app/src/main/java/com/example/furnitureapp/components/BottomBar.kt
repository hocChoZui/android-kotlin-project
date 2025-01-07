package com.example.furnitureapp.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.furnitureapp.model.NavBarItem

@Composable
fun BottomNavBar(
    navBarItems: List<NavBarItem>,
    selectedIndex: Int,
    onNavigationItemSelected: (Int) -> Unit,
    modifier: Modifier = Modifier
) {

    NavigationBar(
        containerColor = Color.White,
        modifier = modifier
            .fillMaxWidth()
            .height(80.dp)
    ) {
        navBarItems.forEachIndexed { index, navItem ->
            NavigationBarItem(
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = Color.White,
                ),
                selected = selectedIndex == index,
                onClick = {
                    onNavigationItemSelected(index)
                },
                icon = {
                    Icon(
                        modifier = Modifier.size(24.dp),
                        painter = painterResource(id = navItem.iconResId),
                        contentDescription = navItem.title,
                        tint = if (selectedIndex == index) Color(0xFF896de7) else Color(0xFFc9c9c9)
                    )
                },
                label = null
            )
        }
    }
}