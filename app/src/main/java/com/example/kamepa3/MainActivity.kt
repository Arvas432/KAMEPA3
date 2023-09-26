package com.example.kamepa3

import android.content.pm.PackageManager
import android.hardware.Camera
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.SurfaceHolder
import android.view.SurfaceView

class MainActivity : AppCompatActivity(), SurfaceHolder.Callback  {
    private lateinit var camera: Camera
    private lateinit var surfaceHolder: SurfaceHolder
    private lateinit var surfaceView: SurfaceView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

            // Открываем камеру
            camera = Camera.open(2)
            surfaceView = findViewById(R.id.surfaceView)
            surfaceHolder = surfaceView.holder
            surfaceHolder.addCallback(this)

    }

    override fun surfaceCreated(holder: SurfaceHolder) {
        try {
            // Подключаем предпросмотр к SurfaceView
            camera.setPreviewDisplay(holder)
            camera.startPreview()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {

    }

    override fun surfaceDestroyed(p0: SurfaceHolder) {
        // Освобождаем ресурсы камеры
        camera.stopPreview()
        camera.release()
    }
    private fun checkCameraPermission(): Boolean {
        return if
                       (checkSelfPermission(android.Manifest.permission.CAMERA) ==
            PackageManager.PERMISSION_GRANTED) {
            true } else {
            requestPermissions(arrayOf(android.Manifest.permission.CAMERA), 1)
            false
        } }
}