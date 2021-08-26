package pl.buzkaaclicker.app.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun NavigationHeader() {
    Row {
        Image(
            painter = painterResource("ui/logo.png"),
            contentDescription = "Logo",
            modifier = Modifier.size(30.dp),
        )

        Row(
            modifier = Modifier.padding(6.dp),
        ) {
            Text("Buzkaa", fontWeight = FontWeight.Light, color = Color.White, fontSize = 24.sp)
            Text("Clicker", fontWeight = FontWeight.Bold, color = Color.White, fontSize = 24.sp)
        }
    }
}

@Composable
fun NavigationBar() {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .width(300.dp)
            .padding(20.dp)
            .fillMaxHeight(),
    ) {
        NavigationHeader()
    }
}
