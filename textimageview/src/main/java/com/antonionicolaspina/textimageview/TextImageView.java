package com.antonionicolaspina.textimageview;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.ImageView;

public class TextImageView extends ImageView {
  private String text;
  private Paint paint;
  private RectF imageRect;
  private Rect textRect;

  public TextImageView(Context context) {
    super(context);
    init(context, null);
  }

  public TextImageView(Context context, AttributeSet attrs) {
    super(context, attrs);
    init(context, attrs);
  }

  public TextImageView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init(context, attrs);
  }

  @TargetApi(Build.VERSION_CODES.LOLLIPOP)
  public TextImageView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
    super(context, attrs, defStyleAttr, defStyleRes);
    init(context, attrs);
  }

  protected void init(Context context, AttributeSet attributeSet) {
    if (null != attributeSet) {
      TypedArray attrs = context.getTheme().obtainStyledAttributes(attributeSet, R.styleable.TextImageView, 0, 0);
      text = attrs.getString(R.styleable.TextImageView_text);
      attrs.recycle();
    }
    paint     = new Paint(Paint.ANTI_ALIAS_FLAG);
    imageRect = new RectF();
    textRect  = new Rect();
  }

  @Override
  protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);

    if ( (null == text) && isInEditMode()) {
      text = "sample text2";
    }

    if (null == text) {
      return;
    }

    // Get rectangle of the bitmap (drawable) drawn in the imageView.
    imageRect.right = getDrawable().getIntrinsicWidth();
    imageRect.bottom = getDrawable().getIntrinsicHeight();

    // Translate and scale the bitmapRect according to the imageview's scale-type, etc.
    getImageMatrix().mapRect(imageRect);

    // Draw text.
    paint.setColor(Color.BLACK);
    paint.setTextSize(200);
    paint.getTextBounds(text, 0, text.length(), textRect);
    canvas.drawText(text, imageRect.left, imageRect.top+textRect.height(), paint);
  }
}
