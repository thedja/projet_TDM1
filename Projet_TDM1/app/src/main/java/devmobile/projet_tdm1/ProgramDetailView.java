package devmobile.projet_tdm1;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import devmobile.projet_tdm1.model.ProgrammeTele;


/**
 * TODO: document your custom view class.
 */
public class ProgramDetailView extends LinearLayout {


    public ProgramDetailView(Context context, ProgrammeTele programme) {
        super(context);
        bind();
        init(null, 0, programme);
    }

    public ProgramDetailView(Context context, AttributeSet attrs, ProgrammeTele programme) {
        super(context, attrs);
        init(attrs, 0, programme);
    }

    public ProgramDetailView(Context context, AttributeSet attrs, int defStyle, ProgrammeTele programme) {
        super(context, attrs, defStyle);
        init(attrs, defStyle, programme);
    }

    private void init(AttributeSet attrs, int defStyle, ProgrammeTele programme) {

    }

    private void bind(){
        
    }
}
