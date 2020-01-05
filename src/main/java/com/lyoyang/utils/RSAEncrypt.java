//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.lyoyang.utils;


import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

public class RSAEncrypt {
    public RSAEncrypt() {
    }

    public byte[] encrypt(Key publicKey, byte[] plainTextData) throws Exception {
        if(publicKey == null) {
            throw new Exception("加密公钥为空, 请设置");
        } else {
            Cipher cipher = null;

            try {
                cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
                cipher.init(1, publicKey);
                byte[] output = cipher.doFinal(plainTextData);
                return output;
            } catch (NoSuchAlgorithmException var5) {
                throw new Exception("无此加密算法");
            } catch (NoSuchPaddingException var6) {
                var6.printStackTrace();
                return null;
            } catch (InvalidKeyException var7) {
                throw new Exception("加密公钥非法,请检查");
            } catch (IllegalBlockSizeException var8) {
                throw new Exception("明文长度非法");
            } catch (BadPaddingException var9) {
                throw new Exception("明文数据已损坏");
            }
        }
    }

    public byte[] decrypt(Key privateKey, byte[] cipherData) throws Exception {
        if(privateKey == null) {
            throw new Exception("解密私钥为空, 请设置");
        } else {
            Cipher cipher = null;

            try {
                cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
                cipher.init(2, privateKey);
                byte[] output = cipher.doFinal(cipherData);
                return output;
            } catch (NoSuchAlgorithmException var5) {
                throw new Exception("无此解密算法");
            } catch (NoSuchPaddingException var6) {
                var6.printStackTrace();
                return null;
            } catch (InvalidKeyException var7) {
                throw new Exception("解密私钥非法,请检查");
            } catch (IllegalBlockSizeException var8) {
                throw new Exception("密文长度非法");
            } catch (BadPaddingException var9) {
                throw new Exception("密文数据已损坏");
            }
        }
    }
}
