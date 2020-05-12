package com.lyoyang.guava;

import com.google.common.base.Joiner;
import com.google.common.base.Optional;
import com.google.common.base.Splitter;
import com.google.common.collect.ImmutableBiMap;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.lyoyang.guava.cache.Student;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.joining;

public class GuavaUtilTest {

    private static final List<String> l1 = Arrays.asList("java", "google", "php", "kafka");
    private static final List<String> l2 = Arrays.asList("dog", "pig", "giraff", "giralla", null);

    private static final Map<String, String> stringMap = ImmutableBiMap.of("name", "jim", "subject", "PE", "phone", "13444322234");



    @Test(expected = NullPointerException.class)
    public void testJoinerWithNullValue() {
        String join = Joiner.on("#").join(l1);
        System.out.println(join);
        String res2 = Joiner.on("$").skipNulls().join(l2);
        System.out.println(res2);
    }


    @Test
    public void testJoinerSkipNullValue() {
        String join = Joiner.on("#").join(l1);
        System.out.println(join);
        String res1 = Joiner.on("$").skipNulls().join(l2);
        System.out.println(res1);
        String res2 = Joiner.on("$").useForNull("default").join(l2);
        System.out.println(res2);
        StringBuilder stringBuilder = new StringBuilder();
//        stringBuilder.append("hello");
        StringBuilder aDefault = Joiner.on("#").useForNull("default").appendTo(stringBuilder, l2);
        System.out.println(aDefault);
    }

    @Test
    public void testJoinerInJava8() {
        String collect = l2.stream().filter(item -> item != null && !item.isEmpty()).collect(joining("#"));
        System.out.println(collect);
    }

    @Test
    public void testJoinerForMap() {
        String join = Joiner.on("&").withKeyValueSeparator("=").join(stringMap);
        System.out.println(join);
    }

    @Test
    public void testSplitOnSplit() {
        List<String> strings = Splitter.on("|").splitToList("dog|pig|monkey");
        System.out.println(strings);
    }

    @Test
    public void testSplitToOmit() {
        String str = "dog|pig|monkey|||";
        List<String> strings = Splitter.on("|").omitEmptyStrings().splitToList(str);
        System.out.println(strings);

//        trim result
        List<String> res2 = Splitter.on("|").trimResults().omitEmptyStrings().splitToList("pig| panda|cat |monkey");
        System.out.println(res2);

    }


    @Test
    public void testSplitOnFixLenght() {
        List<String> res = Splitter.fixedLength(4).splitToList("aaaabbbbcccc");
        System.out.println(res);
    }

    @Test
    public void testSplitOnLimit() {
        List<String> res = Splitter.on("#").limit(3).splitToList("java#c++#php#scala");
        System.out.println(res);
    }

    @Test
    public void testSplitForMap() {
        Map<String, String> split = Splitter.on(",").trimResults().omitEmptyStrings()
                .withKeyValueSeparator(":").split("{\"name\":\"jim\",\"age\":\"12\"}");
        System.out.println(split);
        System.out.println(split.get("name"));
    }

    @Test
    public void testOptionalTest() {
//        Optional<Integer> of = Optional.of(5);
//        System.out.println(of.get());
//        Optional<Object> absent = Optional.absent();
//        System.out.println(absent.isPresent());
//        List<Student> students = Arrays.asList(new Student(), new Student(), new Student());
//        Lists.partition(students, 2);

        List<Integer> numbers = Lists.newArrayList(1,2,3,4,5,6,7,8,9,10);
        Iterables.partition(numbers,5).forEach(i -> {
            System.out.println("--- seperator ---");
            i.forEach(System.out::println);
        });
        System.out.println("--- The end ---");
    }
}
