package com.example.cscon25sampleapp.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.example.cscon25sampleapp.R
import com.example.cscon25sampleapp.extension.clickableWithoutRipple
import com.example.cscon25sampleapp.ui.main.BottomSheetNavDestination
import com.example.cscon25sampleapp.ui.main.NavDestination

@Composable
fun HomeTopBar(
    modifier: Modifier = Modifier,
    onNavigateTo: (NavDestination) -> Unit,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier
                .clickableWithoutRipple {  }
                .padding(horizontal = 3.dp, vertical = 6.dp),
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_instagram_logo),
            contentDescription = null
        )
        Spacer(modifier = Modifier.weight(1f))
        HomeTopBarIconRow(onNavigateTo = onNavigateTo)
    }
}

@Composable
private fun HomeTopBarIconRow(onNavigateTo: (NavDestination) -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Icon(
            modifier = Modifier
                .clickable { onNavigateTo(BottomSheetNavDestination.LoginRegisterBottomSheet) },
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_add_filled),
            contentDescription = null
        )
        Icon(
            modifier = Modifier
                .clickable { onNavigateTo(BottomSheetNavDestination.LoginRegisterBottomSheet) },
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_heart),
            contentDescription = null
        )
        Icon(
            modifier = Modifier
                .clickable { onNavigateTo(BottomSheetNavDestination.LoginRegisterBottomSheet) },
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_share),
            contentDescription = null
        )
    }
}