package com.example.cscon25sampleapp.ui.main

import androidx.annotation.DrawableRes
import com.example.cscon25sampleapp.R
import kotlinx.serialization.Serializable


interface NavDestination {

}

sealed class TopDestination : NavDestination {
    @Serializable
    object SplashScreen : TopDestination()

    @Serializable
    data class OtherProfile(val userId: Int) : TopDestination()
}

sealed class BottomNavDestination : NavDestination {
    @Serializable
    object Home : BottomNavDestination()

    @Serializable
    object Search : BottomNavDestination()

    @Serializable
    object Shorts : BottomNavDestination()

    @Serializable
    object Store : BottomNavDestination()

    @Serializable
    object MyProfile : BottomNavDestination()
}

data class BottomNavRouteItem(val route: BottomNavDestination, @DrawableRes val icon: Int)

val bottomNavDestinations = buildList {
    add(BottomNavRouteItem(BottomNavDestination.Home, R.drawable.ic_home))
    add(BottomNavRouteItem(BottomNavDestination.Search, R.drawable.ic_search))
    add(BottomNavRouteItem(BottomNavDestination.Shorts, R.drawable.ic_reels))
    add(BottomNavRouteItem(BottomNavDestination.Store, R.drawable.ic_shop))
    add(BottomNavRouteItem(BottomNavDestination.MyProfile, R.drawable.ic_profile))
}

sealed class BottomSheetNavDestination : NavDestination {
    @Serializable
    object LoginRegisterBottomSheet : BottomNavDestination()
}
