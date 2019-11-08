package com.example.animationstransitions

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class DrawableAnimActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drawable_anim)
    }

    fun backBtnClicked(view: View) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}
