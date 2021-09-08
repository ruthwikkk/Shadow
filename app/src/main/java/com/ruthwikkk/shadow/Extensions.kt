package com.ruthwikkk.shadow

import android.content.Context
import android.graphics.drawable.LayerDrawable
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.RoundRectShape
import android.os.Build
import android.util.Log
import android.view.View
import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import androidx.core.content.ContextCompat

fun View.setShadowV2(
    @ColorRes shadowColor: Int,
    cornerRadius: Int,
    shadowRadius: Int,
    shadowX: Int,
    shadowY: Int,
    @DimenRes elevation: Int,
    roundEdgeDirection: ShadowUtils.RoundedEdge = ShadowUtils.RoundedEdge.ALL,
    shadowGravity: ShadowUtils.ShadowGravity = ShadowUtils.ShadowGravity.ALL
) {
    val resource = context.resources
    val cornerRadiusValue = convertDpToPx(context, cornerRadius)
    val elevationValue = resource.getDimension(elevation)

    val shapeDrawable = ShapeDrawable()
    shapeDrawable.paint.color = ContextCompat.getColor(context, R.color.white)
    shapeDrawable.paint.setShadowLayer(
        convertDpToPx(context, shadowRadius),
        shadowX.toFloat(),
        shadowY.toFloat(),
        ContextCompat.getColor(context, shadowColor)
    )

   val corners = when (roundEdgeDirection) {
        ShadowUtils.RoundedEdge.TOP_LEFT -> {
            floatArrayOf(
                cornerRadiusValue, cornerRadiusValue,   // Top left radius in px
                0f, 0f,   // Top right radius in px
                0f, 0f,     // Bottom right radius in px
                0f, 0f      // Bottom left radius in px
            )
        }
        ShadowUtils.RoundedEdge.TOP_RIGHT -> {
            floatArrayOf(
                0f, 0f,   // Top left radius in px
                cornerRadiusValue, cornerRadiusValue,   // Top right radius in px
                0f, 0f,     // Bottom right radius in px
                0f, 0f      // Bottom left radius in px
            )
        }
        ShadowUtils.RoundedEdge.BOTTOM_RIGHT -> {
            floatArrayOf(
                0f, 0f,   // Top left radius in px
                0f, 0f,   // Top right radius in px
                cornerRadiusValue, cornerRadiusValue,     // Bottom right radius in px
                0f, 0f      // Bottom left radius in px
            )
        }
        ShadowUtils.RoundedEdge.BOTTOM_LEFT -> {
            floatArrayOf(
                0f, 0f,   // Top left radius in px
                0f, 0f,   // Top right radius in px
                0f, 0f,     // Bottom right radius in px
                cornerRadiusValue, cornerRadiusValue     // Bottom left radius in px
            )
        }
        ShadowUtils.RoundedEdge.ALL -> {
           FloatArray(8){cornerRadiusValue}
        }
        ShadowUtils.RoundedEdge.NONE -> {
            FloatArray(8){0f}
        }
    }

    shapeDrawable.shape = RoundRectShape(corners, null, null)

    when (Build.VERSION.SDK_INT) {
        in Build.VERSION_CODES.BASE..Build.VERSION_CODES.O_MR1 -> setLayerType(
            View.LAYER_TYPE_SOFTWARE,
            shapeDrawable.paint
        )
    }

    val drawable = LayerDrawable(arrayOf(shapeDrawable))
    when (shadowGravity) {
        ShadowUtils.ShadowGravity.LEFT -> {
            drawable.setLayerInset(0, 2 * elevationValue.toInt(), 0, 0, 0)
        }
        ShadowUtils.ShadowGravity.TOP -> {
            drawable.setLayerInset(0, 0, elevationValue.toInt(), 0, 0)
        }
        ShadowUtils.ShadowGravity.RIGHT -> {
            drawable.setLayerInset(0, 0, 0, 2 * elevationValue.toInt(), 0)
        }
        ShadowUtils.ShadowGravity.BOTTOM -> {
            drawable.setLayerInset(0, 0, 0, 0, 2 * elevationValue.toInt())
        }
        ShadowUtils.ShadowGravity.ALL -> {
            drawable.setLayerInset(0, 2 * elevationValue.toInt(), 2 * elevationValue.toInt(), 2 * elevationValue.toInt(), 2 * elevationValue.toInt())
        }
        ShadowUtils.ShadowGravity.NONE -> {
            drawable.setLayerInset(0, 0, 0, 0, 0)
        }
    }
    background = drawable
}

fun convertDpToPx(context: Context, dp: Int): Float {
    return dp * context.resources.displayMetrics.density
}