package xyz.shuttle.filebox;


import org.junit.Test;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.security.crypto.keygen.KeyGenerators;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.KeyGenerator;
import java.io.IOException;
import java.util.Arrays;
import java.util.Base64;

public class TestClass {
    @Test
    public void test(){
        try {
            System.out.println(
                    new String(new BASE64Decoder().decodeBuffer(
            "dWYvdXNlci91dC91c2VyMS9mbi9HYXlsZSBMYWFrbWFubiBNY0Rvd2VsbCAtIENyYWNraW5nIHRoZSBDb2RpbmcgSW50ZXJ2aWV3IC0gMjAxNi5wZGYvc2FsdC8y"
                    ))
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test2(){
        System.out.println(
                new String(Base64.getEncoder().encode(
                        "Gayle Laakmann McDowell - Cracking the Coding Interview - 2016.pdf".getBytes())));
    }
}
