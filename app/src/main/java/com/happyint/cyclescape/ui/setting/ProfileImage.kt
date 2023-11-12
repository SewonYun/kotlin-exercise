package com.happyint.cyclescape.ui.setting

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.happyint.cyclescape.R

@Composable
fun ProfileImageBox(imagePath: Int) {

    Box(modifier = Modifier.padding(10.dp)) {
        Image(
            painter = painterResource(imagePath),
            contentDescription = "avatar",
            contentScale = ContentScale.Crop,            // crop the image if it's not a square
            modifier = Modifier
                .size(164.dp)
                .clip(CircleShape)                       // clip to the circle shape
                .border(2.dp, Color.Gray, CircleShape)   // add a border (optional)
        )
    }
}

@Preview
@Composable
fun PreviewProfileImageBox() {
    //    val path = imagePath ?: R.drawable.card_sample1 // 입력받은 사진 파일의 위치를 인트로 돌려줘야 하나?
    ProfileImageBox(R.drawable.card_sample1)
}