package com.suk.blog.util;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.CubicCurve2D;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.Random;

import com.google.code.kaptcha.NoiseProducer;
import com.google.code.kaptcha.util.Configurable;

/**
 * @author: Suk
 * @description: 验证码干扰线
 * @date:Created in 21:37 2020/2/14
 * @modified By:
 */
public class VerifyCodeNoise extends Configurable implements NoiseProducer {
    /**
     * Draws a noise on the image. The noise curve depends on the factor values.
     * Noise won't be visible if all factors have the value > 1.0f
     *
     * @param image       the image to add the noise to
     * @param factorOne
     * @param factorTwo
     * @param factorThree
     * @param factorFour
     */
    public void makeNoise(BufferedImage image, float factorOne,
                          float factorTwo, float factorThree, float factorFour) {
        // image size
        int width = image.getWidth();
        int height = image.getHeight();

        // the points where the line changes the stroke and direction
        Point2D[] pts = null;
        Random rand = new Random();

        //   使用 float 坐标指定的三次参数曲线段。
        CubicCurve2D cc = new CubicCurve2D.Float(width * factorOne, height
                * rand.nextFloat(), width * factorTwo, height
                * rand.nextFloat(), width * factorThree, height
                * rand.nextFloat(), width * factorFour, height
                * rand.nextFloat());

        //  返回定义变平形状边界的迭代对象。
        PathIterator pi = cc.getPathIterator(null, 2);
        Point2D tmp[] = new Point2D[200];
        int i = 0;

        // while pi is iterating the curve, adds points to tmp array
        while (!pi.isDone()) {
            float[] coords = new float[6];
            switch (pi.currentSegment(coords))//使用迭代返回当前路径段的坐标和类型,必须传入长度为 6 的 float 数组
            {
                case PathIterator.SEG_MOVETO: //SEG_MOVETO 和 SEG_LINETO 类型返回一个点
                case PathIterator.SEG_LINETO:
                case PathIterator.SEG_CUBICTO: //SEG_CUBICTO 返回 3 个点
                case PathIterator.SEG_QUADTO: //SEG_QUADTO 返回两个点
                    tmp[i] = new Point2D.Float(coords[0], coords[1]);
            }
            i++;
            pi.next();
        }

        pts = new Point2D[i];
        System.arraycopy(tmp, 0, pts, 0, i);

        Graphics2D graph = (Graphics2D) image.getGraphics();
        graph.setRenderingHints(new RenderingHints(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON));


        // 设置干扰线10条；可以自行设置，每条干扰线的颜色位置都随机产生
        for (i = 0; i < 10; i++) {
            int xs = rand.nextInt(width);
            int ys = rand.nextInt(height);
            int xe = xs + rand.nextInt(width / 8);
            int ye = ys + rand.nextInt(height / 8);
            graph.setColor(getColor());
            graph.drawLine(xs, ys, xe, ye);
        }

        graph.dispose();
    }

    /**
     * 干扰线 的颜色随机生成
     *
     * @param @return
     * @return Color
     * @throws
     * @Title: getColor
     * @author ：爱吃草莓的小兔叽
     * @Description: TODO
     * @data 2017年3月31日下午1:32:32
     */
    private Color getColor() {
        Random random = new Random();
        int red = random.nextInt(255);
        int green = random.nextInt(255);
        int blue = random.nextInt(255);
        return new Color(red, green, blue);
    }
}
