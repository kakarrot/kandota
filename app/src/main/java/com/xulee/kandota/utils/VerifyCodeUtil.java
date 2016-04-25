package com.xulee.kandota.utils;

import java.util.Random;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class VerifyCodeUtil {

	// 验证码图片
	public static Bitmap createBitmap(int width, int height, String code, int textSize) {

		Random random = new Random();
		Bitmap bp = Bitmap.createBitmap(width, height, Config.ARGB_8888);
		Canvas c = new Canvas(bp);

		c.drawColor(Color.WHITE);
		Paint paint = new Paint();
		paint.setTextSize(textSize);

		for (int i = 0; i < code.length(); i++) {
			randomTextStyle(paint, random);
			c.drawText(code.charAt(i) + "", 20 + (width - 40) / code.length() * i, height / 2 + textSize / 3, paint);
		}

		for (int i = 0; i < 10; i++) {
			drawLine(c, paint, width, height, random);
		}

		c.save(Canvas.ALL_SAVE_FLAG);// 保存
		c.restore();//
		return bp;
	}

	private static void drawLine(Canvas canvas, Paint paint, int width, int height, Random random) {
		int color = randomColor(1, random);
		int startX = random.nextInt(width);
		int startY = random.nextInt(height);
		int stopX = random.nextInt(width);
		int stopY = random.nextInt(height);
		paint.setStrokeWidth(1);
		paint.setColor(color);
		canvas.drawLine(startX, startY, stopX, stopY, paint);
	}

	private static int randomColor(int rate, Random random) {
		int red = random.nextInt(256) / rate;
		int green = random.nextInt(256) / rate;
		int blue = random.nextInt(256) / rate;
		return Color.rgb(red, green, blue);
	}

	private static void randomTextStyle(Paint paint, Random random) {
		int color = randomColor(1, random);
		paint.setColor(color);
		paint.setFakeBoldText(random.nextBoolean()); // true为粗体，false为非粗体
		float skewX = (float) random.nextInt(11) / 10;
		skewX = random.nextBoolean() ? -skewX : skewX;
		paint.setTextSkewX(skewX); // float类型参数，负数表示右斜，正数左斜
		// paint.setUnderlineText(random.nextBoolean()); //true为下划线，false为非下划线
		// paint.setStrikeThruText(random.nextBoolean()); //true为删除线，false为非删除线
	}

}
