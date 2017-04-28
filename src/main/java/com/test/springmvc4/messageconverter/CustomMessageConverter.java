package com.test.springmvc4.messageconverter;

import com.test.springmvc4.domain.TestObj;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.Charset;

/**
 * Created by lauearo on 27/04/2017.
 */
public class CustomMessageConverter extends AbstractHttpMessageConverter<TestObj> { //inherit AbstractHttpMessageConverter to create custom converter
    public CustomMessageConverter() {
        super(new MediaType("application", "x-lauearo", Charset.forName("UTF-8")));  //create a custom media type
    }

    @Override
    protected boolean supports(Class<?> aClass) {   //defined the class support for
        return TestObj.class.isAssignableFrom(aClass);
    }

    @Override
    protected TestObj readInternal(Class<? extends TestObj> aClass, HttpInputMessage httpInputMessage)
            throws IOException, HttpMessageNotReadableException {   //read input message, convert to target type
        String temp = StreamUtils.copyToString(httpInputMessage.getBody(), Charset.forName("UTF-8"));
        String[] tempArr = temp.split("-");

        return new TestObj(new Long(tempArr[0]), tempArr[1]);
    }

    @Override
    protected void writeInternal(TestObj obj, HttpOutputMessage httpOutputMessage)
            throws IOException, HttpMessageNotWritableException {   //write response message.
        String out = "hello: " + obj.getId() + "-" + obj.getName();
        httpOutputMessage.getBody().write(out.getBytes());
    }
}
