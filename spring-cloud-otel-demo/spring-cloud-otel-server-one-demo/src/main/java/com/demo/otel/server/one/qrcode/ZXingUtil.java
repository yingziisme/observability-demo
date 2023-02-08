package com.demo.otel.server.one.qrcode;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.OutputStream;
import java.util.Hashtable;


public class ZXingUtil {
    private static final String CHARSET = "utf-8";
    public static final String FORMAT = "JPG";
    // 二维码尺寸
    private static final int QRCODE_SIZE = 300;

    /**
     * 生成二维码
     *
     * @param content 二维码内容
     * @return 图片
     * @throws Exception
     */
    public static BufferedImage createImage(String content) throws Exception {
        // 二维码参数设置
        Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();
        // 安全等级，最高h
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        // 编码设置
        hints.put(EncodeHintType.CHARACTER_SET, CHARSET);
        // 设置margin=0-10
        hints.put(EncodeHintType.MARGIN, 1);
        // 创建矩阵容器
        BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, QRCODE_SIZE, QRCODE_SIZE,
                hints);
        int width = bitMatrix.getWidth();
        int height = bitMatrix.getHeight();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        // 矩阵转换图像
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, bitMatrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF);
            }
        }
        return image;
    }


    /**
     * 生成二维码
     *
     * @param content 内容
     * @param output  输出流
     * @throws Exception
     */
    public static void encode(String content, OutputStream output)
            throws Exception {
        BufferedImage image = ZXingUtil.createImage(content);
        ImageIO.write(image, FORMAT, output);
    }
}
