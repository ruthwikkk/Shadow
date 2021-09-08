package com.ruthwikkk.shadow

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var cornerRadius = 32
    var shadowRadius = 4
    var shadowX = 0
    var shadowY = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setShadow()
        tv_radius_value.text = "$cornerRadius dp"
        seekBar.progress = cornerRadius

        seekBar.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                tv_radius_value.text = "$progress dp"
                cornerRadius = progress
                setShadow()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {

            }
            override fun onStopTrackingTouch(seekBar: SeekBar) {

            }
        })

        tv_shadow_radius_value.text = "$shadowRadius dp"
        seekBar_shadow.progress = shadowRadius

        seekBar_shadow.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                tv_shadow_radius_value.text = "$progress dp"
                shadowRadius = progress
                setShadow()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {

            }
            override fun onStopTrackingTouch(seekBar: SeekBar) {

            }
        })

        tv_shadow_x.text = "$shadowX dp"
        seekBar_shadow_x.progress = shadowX

        seekBar_shadow_x.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                tv_shadow_x.text = "$progress dp"
                shadowX = progress
                setShadow()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {

            }
            override fun onStopTrackingTouch(seekBar: SeekBar) {

            }
        })

        tv_shadow_y.text = "$shadowY dp"
        seekBar_shadow_y.progress = shadowY

        seekBar_shadow_y.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                tv_shadow_y.text = "$progress dp"
                shadowY = progress
                setShadow()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {

            }
            override fun onStopTrackingTouch(seekBar: SeekBar) {

            }
        })

    }

    fun setShadow(){
        ll_parent.setShadowV2(R.color.bg_like_b2, cornerRadius, shadowRadius, shadowX, shadowY, R.dimen.elevation, ShadowUtils.RoundedEdge.TOP_LEFT, ShadowUtils.ShadowGravity.ALL)
    }
}