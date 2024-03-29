@file:Suppress("RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")

package net.tinat.group.eventen.utils

import android.animation.*
import android.app.Activity
import android.content.Context
import android.content.res.ColorStateList
import android.content.res.Resources
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.os.Build
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.EditText
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.textfield.TextInputLayout
import net.tinat.group.eventen.R
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/*val Int.dp: Int get() = (this * Resources.getSystem().displayMetrics.density + 0.5f).toInt()

val Float.dp: Int get() = (this * Resources.getSystem().displayMetrics.density + 0.5f).toInt()

fun roundOffDecimal(number: Double): Double {
    val df = DecimalFormat("#.##")
    df.roundingMode = RoundingMode.CEILING
    return df.format(number).toDouble()
}*/

fun Activity.navigationBarAndStatusBarColor(
    @ColorRes statusColor: Int,
    @ColorRes navigationColor: Int,
    lightFlag: Boolean = false
) {
    val window: Window = this.window
    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
    window.statusBarColor = ContextCompat.getColor(this, statusColor)
    window.navigationBarColor = ContextCompat.getColor(this, navigationColor)
    val wic = WindowInsetsControllerCompat(window, window.decorView)
    wic.isAppearanceLightStatusBars = lightFlag
    wic.isAppearanceLightNavigationBars = lightFlag
}

private fun getSimpleDateFormat(): SimpleDateFormat {
    val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.ENGLISH)
    sdf.timeZone = TimeZone.getTimeZone("GMT")
    return sdf
}

/**
 * Formats timestamp to 'date month' format (e.g. 'February 3').
 */
fun String.formatDate(timeInMillis: String?): String? {
    val dateFormat = getSimpleDateFormat()
    var date = timeInMillis
    try {
        date = formatDate(dateFormat.parse(timeInMillis ?: "").time)
    } catch (e: ParseException) {
        e.printStackTrace()
    }
    return date
}

/**
 * Formats timestamp to 'date month' format (e.g. 'February 3').
 */

fun formatDate(timeInMillis: Long): String? {
    val dateFormat = SimpleDateFormat("MMMM dd", Locale.ENGLISH)
    return dateFormat.format(timeInMillis)
}


/**
 * Formats timestamp to 'date month' format (e.g. 'February 3').
 */
fun formatDate(timeInMillis: Long, fullDate: Boolean): String {
    val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
    return dateFormat.format(timeInMillis)
}

fun View.fadeIn(duration: Long = 600) {
    if (visibility != View.VISIBLE)
        this.apply {
            alpha = 0f
            visibility = View.VISIBLE
            post {
                animate().alpha(1f).setDuration(duration).start()
            }
        }

}

fun View.fadeOut(duration: Long = 800) {
    if (visibility != View.GONE)
        this.apply {
            alpha = 1f
            post {
                animate().alpha(0f).setDuration(duration)
                    .withEndAction {
                        visibility = View.GONE
                    }.start()
            }
        }
}

fun View.fadeIn(duration: Long = 600, visible: Int) {
    if (visibility != visible)
        this.apply {
            alpha = 0f
            visibility = visible
            post {
                animate().alpha(1f).setDuration(duration).start()
            }
        }

}

fun View.fadeOut(duration: Long = 800, visible: Int) {
    if (visibility != visible)
        this.apply {
            alpha = 1f
            post {
                animate().alpha(0f).setDuration(duration)
                    .withEndAction {
                        visibility = visible
                    }.start()
            }
        }
}

fun Context.loadWithGlide(
    into: ImageView?,
    url: Any?,
    fitImage: Boolean = false,
    roundImage: Boolean = false,
    listener: RequestListener<Drawable>? = null
) {
    if (url == null)
        return
    val circularProgressDrawable = CircularProgressDrawable(this)
    circularProgressDrawable.strokeWidth = 10f
    circularProgressDrawable.centerRadius = 100f
    circularProgressDrawable.setStyle(CircularProgressDrawable.DEFAULT)
    circularProgressDrawable.start()

    val options = RequestOptions()
//            .optionalFitCenter()
        .placeholder(circularProgressDrawable.apply {
            this.backgroundColor = android.R.color.black

        })
        .skipMemoryCache(false)
        .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
        .error(circularProgressDrawable.apply {
            this.backgroundColor = android.R.color.holo_red_dark
        })
        .priority(Priority.HIGH)


    if (fitImage)
        options.fitCenter()
    if (roundImage)
        options.transform(RoundedCorners(16))



    try {
        var imgURI = "" //RemoteConfigs.BASE_URL

        val glide = Glide.with(this).asDrawable()

        if (url is String) {
            when {
                url.startsWith("/data") -> imgURI = url
                url.startsWith("/") -> imgURI = url.replaceFirst("/", "https://")
                url.startsWith("http:") -> imgURI = url.replace("http", "https")
                url.startsWith("https") -> imgURI = url
                else -> imgURI += url
            }

            if (into != null) {
                glide.load(imgURI).apply(options)
                    .listener(listener)
                    .into(into)
            }
        } else {
            if (into != null) {
                glide.load(url).apply(options)
                    .listener(listener)
                    .into(into)
            }

        }

    } catch (ex: Exception) {
        ex.printStackTrace()
    }

}

fun ViewPager2.setCurrentItemX(
    item: Int,
    duration: Long,
    interpolator: TimeInterpolator = AccelerateDecelerateInterpolator(),
    pagePxWidth: Int = width // Default value taken from getWidth() from ViewPager2 view
) {
    val pxToDrag: Int = pagePxWidth * (item - currentItem)
    val animator = ValueAnimator.ofInt(0, pxToDrag)
    var previousValue = 0
    animator.addUpdateListener { valueAnimator ->
        val currentValue = valueAnimator.animatedValue as Int
        val currentPxToDrag = (currentValue - previousValue).toFloat()
        fakeDragBy(currentPxToDrag)
        previousValue = currentValue
    }
    animator.addListener(object : Animator.AnimatorListener {
        override fun onAnimationStart(animation: Animator?) {
            try {
                beginFakeDrag()
            } catch (ex: java.lang.Exception) {
                ex.printStackTrace()
            }
        }

        override fun onAnimationEnd(animation: Animator?) {
            try {
                endFakeDrag()
            } catch (ex: java.lang.Exception) {
                ex.printStackTrace()
            }
        }

        override fun onAnimationCancel(animation: Animator?) { /* Ignored */
        }

        override fun onAnimationRepeat(animation: Animator?) { /* Ignored */
        }
    })
    animator.interpolator = interpolator
    animator.duration = duration
    animator.start()
}

/*fun vatString(value: Double): String {
    val totalAmountWithoutVAT = value / 1.05
    val vatValue = value - totalAmountWithoutVAT
    return String.format("%.2f", vatValue)
}*/

fun TextView.toColor(@ColorRes colorResId: Int) {
    ObjectAnimator.ofObject(
        this,  // Object to animating
        "textColor",  // Property to animate
        ArgbEvaluator(),  // Interpolation function
        this.currentTextColor,  // Start color
        colorResId // End color
    ).setDuration(600) // Duration in milliseconds
        .start() //

}

/*fun TextView.colorize(subStringToColorize: String, @ColorRes colorResId: Int) {

    val spannable: Spannable = SpannableString(text)

    val startIndex = text.indexOf(subStringToColorize, startIndex = 0, ignoreCase = false)
    val endIndex = startIndex + subStringToColorize.length

    val color: Int = ContextCompat.getColor(context, colorResId)

    if (startIndex != -1) {
        spannable.setSpan(
            ForegroundColorSpan(color),
            startIndex,
            endIndex,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        setText(spannable, TextView.BufferType.SPANNABLE)
    }
}*/

fun TextInputLayout.setRedBoarder(field: Int) {
    this.background = ResourcesCompat.getDrawable(resources, R.drawable.boarder_red_color, null)
    this.hint = "${resources.getString(R.string.please_enter)} ${resources.getString(field)}"
    setTextInputLayoutHintColor(this, context, R.color.red_500)
}

fun EditText.setGrayBoarder(field: Int, textInputLayout: TextInputLayout) {
    this.addTextChangedListener(object : TextWatcher {

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            textInputLayout.background = ResourcesCompat.getDrawable(
                resources,
                R.drawable.boarder_gray_color,
                null
            )
            textInputLayout.hint = resources.getString(field)
            setTextInputLayoutHintColor(textInputLayout, context, R.color.grayColor)
        }

        override fun afterTextChanged(s: Editable?) {}

    })
}

private fun setTextInputLayoutHintColor(
    textInputLayout: TextInputLayout,
    context: Context,
    @ColorRes colorIdRes: Int
) {
    textInputLayout.defaultHintTextColor = ColorStateList.valueOf(
        ContextCompat.getColor(
            context,
            colorIdRes
        )
    )
}

val Int.dpToPx: Int
    get() = (this * Resources.getSystem().displayMetrics.density).toInt()

fun drawable(backgroundColor: String, topLeftCorner: Int = 0, topRightCorner: Int = 0, bottomLeftCorner: Int = 0, bottomRightCorner: Int = 0): GradientDrawable {
    val drawables = GradientDrawable()
    val topLeft = topLeftCorner.dpToPx.toFloat()
    val topRight = topRightCorner.dpToPx.toFloat()
    val bottomLeft = bottomLeftCorner.dpToPx.toFloat()
    val bottomRight = bottomRightCorner.dpToPx.toFloat()

    drawables.setColor(
        Color.parseColor(
            backgroundColor
        )
    )

    drawables.cornerRadii = floatArrayOf(
        // top left
        topLeft,
        topLeft,
        // top right
        topRight,
        topRight,
        // bottom right
        bottomRight,
        bottomRight,
        // bottom left
        bottomLeft,
        bottomLeft
    )
    return drawables
}