package com.jamie;

import com.sun.star.beans.PropertyValue;
import com.sun.star.comp.helper.BootstrapException;
import com.sun.star.frame.XComponentLoader;
import com.sun.star.frame.XStorable;
import com.sun.star.io.*;
import com.sun.star.lang.XComponent;
import com.sun.star.lang.XMultiComponentFactory;
import com.sun.star.uno.UnoRuntime;
import com.sun.star.uno.XComponentContext;
import com.sun.star.util.XCloseable;
import ooo.connector.BootstrapSocketConnector;

import java.io.*;
import java.io.IOException;

public class OpenOfficeConverter {
    private static String oooExecutableFolder = "D:\\software\\LibreOffice\\program";
//    private static String oooExecutableFolder = "D:\\software\\OpenOffice_4.1.10_SDK\\sdk";

    static class OOoOutputStream extends ByteArrayOutputStream implements XOutputStream {
        public OOoOutputStream() {
            super(32768);
        }

        @Override
        public void writeBytes(byte[] values) throws NotConnectedException, BufferSizeExceededException, com.sun.star.io.IOException {
            try {
                this.write(values);
            } catch (java.io.IOException e) {
                throw (new com.sun.star.io.IOException(e.getMessage()));
            }
        }

        @Override
        public void closeOutput() throws NotConnectedException, BufferSizeExceededException, com.sun.star.io.IOException {
            try {
                super.flush();
                super.close();
            } catch (java.io.IOException e) {
                throw (new com.sun.star.io.IOException(e.getMessage()));
            }
        }

        @Override
        public void flush() {
            try {
                super.flush();
            } catch (java.io.IOException e) {
            }
        }
    }

    static class OOoInputStream extends ByteArrayInputStream implements XInputStream, XSeekable {

        public OOoInputStream(byte[] buf) {
            super(buf);
        }

        @Override
        public int readBytes(byte[][] buffer, int bufferSize) throws NotConnectedException, BufferSizeExceededException, com.sun.star.io.IOException {
            int numberOfReadBytes;
            try {
                byte[] bytes = new byte[bufferSize];
                numberOfReadBytes = super.read(bytes);
                if (numberOfReadBytes > 0) {
                    if (numberOfReadBytes < bufferSize) {
                        byte[] smallerBuffer = new byte[numberOfReadBytes];
                        System.arraycopy(bytes, 0, smallerBuffer, 0, numberOfReadBytes);
                        bytes = smallerBuffer;
                    }
                } else {
                    bytes = new byte[0];
                    numberOfReadBytes = 0;
                }

                buffer[0] = bytes;
                return numberOfReadBytes;
            } catch (java.io.IOException e) {
                throw new com.sun.star.io.IOException(e.getMessage(), this);
            }
        }

        @Override
        public int readSomeBytes(byte[][] buffer, int bufferSize) throws NotConnectedException, BufferSizeExceededException, com.sun.star.io.IOException {
            return readBytes(buffer, bufferSize);
        }

        @Override
        public void skipBytes(int skipLength) throws NotConnectedException, BufferSizeExceededException, com.sun.star.io.IOException {
            skip(skipLength);
        }

        @Override
        public void closeInput() throws NotConnectedException, com.sun.star.io.IOException {
            try {
                close();
            } catch (java.io.IOException e) {
                throw new com.sun.star.io.IOException(e.getMessage(), this);
            }
        }

        @Override
        public long getLength() throws com.sun.star.io.IOException {
            return count;
        }

        @Override
        public long getPosition() throws com.sun.star.io.IOException {
            return pos;
        }

        @Override
        public void seek(long position) throws IllegalArgumentException, com.sun.star.io.IOException {
            pos = (int) position;
        }
    }


    //word2pdf
    public void convert(OOoInputStream input, OOoOutputStream output, String filterName) throws Exception {
        XComponentContext xComponentContext = BootstrapSocketConnector.bootstrap(oooExecutableFolder);
        XMultiComponentFactory xMultiComponentFactory = xComponentContext.getServiceManager();
        Object desktopService = xMultiComponentFactory.createInstanceWithContext("com.sun.star.frame.Desktop", xComponentContext);
        XComponentLoader xComponentLoader = UnoRuntime.queryInterface(XComponentLoader.class, desktopService);

        PropertyValue[] conversionProperties = new PropertyValue[2];
        conversionProperties[0] = new PropertyValue();
        conversionProperties[1] = new PropertyValue();

        conversionProperties[0].Name = "InputStream";
        conversionProperties[0].Value = input;
        conversionProperties[1].Name = "Hidden";
        conversionProperties[1].Value = Boolean.TRUE;

        XComponent document = xComponentLoader.loadComponentFromURL("private:stream", "_blank", 0, conversionProperties);

        conversionProperties[0].Name = "OutputStream";
        conversionProperties[0].Value = output;
        conversionProperties[1].Name = "FilterName";
        conversionProperties[1].Value = filterName;

        XStorable xstorable = UnoRuntime.queryInterface(XStorable.class, document);
        xstorable.storeToURL("private:stream", conversionProperties);

        XCloseable xclosable = UnoRuntime.queryInterface(XCloseable.class, document);
        xclosable.close(true);
    }


    public static void convert(String inputFilename, String outputFilename){
        try {
            // Create OOoInputStream
            InputStream inputFile = new BufferedInputStream(new FileInputStream(inputFilename));
            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            byte[] byteBuffer = new byte[4096];
            int byteBufferLength = 0;
            while ((byteBufferLength = inputFile.read(byteBuffer)) > 0) {
                bytes.write(byteBuffer, 0, byteBufferLength);
            }
            inputFile.close();
            OOoInputStream inputStream = new OOoInputStream(bytes.toByteArray());

            // Create OOoOutputStream
            OOoOutputStream outputStream = new OOoOutputStream();

            // Convert document to PDF
            OpenOfficeConverter converter = new OpenOfficeConverter();
            converter.convert(inputStream, outputStream, "writer_pdf_Export");

            // 输出流存成文件
            FileOutputStream outputFile = new FileOutputStream(outputFilename);
            outputFile.write(outputStream.toByteArray());
            outputFile.close();
        } catch (BootstrapException e) {
            System.err.println("Connection not available");
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("File error");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.exit(0);
    }


    public static void main(String[] args) {
        String inputFilename = "D:\\aa.docx";
        String outputFilename = "D:\\bb.pdf";

        convert(inputFilename, outputFilename);
    }
}

