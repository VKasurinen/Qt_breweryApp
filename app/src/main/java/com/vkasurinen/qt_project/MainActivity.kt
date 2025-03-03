package com.vkasurinen.qt_project

//import android.os.Bundle
//import android.view.ViewGroup
//import android.widget.FrameLayout
//import androidx.activity.ComponentActivity
//import androidx.activity.compose.setContent
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.viewinterop.AndroidView
//import org.qtproject.example.QT_TestingApp.QT_Testing.Main
//import org.qtproject.qt.android.QtQmlStatus
//import org.qtproject.qt.android.QtQmlStatusChangeListener
//import org.qtproject.qt.android.QtQuickView
//
//class MainActivity : ComponentActivity(), QtQmlStatusChangeListener {
//
//    private val TAG = "MainActivity"
//    private var qtQuickView: QtQuickView? = null
//    private var m_mainQmlContent: Main = Main()
//    private val statusNames = hashMapOf(
//        QtQmlStatus.READY to "READY",
//        QtQmlStatus.LOADING to "LOADING",
//        QtQmlStatus.ERROR to "ERROR",
//        QtQmlStatus.NULL to "NULL"
//    )
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContent {
//            Box(modifier = Modifier.fillMaxSize()) {
//                AndroidView(
//                    factory = { context ->
//                        FrameLayout(context).apply {
//                            qtQuickView = QtQuickView(context)
//                            qtQuickView?.setStatusChangeListener(this@MainActivity)
//                            val params: ViewGroup.LayoutParams = FrameLayout.LayoutParams(
//                                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT
//                            )
//                            addView(qtQuickView, params)
//                        }
//                    }
//                )
//                // Load QML content
//                qtQuickView?.loadContent(m_mainQmlContent)
//            }
//            // Load QML content
//            qtQuickView?.loadContent(m_mainQmlContent)
//        }
//
//    }
//
//    override fun onStatusChanged(status: QtQmlStatus?) {
//        // Handle QML status changes
//        val qmlStatus = statusNames[status] ?: "UNKNOWN"
//        println("QML Status: $qmlStatus")
//    }
//}







import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import com.vkasurinen.qt_project.domain.BreweryRepository
import com.vkasurinen.qt_project.presentation.BreweryViewModel
import com.vkasurinen.qt_project.presentation.BreweryState
import com.vkasurinen.qt_project.ui.theme.QT_ProjectTheme
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject


class MainActivity : ComponentActivity() {
    private val breweryViewModel: BreweryViewModel by inject()
    private val breweryRepository: BreweryRepository by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            QT_ProjectTheme {
                val state by breweryViewModel.state.collectAsState()

                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.Center

                ) {
                    BreweryInfoScreen(
                        modifier = Modifier
                            .padding(10.dp),
                        state = state
                    )
                }
            }
        }
    }

    @Composable
    fun BreweryInfoScreen(state: BreweryState, modifier: Modifier = Modifier) {
        Column(
            modifier = modifier,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(text = "Northern Most Brewery: ${state.northernMostBrewery?.name ?: "Loading..."}")
            Text(text = "Southern Most Brewery: ${state.southernMostBrewery?.name ?: "Loading..."}")
            Text(text = "Longest Name Brewery: ${state.longestNameBrewery?.name ?: "Loading..."}")
            if (state.isLoading) {
                Text(text = "Loading...")
            }
            state.error?.let {
                Text(text = "Error: $it")
            }
        }
    }
}