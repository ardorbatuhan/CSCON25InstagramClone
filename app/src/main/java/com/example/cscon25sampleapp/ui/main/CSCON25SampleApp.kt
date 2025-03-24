package com.example.cscon25sampleapp.ui.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.dialog
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.cscon25sampleapp.R
import com.example.cscon25sampleapp.ui.bottomSheet.LoginRegisterBottomSheet
import com.example.cscon25sampleapp.ui.home.HomeScreen
import com.example.cscon25sampleapp.ui.profile.ProfileScreen
import com.example.cscon25sampleapp.ui.theme.CSCON25SampleAppTheme
import com.example.cscon25sampleapp.ui.theme.getNavigationBarItemColors

@Composable
fun CSCON25SampleApp(viewModel: MainViewModel) {
    val navController = rememberNavController()
    val startDestination by viewModel.getStartDestinationStateFlow.collectAsStateWithLifecycle()

    CSCON25SampleAppTheme {
        if (startDestination == TopDestination.SplashScreen) {
            SplashScreen()
        } else {
            Scaffold(
                modifier = Modifier.fillMaxSize(),
                bottomBar = {
                    BottomNavigationBar(navController)
                }
            ) { innerPadding ->
                Box(modifier = Modifier.padding(innerPadding)) {
                    CSCON25SampleAppNavHost(
                        navController = navController,
                        startDestination = startDestination,
                        onNavigateTo = {
                            navController.navigate(it)
                        }
                    )
                }
            }
        }

    }
}

@Composable
fun SplashScreen() {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.Black)) {
        Image(
            painterResource(R.drawable.ic_instagram),
            modifier = Modifier
                .align(Alignment.Center)
                .size(100.dp),
            contentDescription = ""
        )
    }
}

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    Column {
        HorizontalDivider()
        NavigationBar {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            bottomNavDestinations.forEach { route ->
                val currentDestination = navBackStackEntry?.destination
                NavigationBarItem(
                    selected = currentDestination?.hierarchy?.any { it.hasRoute(route.route::class) } == true,
                    icon = {
                        Icon(
                            imageVector = ImageVector.vectorResource(route.icon),
                            contentDescription = null
                        )
                    },
                    colors = getNavigationBarItemColors(),
                    onClick = {
                        navController.navigate(route.route) {
                            // Pop up to the start destination of the graph to
                            // avoid building up a large stack of destinations
                            // on the back stack as users select items
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            // Avoid multiple copies of the same destination when
                            // reselecting the same item
                            launchSingleTop = true
                            // Restore state when reselecting a previously selected item
                            restoreState = true
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun CSCON25SampleAppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: NavDestination,
    onNavigateTo: (NavDestination) -> Unit = {},
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable<BottomNavDestination.Home> { backStackEntry ->
            HomeScreen(
                onNavigateToProfile = {
                    onNavigateTo(TopDestination.OtherProfile(it))
                },
                onNavigateTo = onNavigateTo
            )
        }
        composable<BottomNavDestination.Search> { backStackEntry ->
            Text(text = "This is a Search screen")
        }
        composable<BottomNavDestination.Shorts> { backStackEntry ->
            Text(text = "This is a Shorts screen")
        }
        composable<BottomNavDestination.Store> { backStackEntry ->
            Text(text = "This is a Store screen")
        }
        composable<BottomNavDestination.MyProfile> { backStackEntry ->
            ProfileScreen(isMyProfile = true)
        }
        composable<TopDestination.OtherProfile> { backStackEntry ->
            val otherProfile = backStackEntry.toRoute<TopDestination.OtherProfile>()
            val userId = otherProfile.userId
            backStackEntry.arguments?.putInt("userId", userId)
            ProfileScreen(isMyProfile = false) {
                navController.popBackStack()
            }
        }
        dialog<BottomSheetNavDestination.LoginRegisterBottomSheet> { backStackEntry ->
            LoginRegisterBottomSheet {
                navController.popBackStack()
            }
        }
    }
}