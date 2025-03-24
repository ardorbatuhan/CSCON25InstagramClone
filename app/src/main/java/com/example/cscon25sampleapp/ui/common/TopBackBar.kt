package com.example.cscon25sampleapp.ui.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.example.cscon25sampleapp.R

@Composable
fun TopBackBar(modifier: Modifier = Modifier, middleText: String?, onBack: () -> Unit) {
    Box(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .heightIn(min = 54.dp),
    ) {
        Icon(
            modifier = Modifier
                .align(Alignment.CenterStart)
                .clickable { onBack() },
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_arrow_back),
            contentDescription = null
        )
        middleText?.let {
            Text(modifier = Modifier.align(Alignment.Center), text = middleText)
        }
    }
}