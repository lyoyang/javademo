package com.lyoyang.test;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.lyoyang.entity.Student;
import com.lyoyang.entity.User;
import com.lyoyang.utils.ValidationUtils;
import org.apache.commons.collections.CollectionUtils;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.RSAPrivateKeySpec;
import java.util.*;
import java.util.stream.IntStream;

public class TestDemo {

    private static Base64.Encoder base64Encoder = Base64.getEncoder();
    private static Base64.Decoder base64Decoder = Base64.getDecoder();
    public static void main(String[] args) {
        ArrayList<String> list = Lists.newArrayList();
        list.add("jim");
        list.add("bob");
        list.add("1234");
        System.out.println(list.indexOf("jim"));
//        System.out.println(list.toString());
//        Collections.sort(list);
//        System.out.println(list.toString());
//        User user = new User();
//        test_demo2()
    }


    @Test
    public static void test_demo2() {
        Student student = new Student();
        student.setId(12);
        student.setUsername("jimgssgfdg");
//        String validate = ValidationUtils.validate(student);
////        System.out.println(validate);
////        System.out.println(student);
        System.out.println(student.toString());
    }


    @Test
    public static void test_bigdecimal() {
        BigDecimal bigDecimal = new BigDecimal(12.3);
//        BigDecimal add = bigDecimal.add(new BigDecimal(4));
//        System.out.println(add);
//        System.out.println(Config.NAME);
    }

    @Test
    public void test_hashMap() {
        HashMap<String, String> map = Maps.newHashMap();
        map.put("1", "jim");
        map.put("2", "bob");
    }

    @Test
    public void test_string() {
        String str  = null;
        String[] split = str.split(",");
        System.out.println(split.length);
    }


    @Test
    public void test_for() {
        Random random = new Random();
        ArrayList<Integer> list = new ArrayList<>();
        for(long i=0; i<1000000; i++) {
            list.add(random.nextInt());
        }
        long start = System.currentTimeMillis();
        long sum = 0;
        for(Integer i : list) {
            sum += i;
        }
        System.out.println("sum=" + sum + "   时间：" + (System.currentTimeMillis() - start));
    }

    @Test
    public void test_for2() {
        Random random = new Random();
        ArrayList<Integer> list = new ArrayList<>();
        for(long i=0; i<1000000; i++) {
            list.add(random.nextInt());
        }
        long start = System.currentTimeMillis();
        long sum = 0;
        int len = list.size();
        for(int i=0; i<len; i++) {
            sum += list.get(i);
        }
        System.out.println("sum=" + sum + "   时间：" + (System.currentTimeMillis() - start));

        ArrayList<Object> list1 = Lists.newArrayList();
    }


    @Test
    public static void createSign() throws Exception {
        String source = "{\"name\":\"jim\", \"age\":\"13\"}";
        String privateKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDMOR7nRukNL21t5epyQcC3MEVCXJEdLtBMmD8M0Og/m+c1/XgFoRyLQ7jztxmhP34jjHximAXpbdgYjWQyyphmpcXXyIvnsiYRPNCRe6zlyllLXSLThcAYSexilQbWDhed5avWQ7OmMG5yrn98vUDUUs51DMDwQvuZV4P25uPz2wIDAQAB";
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(privateKey.getBytes());
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PrivateKey privateK = keyFactory.generatePrivate(pkcs8KeySpec);
        Signature signet = Signature.getInstance("RSA");
        signet.initSign(privateK);
        signet.update(source.getBytes());
        byte[] sign = signet.sign();
        System.out.println(new String(sign));
    }
    public static final String KEY_ALGORITHM = "RSA";
    public static final String SIGNATURE_ALGORITHM = "SHA256WithRSA";

    @Test
    public void digitalSignature() throws Exception {
        String privateKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDMOR7nRukNL21t5epyQcC3MEVCXJEdLtBMmD8M0Og/m+c1/XgFoRyLQ7jztxmhP34jjHximAXpbdgYjWQyyphmpcXXyIvnsiYRPNCRe6zlyllLXSLThcAYSexilQbWDhed5avWQ7OmMG5yrn98vUDUUs51DMDwQvuZV4P25uPz2wIDAQAB";
        String data = "{\"name\":\"jim\", \"age\":\"13\"}";
        String ppk = "MIIBVQIBADANBgkqhkiG9w0BAQEFAASCAT8wggE7AgEAAkEAiiOSVP8XS7HMzdKTETG2zkbN2FxN/fTU+vtinH6zyMArLwnuWFeknk4ySopVbndvUSH5APRiSWc1dpR+1c3miQIDAQABAkA6qyTpCHYiZd8vwzFG6jBTp2qha4KMS0FxXvbgtEczCRP6o8bN7ik6jozUgssRAGFRnW3OlB0ekyHRMe/bNwkBAiEA/QukgYHcPDV/N/XVFaSd6NP+PgcYZe+PNjM3ty2uAfECIQCLwHir0v8fQ+Rp1IlmYfFX9++o/GFQVctpV77/kPUWGQIhAKrUtVaJKPms1BofaJ+X5tNMaxPNDnei5vgcU7zipdrBAiBAy6KneBLVujLDZsm18J9nvAsgfLPZ/Y1scEScVedssQIhAJAViQQJGjZLY0bFuSIRvtRTazUrfvOQuZ/Ggx26D38X";
        String ppk2 = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAMw5HudG6Q0vbW3l6nJBwLcwRUJckR0u0EyYPwzQ6D+b5zX9eAWhHItDuPO3GaE/fiOMfGKYBelt2BiNZDLKmGalxdfIi+eyJhE80JF7rOXKWUtdItOFwBhJ7GKVBtYOF53lq9ZDs6YwbnKuf3y9QNRSznUMwPBC+5lXg/bm4/PbAgMBAAECgYBmPnbNXYASPNtxePleiLLeRAl4xvVUAfsBTfvLA9AzAG/KZQMw9k9fHeG8CsCHLU2OqKghiHD1RCw7ZpZyo2GWpObMd1ulN/Ej6IToFxFuPbFejKL9f/sLYAuIW9EeUDIb+L7z6Qemw6SeLfx6jzOf4lo5za8vVNQC8SGJJC1EEQJBAPcRKr/9SoSA3d5ZhKs8w70mIU43aC3uabs/BhX58MNpurx/LK1X0UiNsN/OCSfeDhIwrAOQGmDvpBpNEuMc2FkCQQDTm2TKgzvZKtzEPTsE93H6GlvoKVxC8ZsfBw2O3qCTIrOVcnDVdYKw6HWTGCf9a3Up5zIQFoqvSFNrSnTlrmdTAkA4V0jZaUhx+PUffGh9qCi23Jghvw7Z0pF36sJXhpy540V7MMEecnU+R1l7X//EgKXSzhib42OwkVVpzgOtmOlZAkBhqXt4N99cWHrxmyztGuMgwh+e48/wGaA1TsUEJl3H6qElnV7UNdrumSsCi4iRXpIBl/G0GSNCyMevYfrXsol/AkAijAf8hADbucII3MPDtcyhwVeRKRirz2ICpW/a5sW3d83rn6K6FqT1ToUPg2Qvj3cH7CI/wBWTzrW42zxqT0BF";
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(base64Decoder.decode(ppk2));
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PrivateKey privateK = keyFactory.generatePrivate(pkcs8KeySpec);
        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
        signature.initSign(privateK);
        signature.update(data.getBytes());
        byte[] sign = signature.sign();
        System.out.println(new String(sign));
    }


    @Test
    public void test_key() throws NoSuchAlgorithmException {
        String privateKey = RSAUtils.getPrivateKey(RSAUtils.genKeyPair());
        System.out.println(privateKey);
    }

}
