package it.gcriscione.fabottombar


import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Paint
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import androidx.core.animation.doOnEnd
import androidx.core.content.ContextCompat
import com.google.android.material.bottomappbar.BottomAppBarTopEdgeTreatment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.shape.CornerFamily
import com.google.android.material.shape.MaterialShapeDrawable
import com.google.android.material.shape.ShapeAppearanceModel

@SuppressLint("RestrictedApi")
class FABottomBar @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : BottomNavigationView(context, attrs, defStyleAttr) {

    private var topCurvedEdgeTreatment: BottomAppBarTopEdgeTreatment
    private var materialShapeDrawable: MaterialShapeDrawable
    private var fabSize = 0F
    var fabCradleMargin = 0F
    var fabCradleRoundedCornerRadius = 0F
    var cradleVerticalOffset = 0F

    var animDuration = 200L
    var cornerSize = 30f

    private var shapeAppearanceModel : ShapeAppearanceModel


    private var itemId: Int = 0
    private lateinit var itemIcon: Drawable
    private lateinit var itemTitle: String

    init {
        val ta = context.theme.obtainStyledAttributes(attrs, R.styleable.FABBottomBar, 0, 0)
        fabSize = ta.getDimension(R.styleable.FABBottomBar_fab_size, 0F)
        fabCradleMargin = ta.getDimension(R.styleable.FABBottomBar_fab_cradle_margin, 0F)
        fabCradleRoundedCornerRadius =
            ta.getDimension(R.styleable.FABBottomBar_fab_cradle_rounded_corner_radius, 0F)
        cradleVerticalOffset = ta.getDimension(R.styleable.FABBottomBar_cradle_vertical_offset, 0F)

        topCurvedEdgeTreatment = BottomAppBarTopEdgeTreatment(fabCradleMargin, fabCradleRoundedCornerRadius, cradleVerticalOffset).apply {
            fabDiameter = fabSize
        }

        shapeAppearanceModel = ShapeAppearanceModel.Builder()
            .setTopEdge(topCurvedEdgeTreatment)
            .setTopLeftCorner(CornerFamily.ROUNDED, cornerSize)
            .setTopRightCorner(CornerFamily.ROUNDED, cornerSize)
            .build()

        materialShapeDrawable = MaterialShapeDrawable(shapeAppearanceModel).apply {
            setTint(ContextCompat.getColor(context, ta.getResourceId(R.styleable.FABBottomBar_background_color, android.R.color.white)))
            paintStyle = Paint.Style.FILL_AND_STROKE

        }

        background = materialShapeDrawable
    }

    fun showFAB(fab: FloatingActionButton): Boolean {
        ValueAnimator.ofFloat(materialShapeDrawable.interpolation, 1F).apply {
            duration = animDuration
            addUpdateListener { materialShapeDrawable.interpolation = it.animatedValue as Float }
            doOnEnd { fab.show() }
            start()
            hideItem()
        }
        return true
    }

    fun hideFAB(fab: FloatingActionButton): Boolean {
        fab.hide(object : FloatingActionButton.OnVisibilityChangedListener() {
            override fun onHidden(fab: FloatingActionButton?) {
                super.onHidden(fab)
                ValueAnimator.ofFloat(materialShapeDrawable.interpolation, 0F).apply {
                    duration = animDuration
                    addUpdateListener { materialShapeDrawable.interpolation = it.animatedValue as Float }
                    start()
                    showItem()
                }
            }
        })
        return true
    }

    fun setFabItem(res: Int){
        itemId = res
        itemIcon = menu.findItem(res).icon
        itemTitle = menu.findItem(res).title.toString()
    }


    fun showItem() {
        menu.findItem(itemId).icon = itemIcon
        menu.findItem(itemId).title = itemTitle
    }


    fun hideItem() {
        menu.findItem(itemId).icon = context!!.getDrawable(android.R.color.transparent)
        menu.findItem(itemId).title = ""
    }



}