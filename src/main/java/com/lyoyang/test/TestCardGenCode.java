package com.lyoyang.test;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.lyoyang.utils.MatrixToImageWriter;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Hashtable;

/**
 * @Description:
 * @User zhaoyf
 * @DateTime 2018/10/30 17:29
 */
public class TestCardGenCode {

    public static void main(String[] args) throws Exception {
        //36242-36542
       /* for(int i=47767;i<47867 ;i++){
            TestCardGenCode.mkCard(i+"");
        }*/
        TestCardGenCode.mkCard(25+"");
    }


    public static void  mkCard(String cardId) throws  Exception{

        //二维码存放地址
        //String cardId = "28168";
        String cardUrl = "https://pay.ipaynow.cn/scanpay/scanCardPay/?cardId="+cardId;
        String filePath = "D:\\card\\card\\";
        String format = "jpg";

        int width = 1250;
        int height = 1250;
        //二维码的图片格式
        Hashtable hints = new Hashtable();
        //二维码留白
        hints.put(EncodeHintType.MARGIN, 1);
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.Q);
        //内容所使用编码
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        BitMatrix bitMatrix = new MultiFormatWriter().encode(cardUrl, BarcodeFormat.QR_CODE, width, height, hints);

        //生成二维码
        String qrCodeImage = filePath + cardId + "." + format;
        File outputFile = new File(qrCodeImage);
        //如果文件已经存在  删掉
        if (outputFile.exists()) outputFile.delete();
        MatrixToImageWriter.writeToFile(bitMatrix, format, outputFile);
        String demoImage = "D:\\card\\hicard.png";
        //写出二维码后 继续处理 模板 logo
        BufferedImage code = ImageIO.read(outputFile);
        Graphics2D codeGraphics = code.createGraphics();
        // 加模板
        BufferedImage big = ImageIO.read(new File(demoImage));
        Graphics2D bigGraphics = big.createGraphics();
        int demoX = (big.getWidth()-width)/2;
        System.out.println(demoX);
        int demoY = (big.getHeight()-height)/2 + 400;
        System.out.println(demoY);
        bigGraphics.drawImage(code, demoX, demoY, code.getWidth(), code.getHeight(), null);

        //填写卡牌编号
        String fileContent = "NO:" + cardId;
        bigGraphics.setBackground(Color.WHITE);
        bigGraphics.setColor(Color.lightGray);//设置字体颜色
        Font font = new Font("黑体", Font.PLAIN, 80);
        bigGraphics.setFont(font);
        int fontX = 2120;
        int fontY = 170;
        bigGraphics.drawString(fileContent, fontX, fontY);
        bigGraphics.dispose();
        ImageIO.write(big, format, outputFile);

    }
}
