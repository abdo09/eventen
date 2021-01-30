package net.tinat.group.eventen.utils

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import androidx.core.content.ContextCompat

@SuppressLint("ObsoleteSdkInt")
fun Context.getCustomColor(context: Context, color: Int): Int {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        resources.getColor(color, theme)
    } else {
        ContextCompat.getColor(context, color)
    }
}

@SuppressLint("ObsoleteSdkInt")
fun Context.getCustomColor(color: Int): Int {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        resources.getColor(color, theme)
    } else {
        ContextCompat.getColor(this, color)
    }
}

fun String.digitsOnly(): String {
    val regex = Regex("[^0-9]")
    return regex.replace(this, "")
}

fun isOdd(value: Int): Boolean {
    return value and 0x01 != 0
}

fun getDoubleValue(value: Double): String {
    @SuppressLint("DefaultLocale")
    var enAmount = String.format("%.2f", value)
    enAmount =
        enAmount.replace(",".toRegex(), ".").replace("٫".toRegex(), ".").replace("٠".toRegex(), "0")
            .replace("١".toRegex(), "1").replace("٢".toRegex(), "2").replace("٣".toRegex(), "3")
            .replace("٤".toRegex(), "4")
            .replace("٥".toRegex(), "5").replace("٦".toRegex(), "6").replace("٧".toRegex(), "7")
            .replace("٨".toRegex(), "8").replace("٩".toRegex(), "9")
    return enAmount
}

fun getFloatValue(value: Float): String {
    @SuppressLint("DefaultLocale")
    var enAmount = String.format("%.2f", value)
    enAmount =
        enAmount.replace(",".toRegex(), ".").replace("٫".toRegex(), ".").replace("٠".toRegex(), "0")
            .replace("١".toRegex(), "1").replace("٢".toRegex(), "2").replace("٣".toRegex(), "3")
            .replace("٤".toRegex(), "4")
            .replace("٥".toRegex(), "5").replace("٦".toRegex(), "6").replace("٧".toRegex(), "7")
            .replace("٨".toRegex(), "8").replace("٩".toRegex(), "9")
    return enAmount
}

fun String.replaceAll(): String{
    return  this.replace(",".toRegex(), ".").replace("٫".toRegex(), ".").replace("٠".toRegex(), "0")
        .replace("١".toRegex(), "1").replace("٢".toRegex(), "2").replace("٣".toRegex(), "3")
        .replace("٤".toRegex(), "4")
        .replace("٥".toRegex(), "5").replace("٦".toRegex(), "6").replace("٧".toRegex(), "7")
        .replace("٨".toRegex(), "8").replace("٩".toRegex(), "9")
}