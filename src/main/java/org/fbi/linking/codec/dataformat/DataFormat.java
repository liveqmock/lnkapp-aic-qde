package org.fbi.linking.codec.dataformat;

/**
 * Created with IntelliJ IDEA.
 * User: zhanrui
 * Date: 13-9-6
 */
public interface DataFormat {
    java.lang.Object toMessage(java.lang.Object o) throws java.lang.Exception;
    java.lang.Object fromMessage(java.lang.Object o) throws java.lang.Exception;
}
