package com.lyoyang.classloader;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CustomerClassLoader extends ClassLoader {

    private final static Path DEFAULT_CLASS_DIR = Paths.get("/home/yangbing/tmp", "classloader");

    private final Path classDir;

    public CustomerClassLoader() {
        super();
        this.classDir = DEFAULT_CLASS_DIR;
    }

    public CustomerClassLoader(String classDir) {
        super();
        this.classDir = Paths.get(classDir);
    }

    public CustomerClassLoader(ClassLoader parent, String classDir) {
        super(parent);
        this.classDir = Paths.get(classDir);
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] classBytes = this.readClassBytes(name);
        if (classBytes == null || classBytes.length == 0) {
            throw new ClassNotFoundException("the class " + name + " not found");
        }
        return this.defineClass(name, classBytes, 0, classBytes.length);

    }

    private byte[] readClassBytes(String name) throws ClassNotFoundException {
        String classPath = name.replace(".", "/");
        Path classFullPath = classDir.resolve(Paths.get(classPath + ".class"));
        if (!classFullPath.toFile().exists()) {
            throw new ClassNotFoundException("the class " + name + " not found");
        }
        try(ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            Files.copy(classFullPath, baos);
            return baos.toByteArray();
        } catch (IOException e) {
            throw new ClassNotFoundException("the class " + name + " not found");
        }
    }

}
