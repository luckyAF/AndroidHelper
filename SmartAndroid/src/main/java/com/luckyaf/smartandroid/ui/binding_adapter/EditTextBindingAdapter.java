package com.luckyaf.smartandroid.ui.binding_adapter;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.widget.EditText;
import com.luckyaf.smartandroid.ui.binding_adapter.commond.BindingCommand;
import androidx.databinding.BindingAdapter;
/**
 * 类描述：editText
 *
 * @author Created by luckyAF on 2021/10/19
 */
@SuppressWarnings("unused")
public class EditTextBindingAdapter {


    @BindingAdapter(value={"onTextChanged"})
    public static void addTextChangedListener(EditText editText, final BindingCommand<String> textChanged) {
        editText.addTextChangedListener(new TextWatcher(){
            @Override
            public void beforeTextChanged(CharSequence text, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence text, int start, int before, int count) {
                textChanged.execute(text.toString());
            }
            @Override
            public void afterTextChanged(Editable s) { }
        });
    }

    @BindingAdapter(value={"onEnterKeyDown"})
    public static void addEnterKeyListener(EditText editText, final BindingCommand<String> enterKeyDown) {
        editText.setOnKeyListener((v, keyCode, event) -> {
            boolean result = false;
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {
                enterKeyDown.execute(editText.getText().toString());
                result = true;
            } else if (keyCode == KeyEvent.KEYCODE_DPAD_UP || keyCode == KeyEvent.KEYCODE_DPAD_DOWN
                    || keyCode == KeyEvent.KEYCODE_DPAD_LEFT || keyCode == KeyEvent.KEYCODE_DPAD_RIGHT) {
                result = true;
            } else if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_UP) {
                result = true;
            }
            return result;
        });
    }
}
