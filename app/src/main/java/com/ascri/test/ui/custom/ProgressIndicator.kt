package com.ascri.test.ui.custom

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.os.Parcelable
import android.util.AttributeSet
import android.view.View
import android.view.animation.DecelerateInterpolator
import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
import androidx.core.view.drawToBitmap
import com.ascri.test.R
import com.ascri.test.utils.extensions.dpToPx
import com.ascri.test.utils.extensions.spToPx
import kotlinx.android.parcel.IgnoredOnParcel
import kotlinx.android.parcel.Parcelize
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.min


class ProgressIndicator @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    private var indicatorList = listOf<IndicatorModel>()
    private var indicatorDrawableList = listOf<Drawable>()
    private var bgBitmap: Bitmap? = null
    private var selectedIndex = -1
    private var progress = -2
    private val linePaint = Paint()
    private val datePaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val namePaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var dateFontSize = 8f.spToPx(context)
    private var nameFontSize = 12f.spToPx(context)
    private var circleRadius = 1.0f.dpToPx
    private var lineWidth = 4.dpToPx
    private var imageSize = 24.dpToPx
    private var itemVerticalPadding = 12.dpToPx

    @ColorInt
    private var lineColorSelected: Int = Color.CYAN

    @ColorInt
    private var textColor: Int = Color.BLACK

    @ColorInt
    private var lineColorUnSelected: Int = Color.LTGRAY
    private var circleColor: Int = Color.GRAY
    private var barAnimator: ValueAnimator? = null

    init {
        val typedArray =
            getContext().theme.obtainStyledAttributes(attrs, R.styleable.ProgressIndicator, 0, 0)
        try {
            itemVerticalPadding =
                typedArray.getDimensionPixelSize(
                    R.styleable.ProgressIndicator_itemVerticalPadding,
                    itemVerticalPadding
                )
            imageSize = typedArray.getDimensionPixelSize(
                R.styleable.ProgressIndicator_imageSize,
                imageSize
            )
            lineWidth = typedArray.getDimensionPixelSize(
                R.styleable.ProgressIndicator_lineWidth,
                lineWidth
            )
            circleRadius = typedArray.getDimensionPixelSize(
                R.styleable.ProgressIndicator_circleRadius,
                circleRadius
            )
            nameFontSize = typedArray.getDimensionPixelSize(
                R.styleable.ProgressIndicator_nameFontSize,
                nameFontSize
            )
            dateFontSize = typedArray.getDimensionPixelSize(
                R.styleable.ProgressIndicator_dateFontSize,
                dateFontSize
            )
            lineColorSelected = typedArray.getColor(
                R.styleable.ProgressIndicator_lineColorSelected,
                lineColorSelected
            )
            lineColorUnSelected = typedArray.getColor(
                R.styleable.ProgressIndicator_lineColorUnSelected,
                lineColorUnSelected
            )
            circleColor =
                typedArray.getColor(R.styleable.ProgressIndicator_circleColor, circleColor)
            textColor = typedArray.getColor(R.styleable.ProgressIndicator_textColor, textColor)

        } finally {
            typedArray.recycle()
        }

        linePaint.style = Paint.Style.FILL_AND_STROKE
        linePaint.strokeWidth = lineWidth.toFloat()
        linePaint.strokeCap = Paint.Cap.ROUND

        datePaint.textSize = dateFontSize.toFloat()
        datePaint.style = Paint.Style.FILL_AND_STROKE
        datePaint.color = textColor

        namePaint.textSize = nameFontSize.toFloat()
        namePaint.style = Paint.Style.FILL_AND_STROKE
        namePaint.color = textColor
        setUpIndicators(
            listOf(
                IndicatorModel(
                    name = "Step 1",
                    drawableRes = R.drawable.ic_launcher_background
                ),
                IndicatorModel(
                    name = "Step 2",
                    drawableRes = R.drawable.ic_launcher_background
                )
            )
        )
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val width = MeasureSpec.getSize(widthMeasureSpec)
        val heightSize = MeasureSpec.getSize(heightMeasureSpec)
        val minHeight =
            (((circleRadius.toInt() + lineWidth) * 2) + imageSize + nameFontSize + dateFontSize + itemVerticalPadding * 2.5).toInt()
        val height: Int
        height = when (MeasureSpec.getMode(heightMeasureSpec)) {
            MeasureSpec.EXACTLY ->
                heightSize
            MeasureSpec.AT_MOST ->
                min(minHeight, heightSize)
            else ->
                minHeight
        }
        setMeasuredDimension(width, height)
    }

    override fun onDraw(canvas: Canvas) {
        val halfHeight = (height / 2).toFloat()
        val partWidth = ((width - paddingStart - paddingEnd) / (indicatorList.size + 1)).toFloat()

        if (bgBitmap == null) {
            //draw images
            indicatorDrawableList.forEachIndexed { index, drawable ->
                val left = (((index + 1) * partWidth) + paddingStart) - imageSize / 2
                val top = halfHeight - imageSize - itemVerticalPadding
                drawable.setBounds(
                    left.toInt(),
                    top.toInt(),
                    (left + imageSize).toInt(),
                    (top + imageSize).toInt()
                )
                drawable.draw(canvas)
            }
            //draw names and dates
            indicatorList.forEachIndexed { index, item ->
                val nameWidth = namePaint.measureText(item.name)
                val nameY = halfHeight + itemVerticalPadding + nameFontSize
                val dateWidth = datePaint.measureText(item.dateString)
                canvas.drawText(
                    item.name,
                    ((index + 1) * partWidth) + paddingStart - nameWidth / 2,
                    nameY,
                    namePaint
                )
                canvas.drawText(
                    item.dateString,
                    ((index + 1) * partWidth) + paddingStart - dateWidth / 2,
                    nameY + itemVerticalPadding / 2 + dateFontSize,
                    datePaint
                )
            }
        } else {
            canvas.drawBitmap(bgBitmap!!, 0f, 0f, null)
            //draw lines
            linePaint.color = lineColorUnSelected
            canvas.drawLine(
                (progress * partWidth) + paddingStart,
                halfHeight,
                ((indicatorList.size + 1) * partWidth) + paddingStart,
                halfHeight,
                linePaint
            )
            for (index in 0..indicatorList.size) {
                linePaint.color = lineColorSelected
                canvas.drawLine(
                    (progress * partWidth) + paddingStart,
                    halfHeight,
                    ((progress + 1) * partWidth) + paddingStart,
                    halfHeight,
                    linePaint
                )
            }

            //draw dots
            for (index in indicatorList.indices) {
                linePaint.color = circleColor
                canvas.drawCircle(
                    ((index + 1) * partWidth) + paddingStart,
                    halfHeight,
                    circleRadius.toFloat(),
                    linePaint
                )
            }
        }
    }

    override fun onSaveInstanceState(): Parcelable? {
        val bundle = Bundle()
        bundle.putParcelableArrayList(KEY_SELECTED, ArrayList(indicatorList))
        bundle.putParcelable(KEY_SUPER_STATE, super.onSaveInstanceState())
        return bundle
    }

    override fun onRestoreInstanceState(state: Parcelable?) {
        var localState = state
        if (localState is Bundle) {
            val bundle = localState
            bundle.getParcelableArrayList<IndicatorModel>(KEY_SELECTED)?.let {
                indicatorList = it
            }
            localState = bundle.getParcelable(KEY_SUPER_STATE)
        }
        super.onRestoreInstanceState(localState)
    }

    private fun setProgress(index: Int, animate: Boolean, progress: Int = 0) {
        if (animate) {
            barAnimator = ValueAnimator.ofFloat(0f, indicatorList.size.toFloat())
            barAnimator?.duration = 700
            // reset progress without animating
            setProgress(index, false, 0)
            barAnimator?.interpolator = DecelerateInterpolator()
            barAnimator?.addUpdateListener { animation ->
                val interpolation = animation.animatedValue as Float
                setProgress(index, false, (interpolation * progress).toInt())
            }
            if (barAnimator?.isStarted != true) {
                barAnimator?.start()
            }
        } else {
            selectedIndex = index
            this.progress = if (progress != 0) progress else index
            postInvalidate()
        }
    }

    fun deselectAll() {
        indicatorList.map { it.isSelected = false }
        selectedIndex = -1
    }

    fun setUpIndicators(list: List<IndicatorModel>) {
        indicatorList = list
        indicatorDrawableList =
            list.map { resources.getDrawable(it.drawableRes, context.theme).mutate() }
    }

    fun setSelected(selectedName: String, animate: Boolean = false) {
        indicatorList.mapIndexed { index, indicatorModel ->
            if (indicatorModel.name == selectedName) {
                deselectAll()
                indicatorModel.isSelected = true
                setProgress(index, animate)
            }
        }
    }

    fun complete(animate: Boolean = false) {
        deselectAll()
        setProgress(indicatorList.size, animate)
    }

    fun selectNext(animate: Boolean = true) {
        val tempIndex = selectedIndex + 1
        indicatorList.getOrNull(tempIndex)?.let {
            deselectAll()
            it.isSelected = true
            setProgress(tempIndex, animate)
        } ?: kotlin.run {
            if (tempIndex == indicatorList.size) {
                deselectAll()
                setProgress(tempIndex, animate)
            }
        }
    }

    fun selectBefore(animate: Boolean = true) {
        val tempIndex = selectedIndex - 1
        indicatorList.getOrNull(tempIndex)?.let {
            deselectAll()
            it.isSelected = true
            setProgress(tempIndex, animate)
        } ?: kotlin.run {
            if (tempIndex == -1) {
                deselectAll()
                setProgress(tempIndex, animate)
            }
        }
    }

    fun getSelected(): IndicatorModel? {
        return indicatorList.find { it.isSelected }
    }

    @Parcelize
    data class IndicatorModel(
        val name: String = "",
        @DrawableRes
        val drawableRes: Int = 0,
        val date: Date = Date(),
        var isSelected: Boolean = false
    ) : Parcelable {
        @IgnoredOnParcel
        val dateString: String by lazy {
            SimpleDateFormat("dd.M.yyyy", Locale.getDefault()).format(
                date
            )
        }
    }

    companion object {
        const val KEY_SELECTED = "selected"
        const val KEY_SUPER_STATE = "superState"
    }
}