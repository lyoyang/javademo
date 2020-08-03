package com.lyoyang.test;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author: Brian
 * @Date: 2020/6/12 10:00
 * @Description:
 */
@Slf4j
public class SimpleTest {

    @Test
    public void calc() throws IOException {
//        BigDecimal bigDecimal = new BigDecimal("456565.65867876979");
//        System.out.println(bigDecimal.multiply(BigDecimal.valueOf(100L)).setScale(4, BigDecimal.ROUND_HALF_UP));
//        getNUm();
//        log.info("test msg [{}]", "12345");

        BlockingQueue blockingQueue = new LinkedBlockingQueue();
//        blockingQueue.offer("1");
//        blockingQueue.offer("2");
//        blockingQueue.offer("3");
//        blockingQueue.offer("4");
        System.out.println(blockingQueue.poll());
//        System.out.println(blockingQueue.take());
//        String str = "56464564";
//        int h = str.hashCode();
//        System.out.println(h ^ (h >>> 16));
        System.out.println(3 % 10);
        LinkedList<String> linkedList = new LinkedList<>();
        linkedList.add("hello");
        linkedList.removeLast();

        ArrayList<Integer> intes = new ArrayList<>();
        intes.add(2);
        intes.add(3);
        intes.add(3);
        intes.add(3);
        intes.add(4);
        Iterator<Integer> iterator = intes.iterator();
        while (iterator.hasNext()) {
            Integer next = iterator.next();
            if (next.equals(3)) {
//                intes.remove(next);
                iterator.remove();
            }
        }
//        for (int i = 0; i < intes.size(); i++) {
//            if (intes.get(i).equals(3)) {
//                intes.remove(i);
//            }
//        }

        System.out.println(intes);
    }





    @Test
    public void test() {
        BigDecimal ratio = new BigDecimal("0.0038");
        for (int i=1; i<10000000; i++) {
            BigDecimal amount_05 = new BigDecimal(""+i);
            BigDecimal fee_05 = amount_05.multiply(ratio).setScale(0, BigDecimal.ROUND_HALF_UP);

            BigDecimal amount_03_1 = new BigDecimal(""+ (i/2) );
            BigDecimal fee_03_1 = amount_03_1.multiply(fee_05).divide(amount_05, 0, BigDecimal.ROUND_HALF_UP);

            BigDecimal amount_03_2 = amount_05.subtract(amount_03_1);
            BigDecimal fee_03_2 = amount_03_2.multiply(fee_05).divide(amount_05, 0, BigDecimal.ROUND_HALF_UP);
            if (fee_05.compareTo((fee_03_1.add(fee_03_2))) != 0) {
                System.out.println("amount_05=" + amount_05.toPlainString() + " fee_05=" + fee_05.toPlainString()+ " amount_03_1=" + amount_03_1.toPlainString()+ " fee_03_1=" + fee_03_1.toPlainString()+ " amount_03_2=" + amount_03_2.toPlainString()+ " fee_03_2=" + fee_03_2.toPlainString());
            }
        }
    }


    @Test
    public void getLength() {
        String str = "msg=6Kj9Fm4fE9ljvklkudVnEWgM6ZXqOBlkPxRe5a6JBaa43yWi3RCHu6rwQnuDM7z5VSOTYQKF3mGLoiN1WIV5tSfnfaYX0DuP/CfBiT6eX2Qdl0FHYg56r0kqDegqZA7JTTSJI84BUyRz7IP6iUiERJNeyYHqltO05nrJ5fJA2CitjJuWEkxIJJouWsKQzJSyT+05d6B4dseiRln4zwU9HaLFqsFwqFOeprENs1x4v/eavA95j4MtAVM8czechDg7n39qtuPZeV5/E3C0UTTz//8uwdTcDWChe26jXcoNHYbbSvgJC4WkqZC7hDAToj+9u8qUNsH80YbNq0d9KScCPy+sbRz9bb8j+s9By2vBwXE2uj2uxgO2M3HPzZqe53CCDCO9c9xjhNXx83c99onk5tF8vlWJblHaEx93Q8V5y71h+Zr/r8rPjJY9/lenEooyxTchAhGOfZ/j7PCrUN9hug5lDzQC+7z/LQg9IydoSQ3Ug9hvCthSraeO73V+/73949w4kIxw7xbYJ9IMF1jxM0mhxIrkmPZmGg3x/3WXUZQ3m4YntNrsWji1xjZKZ3RBF35fsomp9dGQxiTVx87rATtN4AvpEI+Y9tSRGbcnkKN4PdmAEmY4x1qrPFSaTgTGQOfXTxQby9uF1OiyoSuoEG6oT3rpoVec5OQL1uK8kolOmZnsd+tEIncbsjHgEryw458n7qkMXGQmgnSFdYCeG787Y94XEJnXXhFvQfvZ6pq1ZMdmwqYtGN+JZBS18lvTvosjYZukfjmUsWlBQD17SpouWsKQzJSyFIn7MK1SzqZo78xTkXQgpzMcpiYRy5Ya9AGL3rQtObo6eKh5nLJRQzuAQk2oYuAS663FFn8irWlh+Zr/r8rPjJY9/lenEooytDFtm5d7lATDmH83u5/b6rlTzuafLnDUsp+Pw4O/TFVdjI2YansgXEe4RFZrcXqw8hWwwH++adQEWpp0LONx8bRm1rAI124uvQzMTrgj4Pydqh8t1h46ITehnxqcmAxZk9snVmM1TCiJiCAXsUYAw+hRIRDJA6+VC/q+LJ4ddMTaOFeKVLaq7OMPk5rDxNmJXRVSf+Ce+Qu2ovwK6/Gg2MIJEERh4ogGJ+d9phfQO49F7OAsI8+etk00iSPOAVMkiYaaCdIltJz0o1gf+wKlzP+YkvQTXNT0xLyJCzXSIM/HMlvoTqAO4+xqKQxCLU/jB/jVIuXP7Uucq5XELBpF9S1M9CrupjpuL9b1ZtJOdqD4RC2bpAr+IfW2prod0S2l/RYqFZMH7Pk7/l63gXXGfZouWsKQzJSyc8fU9uG0FlyhrLamUHSfSnInZ+cTcRpjka1EDv87mueTF+nS+kRWh6HVQ/tQRKPyYfBuBoT/G/rNPwcOGItx+9Ew7n4lEW6l2ttKHjKx09TXyTxO6/2lD82rR30pJwI/L6xt\n" +
                "HP1tvyP6z0HLa8HBcTa6Pa7GA7Yzcc/Nmp7ncIIMI71z3GOE1fHzdz32ieTm0Xy+VYluUdpfrwk++6xJn8s1LWZaAiCsBHMpc1jCGbjKmMvx7UeriS9OaJ0ZfM7nRMc1qjxEKx4nVoq+aZsqktHHsDPltU40RNDWxr6mNz3QNXDMlgkcpWi0TufMwfMll9PlabONdABneceli3awprq6+mKNH1Ym1ptJma6144IolPagFMj1nIQosUr1UG3BDXbOjfwrydZGIEHYnl+GMHnjgpPEfhFG/uNKHF2SJnA=";
        System.out.println(str.getBytes().length);
    }


}
