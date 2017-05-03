package com.tata.android.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PixelFormat;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.NinePatchDrawable;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 
 * 
 * 名称： ImageTools.java
 * 
 * 描述：图片工具类
 * 
 * 最近修改时间：2013-4-17 下午2:37:57
 * 
 * @since 2013-4-17
 * 
 * @author lzt
 * 
 */
public class ImageUtils {

	private static final String TAG = "ImageTools";

	/**
	 * 把图片按固定比例缩小
	 * 
	 * @param bmp
	 * @param iWidth
	 * @param iHeight
	 * @return 修改时间：2013-4-17 下午2:37:52
	 * @author lzt
	 */
	public static Bitmap imageZoom(Bitmap bmp, int iWidth, int iHeight) {
		Bitmap newBmp = null;
		int imageHeight = bmp.getHeight();
		int imageWidth = bmp.getWidth();
		float scaleW = 1;
		float scaleH = 1;
		double scalex = (float) iWidth / imageWidth;
		double scaley = (float) iHeight / imageHeight;
		scaleW = (float) (scaleW * scalex);
		scaleH = (float) (scaleH * scaley);
		Matrix matrix = new Matrix();
		matrix.postScale(scaleW, scaleH);
		newBmp = Bitmap.createBitmap(bmp, 0, 0, imageWidth, imageHeight,
				matrix, true);
		return newBmp;
	}

	/**
	 * 保存图片
	 * 
	 * @param bmp
	 * @param filePath
	 *            绝对路径
	 * @param quality
	 *            质量
	 * @modifiedTime 下午5:12:02
	 * @author lzt
	 */
	public static void saveImage(Bitmap bmp, String filePath, int quality) {
		if (bmp != null) {
			FileOutputStream fos = null;
			try {
				File fatherFile = new File(filePath);// 默认保存在/sdcard/.hdxf/images/
				if (!fatherFile.exists()) {
					fatherFile.mkdir();
				}
				File n = new File(filePath);
				fos = new FileOutputStream(n.getAbsolutePath());
				bmp.compress(Bitmap.CompressFormat.JPEG, quality, fos);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					fos.flush();
					fos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
		// 回收照片
		if (!bmp.isRecycled()) {
			bmp.recycle();
			bmp = null;
		}
	}

	// 根据路径获得图片并压缩，返回bitmap用于显示
	public static Bitmap getSmallBitmap(String filePath) {
		final BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(filePath, options);
		options.inSampleSize = 4;
		// Decode bitmap with inSampleSize set
		options.inJustDecodeBounds = false;

		return BitmapFactory.decodeFile(filePath, options);
	}

	/**
	 * 压缩图片
	 * 
	 * @param filePath
	 * @return
	 * @modifiedTime 下午5:13:42
	 * @author lzt
	 */
	public static byte[] compressBitmap(String filePath, int quality) {
		quality = 90;// 如果不指定，默认为90
		Bitmap bm = getSmallBitmap(filePath);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		bm.compress(Bitmap.CompressFormat.JPEG, quality, baos);
		return baos.toByteArray();
	}

	/**
	 * 计算SampleSize
	 * 
	 * @param options
	 * @param reqWidth
	 * @param reqHeight
	 * @return
	 * @modifiedTime 下午5:14:19
	 * @author lzt
	 */
	public static int calculateInSampleSize(BitmapFactory.Options options,
			int reqWidth, int reqHeight) {
		final int height = options.outHeight;
		final int width = options.outWidth;
		int inSampleSize = 1;

		if (height > reqHeight || width > reqWidth) {
			final int heightRatio = Math.round((float) height
					/ (float) reqHeight);
			final int widthRatio = Math.round((float) width / (float) reqWidth);
			inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
		}
		return inSampleSize;
	}

	/**
	 * 根据路径得到bitmap
	 * 
	 * @param path
	 * @return
	 * @modifiedTime：2013-5-12 下午5:21:54
	 * @author lzt
	 */
	public static Bitmap getBitmapFromPath(String path) {
		Bitmap bitmap = null;
		try {
			BitmapFactory.Options opts = new BitmapFactory.Options();
			opts.inSampleSize = 1;
			FileInputStream fis;
			fis = new FileInputStream(new File(path));
			bitmap = BitmapFactory.decodeStream(fis, null, opts);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bitmap;
	}
	public static Bitmap drawable2Bitmap(Drawable drawable) {
		if (drawable instanceof BitmapDrawable) {
			return ((BitmapDrawable) drawable).getBitmap();
		} else if (drawable instanceof NinePatchDrawable) {
			Bitmap bitmap = Bitmap
					.createBitmap(
							drawable.getIntrinsicWidth(),
							drawable.getIntrinsicHeight(),
							drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888
									: Bitmap.Config.RGB_565);
			Canvas canvas = new Canvas(bitmap);
			drawable.setBounds(0, 0, drawable.getIntrinsicWidth(),
					drawable.getIntrinsicHeight());
			drawable.draw(canvas);
			return bitmap;
		} else {
			return null;
		}
	}
}
