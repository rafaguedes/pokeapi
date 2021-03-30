package br.com.yourapp.pokeapi.utils;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import br.com.yourapp.pokeapi.R;

public class StaticButton extends RelativeLayout implements View.OnClickListener {

    View rootView;
    TextView txtTitle;
    RelativeLayout btnContent;

    public StaticButton(Context context) {
        super(context);
        init(context, null);
    }

    public StaticButton(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public StaticButton(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        rootView = inflate(context, R.layout.static_button, this);
        btnContent = rootView.findViewById(R.id.btnContent);
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.ExpandableView, 0, 0);
        txtTitle = rootView.findViewById(R.id.txtTitle);
        String value = typedArray.getString(R.styleable.ExpandableView_customTitle);
        if(value != null && !value.equals("")) {
            txtTitle.setText(value);
        }
        btnContent.setOnClickListener(this);

        boolean isRed = typedArray.getBoolean(R.styleable.ExpandableView_isRed, false);
        if(isRed) {
            btnContent.setBackground(ContextCompat.getDrawable(context, R.drawable.ripple_red));
        }
    }

    @Override
    public void onClick(View v) {
        super.callOnClick();
    }
}