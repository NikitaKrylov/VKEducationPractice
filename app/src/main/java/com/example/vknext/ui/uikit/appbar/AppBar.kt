package com.example.vknext.ui.uikit.appbar

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.size
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.vknext.R
import com.example.vknext.ui.FeedBackRoute
import com.example.vknext.ui.ProfileRoute

@Composable
fun AppBar(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {

    val navItems = remember {
        listOf(
            NavItem(
                name = "Поиск",
                icon = R.drawable.ic_search,
                route = FeedBackRoute.toRoute(),
            ),
            NavItem(
                name = "Профиль",
                icon = R.drawable.ic_profile,
                route = ProfileRoute.toRoute()
            )
        )
    }

    val routes = remember { navItems.map { it.route } }
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    AnimatedVisibility(
        visible = currentRoute in routes
    ) {
        NavigationBar(
            containerColor = Color.Transparent,
            modifier = Modifier
                .then(modifier)
        ) {
            navItems.forEach { item ->
                NavigationBarItem(
                    selected = item.route == currentRoute,
                    icon = {
                        Icon(
                            painter = painterResource(item.icon),
                            contentDescription = item.name,
                            modifier = Modifier.size(22.dp)
                        )
                    },
                    label = {
                        Text(
                            text = item.name,
                        )
                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Color(0xFF2975CC),
                        selectedTextColor = Color(0xFF2975CC),
                        indicatorColor = Color.Transparent,
                        unselectedIconColor = Color(0xFF99A2AD),
                        unselectedTextColor = Color(0xFF99A2AD),

                    ),
                    onClick = {
                        navController.navigate(item.route)
                    },
                    modifier = Modifier
                        .size(48.dp)
                )
            }

        }
    }


}

private data class NavItem(
    val name: String,
    val icon: Int,
    val route: String,
)