package com.example.animationstransitions

import android.animation.ObjectAnimator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.*
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_property_anim.*

class PropertyAnimActivity : AppCompatActivity() {

    var isClosed : Boolean = true // true - curtains are closed, false - curtains are open
    var durationSet : Long = 2000
    var interpolationType : Interpolator = AccelerateDecelerateInterpolator()
    var interpolationTypeTxt = "acceldecel"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_property_anim)

        val runBtn : Button = findViewById(R.id.runAnimBtn)
        val initBtnAnim = ObjectAnimator.ofFloat(runBtn, "translationY", 200f, -50f, 0f).apply {
            duration = 1000
            interpolator = AccelerateDecelerateInterpolator()
        }
//        initBtnAnim.start()

        val animFadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in)
        runBtn.startAnimation(animFadeIn)
    }

    fun backBtnClicked(view: View) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    fun runAnimBtnClicked(view: View) {
        val leftCurtain : ImageView = findViewById(R.id.curtainLeft)
        val rightCurtain : ImageView = findViewById(R.id.curtainRight)
        val runAnimBtn : Button = findViewById(R.id.runAnimBtn)

        var leftCurtainStart = 0f
        var leftCurtainEnd = 0f
        var rightCurtainStart = 0f
        var rightCurtainEnd = 0f

        jumpUp(runAnimBtn)

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
            duration = durationSet
            interpolator = interpolationType
        }
        val rightCurtainAnimator = ObjectAnimator.ofFloat(rightCurtain, "translationX", rightCurtainStart, rightCurtainEnd ).apply {
            duration = durationSet
            interpolator = interpolationType
        }

        leftCurtainAnimator.start()
        rightCurtainAnimator.start()
    }

    private fun jumpUp(button: Button) {
        val jumpUp = ObjectAnimator.ofFloat(button, "translationY", 0f, -50f, 0f).apply {
            duration = 1500
            interpolator = BounceInterpolator()
        }
        jumpUp.start()
    }

    fun plusBtnClicked(view:View) {
        val secondsText : TextView = findViewById(R.id.secondsTxt)
        durationSet += 500
        if (durationSet.toInt() != 0) {
            minusBtn.isClickable = true
            minusBtn.alpha = 1f
        }
        secondsText.text = ("%.1f".format((durationSet.toFloat() / 1000))) + " seconds"
    }

    fun minusBtnClicked(view:View) {
        val secondsText : TextView = findViewById(R.id.secondsTxt)
        if (durationSet.toInt() != 0) {durationSet -= 500}
        if (durationSet.toInt() == 0) {
            minusBtn.isClickable = false
            minusBtn.alpha = 0.3f
        }
        secondsText.text = ("%.1f".format((durationSet.toFloat() / 1000))) + " seconds"
    }

    fun changeInterpolationBtnClicked(view: View) {
        val interpolationTxt : TextView = findViewById(R.id.interpolationType)

        if (interpolationTypeTxt == "acceldecel") {
            interpolationType = AccelerateInterpolator()
            interpolationTxt.text = "Accelerate"
            interpolationTypeTxt = "accel"
        }


        when (interpolationTypeTxt) {
            "acceldecel" -> {
                interpolationType = AccelerateInterpolator()
                interpolationTxt.text = "Accelerate"
                interpolationTypeTxt = "accel"
            }
            "accel" -> {
                interpolationType = AnticipateInterpolator()
                interpolationTxt.text = "Anticipate"
                 interpolationTypeTxt = "antic"
            }
            "antic" -> {
                interpolationType = AnticipateOvershootInterpolator()
                interpolationTxt.text = "Anticipate Overshoot"
                 interpolationTypeTxt = "anticiover"
            }
            "anticiover" -> {
                interpolationType = BounceInterpolator()
                interpolationTxt.text = "Bounce"
                 interpolationTypeTxt = "bounce"
            }
            "bounce" -> {
                interpolationType = AccelerateDecelerateInterpolator()
                interpolationTxt.text = "Accelerate Decelerate"
                 interpolationTypeTxt = "acceldecel"
            }
        }

    }
}
