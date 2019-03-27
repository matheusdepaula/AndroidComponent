package iesb.com.customcomponents.components

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import iesb.com.customcomponents.R

class OnBoarding @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private var pageIndicatorRadius: Float = 0f
    private var pageIndicatorStrokeWidth: Float = 0f
    private var pageIndicatorStrokeColor: Int = 0
    private var pageIndicatorColor: Int = 0
    private var pageIndicatorSelectedColor: Int = 0

    //Backgrounds Colors
    private var pageBackgroundColor: Int = 0

    private val paint = Paint()
    private val backgroundRect = Rect(0, 0, 0, 0)

    init {
        //Gera o ID do componente dinamicamente, igual fazemos no XML -> android:id="@+id/"
        id = generateViewId()
        initObjects(attrs)
    }

    override fun onDraw(canvas: Canvas) {
        //Draw the background
        drawBackground(canvas)
        drawPageIndicator(canvas)

    }

    private fun initObjects(attrs: AttributeSet?) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.OnBoarding)

        pageIndicatorRadius = typedArray.getFloat(R.styleable.OnBoarding_pageIndicatorRadius, 30f)
        pageIndicatorStrokeWidth = typedArray.getFloat(R.styleable.OnBoarding_pageIndicatorStrokeWidth, 10f)
        pageIndicatorStrokeColor = typedArray.getColor(R.styleable.OnBoarding_pageIndicatorStrokeColor, Color.BLACK)
        pageIndicatorSelectedColor = typedArray.getColor(R.styleable.OnBoarding_pageIndicatorSelectedColor, Color.GRAY)
        pageIndicatorColor = typedArray.getColor(R.styleable.OnBoarding_pageIndicatorSelectedColor, Color.DKGRAY)
        pageBackgroundColor = typedArray.getColor(R.styleable.OnBoarding_pageBackgroundColor, Color.LTGRAY)

        typedArray.recycle()
    }

    private fun drawBackground(canvas: Canvas) {
        paint.color = pageBackgroundColor
        paint.style = Paint.Style.FILL
        backgroundRect.set(0, 0, measuredWidth, measuredHeight)
        canvas.drawRect(backgroundRect, paint)
    }

    private fun drawPageIndicator(canvas: Canvas) {
        val bottoPosition = measuredHeight - pageIndicatorRadius - dpToPx(5f)
        val leftPosition = measuredWidth / 2f - pageIndicatorRadius / 2f

        paint.color = pageIndicatorStrokeColor
        canvas.drawCircle(leftPosition, bottoPosition, pageIndicatorRadius + pageIndicatorStrokeWidth, paint)

        paint.color = pageIndicatorColor
        canvas.drawCircle(leftPosition, bottoPosition, pageIndicatorRadius, paint)
    }

    private fun dpToPx(value: Float) =
        TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value, resources.displayMetrics)
}