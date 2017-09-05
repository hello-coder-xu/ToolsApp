package com.tools.glide;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;

import java.io.File;

/**
 * Created by xuge on 2017/3/6.
 * 网络图片加载类
 */

public class GlideUtils {
    private static GlideUtils instance;

    public static GlideUtils getInstance() {
        if (instance == null) {
            synchronized (GlideUtils.class) {
                if (instance == null) {
                    instance = new GlideUtils();
                }
            }
        }
        return instance;
    }

    /**
     * 图片转圆形
     *
     * @param context
     * @param path
     * @param imageView
     */
    @SuppressWarnings("unchecked")
    public void LoadContextCircleBitmap(Context context, String path, ImageView imageView) {
        Glide
                .with(context)
                .load(path)
                .bitmapTransform(new GlideCircleTransform(context))
                .into(imageView);
    }

    /**
     * 图片转圆形
     *
     * @param context
     * @param path
     * @param imageView
     */
    public void LoadContextCircleFile(Context context, File path, ImageView imageView) {
        Glide
                .with(context)
                .load(path)
                .bitmapTransform(new GlideCircleTransform(context))
                .into(imageView);
    }

    /**
     * 图片转圆形
     *
     * @param context
     * @param resId
     * @param imageView
     */
    @SuppressWarnings("unchecked")
    public void LoadContextCircleBitmap(Context context, int resId, ImageView imageView) {
        Glide
                .with(context)
                .load(resId)
                .bitmapTransform(new GlideCircleTransform(context))
                .into(imageView);
    }


    /**
     * 图片无角度
     */
    @SuppressWarnings("unchecked")
    public void loadContextRoundFitCenter(Context context, String path, ImageView imageView) {
        Glide.with(context).load(path).fitCenter().into(imageView);
    }

    /**
     * 图片无角度
     */
    @SuppressWarnings("unchecked")
    public void loadContextRoundCenterCrop(Context context, String path, ImageView imageView) {
        Glide.with(context).load(path).centerCrop().into(imageView);
    }

    /**
     * 图片有角度
     */
    @SuppressWarnings("unchecked")
    public void loadContextRoundBitmap(Context context, String path, ImageView imageView, int roundradius) {
        Glide.with(context).load(path)
                .transform(new CenterCrop(context)
                        , new GlideRoundTransform(context, roundradius)).into(imageView);
    }
    /**
     * 图片有角度
     */
    @SuppressWarnings("unchecked")
    public void loadContextRoundBitmap(Context context, int path, ImageView imageView, int roundradius) {
        Glide.with(context).load(path)
                .fitCenter()
                .transform(new GlideRoundTransform(context, roundradius)).into(imageView);
    }

    /**
     * 图片有角度--顶部
     */
    public void loadContextRoundTopBitmap(Context context, String path, ImageView imageView, int roundradius) {
        Glide.with(context)
                .load(path)
                .bitmapTransform(new CenterCrop(context), new RoundedCornersTransformation(context, roundradius, 0, RoundedCornersTransformation.CornerType.TOP))
                .crossFade(1000)
                .into(imageView);
    }
}
