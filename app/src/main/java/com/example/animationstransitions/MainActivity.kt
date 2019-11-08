package com.example.animationstransitions

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun propertyAnimBtnClicked(view: View) {
        val intent = Intent(this, PropertyAnimActivity::class.java)
        startActivity(intent)
    }

    fun viewAnimBtnClicked(view: View) {
        val intent = Intent(this, ViewAnimActivity::class.java)
        startActivity(intent)
    }

    fun drawableAnimBtnClicked(view: View) {
        val intent = Intent(this, DrawableAnimActivity::class.java)
        startActivity(intent)
    }

}
