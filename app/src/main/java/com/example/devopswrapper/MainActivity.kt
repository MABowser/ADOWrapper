package com.example.devopswrapper

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import com.example.devopswrapper.R.layout.activity_main
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        val sharedPref = this?.getPreferences(Context.MODE_PRIVATE) ?: return
        val userName = sharedPref.getString(getString(R.string.user_name_preference), "")
        if(userName == ""){
            setContentView(R.layout.activity_intro)
            val btnSubmitUserName = this.findViewById<Button>(R.id.set_username)
            btnSubmitUserName.setOnClickListener {
                val userNameText = findViewById<TextInputEditText>(R.id.username).text.toString()
                if(userNameText == ""){
                    return@setOnClickListener
                }
                else{
                    val prefEdit = sharedPref.edit()
                    prefEdit.putString(getString(R.string.user_name_preference), userNameText)
                    prefEdit.apply()
                    showADOWebView()
                }
            }
        }
        else{
            showADOWebView()
        }
    }

   private fun showADOWebView(){
       val sharedPref = this?.getPreferences(Context.MODE_PRIVATE) ?: return
       val userName = sharedPref.getString(getString(R.string.user_name_preference), "")
       setContentView(activity_main)
       val myWebView: WebView = findViewById(R.id.webview)
       myWebView.loadUrl("https://dev.azure.com/" + userName)
       myWebView.settings.javaScriptEnabled = true
       myWebView.webViewClient = WebViewClient()
   }
}