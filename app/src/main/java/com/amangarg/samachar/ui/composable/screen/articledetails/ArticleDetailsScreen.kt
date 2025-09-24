package com.amangarg.samachar.ui.composable.screen.articledetails

import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.amangarg.samachar.ui.theme.VintageBackground

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArticleDetailsScreen(
    articleUrl: String,
    onBackClick: () -> Unit
) {
    var isLoading by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(VintageBackground)
            .statusBarsPadding()
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .background(VintageBackground)
        ) {
            AndroidView(
                factory = { context ->
                    WebView(context).apply {
                        setBackgroundColor(0x00000000)
                        webViewClient = object : WebViewClient() {
                            override fun onPageFinished(view: WebView?, url: String?) {
                                super.onPageFinished(view, url)
                                isLoading = false
                            }
                        }
                        settings.apply {
                            javaScriptEnabled = true
                            domStorageEnabled = true
                            loadWithOverviewMode = true
                            useWideViewPort = true
                            builtInZoomControls = true
                            displayZoomControls = false
                        }
                        loadUrl(articleUrl)
                    }
                },
                modifier = Modifier.fillMaxSize()
            )

            if (isLoading) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        CircularProgressIndicator(
                            color = Color.Red,
                            strokeWidth = 3.dp
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = "Loading article...",
                            color = Color.Gray,
                            fontSize = 14.sp
                        )
                    }
                }
            }
        }
    }
}