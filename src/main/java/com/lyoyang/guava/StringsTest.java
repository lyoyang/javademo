package com.lyoyang.guava;

import com.google.common.base.CharMatcher;
import com.google.common.base.Charsets;
import com.google.common.base.Strings;
import org.hamcrest.Matchers;
import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Test;

import java.nio.charset.Charset;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

public class StringsTest {

    @Test
    public void testStringMethod() {
        assertThat(Strings.emptyToNull(""), nullValue());
        assertThat(Strings.nullToEmpty(null), equalTo(""));
        assertThat(Strings.emptyToNull("123"), equalTo("123"));
        assertThat(Strings.commonPrefix("hello","have"), equalTo("h"));
        assertThat(Strings.commonPrefix("hello","like"), equalTo(""));
        assertThat(Strings.commonSuffix("hello","to"), equalTo("o"));
        assertThat(Strings.repeat("to",3), equalTo("tototo"));
        assertThat(Strings.isNullOrEmpty(""), equalTo(true));
        assertThat(Strings.isNullOrEmpty(null), equalTo(true));
        assertThat(Strings.padStart("like", 5, 'i'), equalTo("ilike"));
        assertThat(Strings.padEnd("like", 5, 'y'), equalTo("likey"));
    }

    @Test
    public void testCharset() {
        Charset charset = Charset.forName("UTF-8");
        assertThat(charset, equalTo(Charsets.UTF_8));
    }

    @Test
    public void testCharMatcher() {
        assertThat(CharMatcher.javaDigit().matches('5'), equalTo(true));
        assertThat(CharMatcher.javaDigit().matches('x'), equalTo(false));
        assertThat(CharMatcher.is('a').countIn("a good man"), equalTo(2));
        assertThat(CharMatcher.breakingWhitespace().collapseFrom("  a good man ", '*'), equalTo("*a*good*man*"));
        assertThat(CharMatcher.javaDigit().or(CharMatcher.whitespace()).removeFrom("hello 123 world"), equalTo("helloworld"));
        assertThat(CharMatcher.javaDigit().or(CharMatcher.whitespace()).retainFrom("hello 123 world"), equalTo(" 123 "));
        System.out.println(Strings.nullToEmpty(null));

    }


}
