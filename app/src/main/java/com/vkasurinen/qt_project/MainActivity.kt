

package com.vkasurinen.qt_project

import android.content.Intent
import android.os.Bundle
import org.qtproject.qt.android.QtNative
import org.qtproject.qt.android.bindings.QtActivity

class MainActivity : QtActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Load the QML file
        loadQml()
    }

    private fun loadQml() {
        // Load the QML file from the assets folder
        val qmlFilePath = "qrc:/Main.qml"
        QtNative.onNewIntent(Intent().apply {
            putExtra("qmlFilePath", qmlFilePath)
        })
    }
}







//package com.vkasurinen.qt_project
//
//import android.os.Bundle
//import android.util.Log
//import androidx.activity.ComponentActivity
//import androidx.activity.compose.setContent
//import androidx.activity.enableEdgeToEdge
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.padding
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.collectAsState
//import androidx.compose.runtime.getValue
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import androidx.lifecycle.lifecycleScope
//import com.vkasurinen.qt_project.domain.BreweryRepository
//import com.vkasurinen.qt_project.presentation.BreweryViewModel
//import com.vkasurinen.qt_project.presentation.BreweryState
//import com.vkasurinen.qt_project.ui.theme.QT_ProjectTheme
//import kotlinx.coroutines.launch
//import org.koin.android.ext.android.inject
//
//
//class MainActivity : ComponentActivity() {
//    private val breweryViewModel: BreweryViewModel by inject()
//    private val breweryRepository: BreweryRepository by inject()
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
//        setContent {
//            QT_ProjectTheme {
//                val state by breweryViewModel.state.collectAsState()
//
//                Column(
//                    modifier = Modifier
//                        .fillMaxSize(),
//                    verticalArrangement = Arrangement.Center
//
//                ) {
//                    BreweryInfoScreen(
//                        modifier = Modifier
//                            .padding(10.dp),
//                        state = state
//                    )
//                }
//            }
//        }
//    }
//
//    private fun fetchBreweries() {
//        lifecycleScope.launch {
//            breweryViewModel.fetchBreweries()
//            breweryViewModel.state.collect { state ->
//                when {
//                    state.isLoading -> Log.d("MainActivity", "Loading...")
//                    state.error != null -> Log.d("MainActivity", "Error: ${state.error}")
//                    else -> {
//                        Log.d("MainActivity", "Northern Most Brewery: ${state.northernMostBrewery?.name}")
//                        Log.d("MainActivity", "Southern Most Brewery: ${state.southernMostBrewery?.name}")
//                        Log.d("MainActivity", "Longest Name Brewery: ${state.longestNameBrewery?.name}")
//                    }
//                }
//            }
//        }
//    }
//
//    private fun testBreweryRepository() {
//        lifecycleScope.launch {
//            breweryRepository.fetchBreweriesFromIreland().collect { result ->
//                when (result) {
//                    is Resource.Loading -> Log.d("MainActivity", "Loading...")
//                    is Resource.Error -> Log.d("MainActivity", "Error: ${result.message}")
//                    is Resource.Success -> {
//                        val breweries = result.data
//                        Log.d("MainActivity", "Fetched Breweries: $breweries")
//                    }
//
//                    else -> {}
//                }
//            }
//        }
//    }
//}
//
//
//@Composable
//fun BreweryInfoScreen(state: BreweryState, modifier: Modifier = Modifier) {
//    Column(
//        modifier = modifier,
//        verticalArrangement = Arrangement.spacedBy(8.dp)
//    ) {
//        Text(text = "Northern Most Brewery: ${state.northernMostBrewery?.name ?: "Loading..."}")
//        Text(text = "Southern Most Brewery: ${state.southernMostBrewery?.name ?: "Loading..."}")
//        Text(text = "Longest Name Brewery: ${state.longestNameBrewery?.name ?: "Loading..."}")
//        if (state.isLoading) {
//            Text(text = "Loading...")
//        }
//        state.error?.let {
//            Text(text = "Error: $it")
//        }
//    }
//}