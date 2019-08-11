/*
 * Copyright (C) 2008-2009 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package nodomain.vanous.customkeyboard;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.Keyboard.Key;
import android.inputmethodservice.KeyboardView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.inputmethod.InputMethodSubtype;
import android.graphics.drawable.Drawable;
import java.util.List;
//import java.util.Arrays; //unused atm

public class LatinKeyboardView extends KeyboardView {

    static final int KEYCODE_OPTIONS = -100;
    // TODO: Move this into android.inputmethodservice.Keyboard
    static final int KEYCODE_LANGUAGE_SWITCH = -101;

	private Drawable dr;


    public LatinKeyboardView(Context context, AttributeSet attrs) {
        super(context, attrs);
			dr = context.getResources().getDrawable(R.drawable.bottom);
    }

    public LatinKeyboardView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected boolean onLongPress(Key key) {
        if (key.codes[0] == Keyboard.KEYCODE_CANCEL) {
            getOnKeyboardActionListener().onKey(KEYCODE_OPTIONS, null);
            return true;
        /*} else if (key.codes[0] == 113) {

            return true; */
        } else {
            //Log.d("LatinKeyboardView", "KEY: " + key.codes[0]);
            return super.onLongPress(key);
        }
    }

    void setSubtypeOnSpaceKey(final InputMethodSubtype subtype) {
        final LatinKeyboard keyboard = (LatinKeyboard)getKeyboard();
        invalidateAllKeys();
    }
@Override
public void onDraw(Canvas canvas) {
    super.onDraw(canvas);

    List<Key> keys = getKeyboard().getKeys();
    for (Key key : keys) {            
    //Log.e("KEY", "Drawing key with code " + key.codes[0]);
        if (key.codes[0] == 32) {
            dr.setBounds(key.x, key.y, key.x + key.width, key.y + key.height);
            dr.draw(canvas);

        }            
    }
}

}