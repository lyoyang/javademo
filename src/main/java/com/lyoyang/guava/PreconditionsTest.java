package com.lyoyang.guava;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import org.junit.Test;

import java.util.List;

public class PreconditionsTest {

    @Test(expected = NullPointerException.class)
    public void testChecNotNull() {
        checkNotNull(null);
    }

    @Test
    public void testChecNotNullWithMessage() {
        try {
            checkNotNullWithMessage(null);
        } catch (Exception e) {
            assertThat(e.getMessage(), equalTo("the list can not be null"));
        }
    }


    @Test
    public void testCheckArgument() {
        final String type = "A";
        try {
            Preconditions.checkArgument(type.equals("B"), "argument is not right!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    @Test
    public void testCheckState() {
        final String state = "0";
        try {
            Preconditions.checkState(state.equals("1"), "the state is illegal!");
            fail("should not be process to here!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testCheckIndex() {
        try {
            List<String> list = ImmutableList.of();
            Preconditions.checkElementIndex(10, list.size());
            fail("should not be process to here!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testAssert() {
        List<String> list = null;
        assert list != null;
    }

    @Test
    public void testAssertWithMessage() {
        List<String> list = null;
        assert list != null : "the list can not be null";
    }




    @Test
    public void testChecNotNullWithFormatMessage() {
        try {
            checkNotNullWithFormatMessage(null);
        } catch (Exception e) {
            assertThat(e.getMessage(), equalTo("the list size should be 2"));
        }
    }



    private void checkNotNull(final List<String> list) {
        Preconditions.checkNotNull(list);
    }

    private void checkNotNullWithMessage(final List<String> list) {
        Preconditions.checkNotNull(list, "the list can not be null");
    }

    private void checkNotNullWithFormatMessage(final List<String> list) {
        Preconditions.checkNotNull(list, "the list size should be %s", 2);
    }




}
