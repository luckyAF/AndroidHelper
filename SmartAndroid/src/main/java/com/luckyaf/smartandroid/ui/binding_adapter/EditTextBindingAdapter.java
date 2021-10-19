package com.luckyaf.smartandroid.ui.binding_adapter;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
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
        editText.setOnEditorActionListener((v, actionId, event) ->{
            boolean result = false;
            if (actionId == EditorInfo.IME_ACTION_SEND
                    || actionId == EditorInfo.IME_ACTION_DONE
                    || actionId == EditorInfo.IME_ACTION_SEARCH
                    || actionId == EditorInfo.IME_ACTION_GO){
                enterKeyDown.execute(editText.getText().toString());
                return true;
            }
            if(event != null && KeyEvent.KEYCODE_ENTER == event.getKeyCode()
                    && KeyEvent.ACTION_UP == event.getAction()){
                enterKeyDown.execute(editText.getText().toString());
                return true;
            }
            return  false;
        });
    }
}
