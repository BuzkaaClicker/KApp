package pl.buzkaaclicker.app.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import java.time.Instant

//@Composable
//fun MainView() {
//    val (tab, setTab) = remember { mutableStateOf(0) }
//    Scaffold(
//        topBar = {
//            TabRow(
//                selectedTabIndex = tab,
//            ) {
//                Tab(
//                    selected = tab == 0,
//                    onClick = {
//                        setTab(0)
//                    }
//                ) {
//                    Box(modifier = Modifier.padding(10.dp)) {
//                        Text("dsaadsasd")
//                    }
//                }
//                Tab(
//                    selected = tab == 1,
//                    onClick = {
//                        setTab(1)
//                    }
//                ) {
//                    Box(modifier = Modifier.padding(10.dp)) {
//                        Text("dsaadsasd")
//                    }
//                }
//            }
//        }
//    ) {
//        Button(
//            modifier = Modifier.padding(top = 5.dp),
//            onClick = {
//
//            }
//        ) {
//            Text("dsadsadsdsadsa: ${Instant.now()}")
//        }
//    }
//}
//
//@Composable
//fun Greeting(name: String) {
//    Text(text = "Hello $name!")
//}
