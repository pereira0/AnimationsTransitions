package com.example.animationstransitions

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.AccelerateInterpolator
import android.widget.Button
import android.widget.ImageView
import kotlinx.android.synthetic.main.activity_property_anim.*

class PropertyAnimActivity : AppCompatActivity() {

    var isClosed : Boolean = true // true - curtains are closed, false - curtains are open

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_property_anim)

        val runBtn = findViewById<Button>(R.id.runAnimBtn)
        val initBtnAnim = ObjectAnimator.ofFloat(runBtn, "translationY", 200f, -50f, 0f).apply {
            duration = 1000
            interpolator = AccelerateDecelerateInterpolator()
        }
        initBtnAnim.start()

    }

    fun backBtnClicked(view: View) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    fun runAnimBtnClicked(view: View) {
        val leftCurtain : ImageView = findViewById(R.id.curtainLeft)
        val rightCurtain : ImageView = findViewById(R.id.curtainRight)

        var leftCurtainStart = 0f
        var leftCurtainEnd = 0f
        var rightCurtainStart = 0f
        var rightCurtainEnd = 0f

        if(isClosed) {
            leftCurtainStart = 0f
            leftCurtainEnd = -500f
            rightCurtainStart = 0f
            rightCurtainEnd = 500f
            isClosed = false

        } else if (!isClosed) {
            leftCurtainStart = -500f
            leftCurtainEnd = 0f
            rightCurtainStart = 500f
            rightCurtainEnd = 0f
            isClosed = true
        }


        val leftCurtainAnimator = ObjectAnimator.ofFloat(leftCurtain, "translationX", leftCurtainStart, leftCurtainEnd).apply {
            duration = 2000
            interpolator = AccelerateDecelerateInterpolator()
        }
        val rightCurtainAnimator = ObjectAnimator.ofFloat(rightCurtain, "translationX", rightCurtainStart, rightCurtainEnd ).apply {
            duration = 2000
            interpolator = AccelerateDecelerateInterpolator()
        }

        leftCurtainAnimator.start()
        rightCurtainAnimator.start()
    }
}
