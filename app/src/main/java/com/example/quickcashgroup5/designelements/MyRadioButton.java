package com.example.quickcashgroup5.designelements;

import android.content.Context;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatRadioButton;

/**
 * Radio Button design element
 */
public class MyRadioButton extends AppCompatRadioButton {

    private OnCheckedChangeListener onCheckedChangeListener;

    /**
     * The constructor of Radio Button class
     *
     * @param context
     */
    public MyRadioButton(Context context) {
        super(context);
    }

    /**
     * The constructor of Radio Button class
     *
     * @param context
     */
    public MyRadioButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * The constructor of Radio Button class
     *
     * @param context
     */
    public MyRadioButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * Overrides the onAttachedToWindow() method
     */
    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();

        setOwnOnCheckedChangeListener();
        setButtonDrawable(null);//lets remove the default drawable to create our own

    }

    /**
     * Sets OnCheckedChangeListener
     *
     * @param onCheckedChangeListener
     */
    public void setOwnOnCheckedChangeListener(OnCheckedChangeListener onCheckedChangeListener) {
        this.onCheckedChangeListener = onCheckedChangeListener;
    }

    /**
     * Sets OnCheckedChangeListener
     */
    private void setOwnOnCheckedChangeListener() {
        setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (onCheckedChangeListener != null) {
                //this is called when we have set our listener
                onCheckedChangeListener.onCheckedChanged(buttonView, isChecked);
            }
        });
    }

}