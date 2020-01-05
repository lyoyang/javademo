package com.lyoyang.guava.cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class CacheLoaderTest {


    public void testBasic() throws ExecutionException {
        LoadingCache<String, Student> cache = CacheBuilder.newBuilder().maximumSize(10)
                .expireAfterAccess(30, TimeUnit.MILLISECONDS)
                .build(createCacheLoader());
        Student jim = cache.get("jim");
        assertThat(jim, notNullValue());
        
        

    }



    private CacheLoader<String, Student> createCacheLoader() {
        return new CacheLoader<String, Student>() {
            @Override
            public Student load(String key) throws Exception {
                return findStudentById(key);
            }
        };
    }

    private Student findStudentById(String key) {
        return new Student(key, key, key);
    }


}
