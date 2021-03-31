package br.com.yourapp.pokeapi.utils

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import br.com.yourapp.pokeapi.R

class StaticButton : RelativeLayout, View.OnClickListener {
    var rootViewButton: View? = null
    lateinit var txtTitle: TextView
    lateinit var btnContent: RelativeLayout

    constructor(context: Context) : super(context) {
        init(context, null)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(context, attrs)
    }

    private fun init(context: Context, attrs: AttributeSet?) {
        rootViewButton = View.inflate(context, R.layout.static_button, this)
        btnContent = rootView.findViewById(R.id.btnContent)
        val typedArray = context.theme.obtainStyledAttributes(attrs, R.styleable.ExpandableView, 0, 0)
        txtTitle = rootView.findViewById(R.id.txtTitle)
        val value = typedArray.getString(R.styleable.ExpandableView_customTitle)
        if (value != null && value != "") {
            txtTitle.setText(value)
        }
        btnContent.setOnClickListener(this)
        val isRed = typedArray.getBoolean(R.styleable.ExpandableView_isRed, false)
        if (isRed) {
            btnContent.setBackground(ContextCompat.getDrawable(context, R.drawable.ripple_red))
        }
    }

    override fun onClick(v: View) {
        super.callOnClick()
    }
}