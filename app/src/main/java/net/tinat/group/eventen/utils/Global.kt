package net.tinat.group.eventen.utils

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import androidx.core.content.ContextCompat
import net.tinat.group.eventen.data.dto.Event

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

fun String.replaceAll(): String {
    return if (this.length == 1) this.replace("0".toRegex(), "00")
        .replace("1".toRegex(), "01").replace("2".toRegex(), "02").replace("3".toRegex(), "03")
        .replace("4".toRegex(), "04")
        .replace("5".toRegex(), "05").replace("6".toRegex(), "06").replace("7".toRegex(), "07")
        .replace("8".toRegex(), "08").replace("9".toRegex(), "09")
    else this
}

fun formatTime(eventTime: Event.Time?): String {
    if (eventTime?.startingTime?.hours?: 0 < 12) eventTime?.startingTime?.hoursFormat =
        Event.HoursFormat.AM
    else eventTime?.startingTime?.hoursFormat = Event.HoursFormat.PM

    if (eventTime?.endingTime?.hours?: 0 < 12) eventTime?.endingTime?.hoursFormat = Event.HoursFormat.AM
    else eventTime?.endingTime?.hoursFormat = Event.HoursFormat.PM

    val startingTime = eventTime?.startingTime
    val endingTime = eventTime?.endingTime

    if (startingTime?.hours?: 0 >= 13) startingTime?.hours = startingTime?.hours?.minus(12)
    if (endingTime?.hours?: 0 >= 13) endingTime?.hours = endingTime?.hours?.minus(12)

    if (startingTime?.hoursFormat == Event.HoursFormat.AM && startingTime.hours == 0) startingTime.hours =
        12
    if (endingTime?.hoursFormat == Event.HoursFormat.AM && endingTime.hours == 0) startingTime?.hours =
        12

    return "${startingTime?.hours.toString().replaceAll()}:${
        startingTime?.minutes.toString().replaceAll()
    } ${startingTime?.hoursFormat?.name} - ${
        endingTime?.hours.toString().replaceAll()
    }:${endingTime?.minutes.toString().replaceAll()} ${endingTime?.hoursFormat?.name}"
}