package com.androidapp.myportfolioappandroid.core.util

import android.content.Context
import android.graphics.Bitmap
import java.io.File
import java.io.FileOutputStream

fun saveBitmapToCacheAsPng(
    context: Context,
    bitmap: Bitmap,
    fileName: String = "image_${System.currentTimeMillis()}.png"
) {
    val file = File(
        context.cacheDir,
        fileName
    )

    FileOutputStream(file).use { outputStream ->
        bitmap.compress(
            Bitmap.CompressFormat.PNG,
            100,
            outputStream
        )
    }
}