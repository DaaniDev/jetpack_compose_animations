package com.daanidev.compose.ui.webview

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCompositionContext
import androidx.compose.ui.viewinterop.AndroidView

const val webUrl="https://www.github.com/Daanidev"
class WebviewScreen : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent{
          urlIntent(url = webUrl)
        }
    }


    @Composable
    fun loadWebUrl(url: String) {

        AndroidView(factory = {
            WebView(this).apply {
                webViewClient = WebViewClient()

                loadUrl(url)
            }
        })
    }

    @Composable
    fun urlIntent(url:String){

        val webIntent: Intent = Uri.parse(url).let { webpage ->
            Intent(Intent.ACTION_VIEW, webpage)
        }
        startActivity(webIntent)
    }
}