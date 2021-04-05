package com.jamie.io;

import com.jamie.entity.Person;
import org.junit.Test;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class IOTest {
    @Test
    public void objectIO() {
        try {
            //字节输出流
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            //构建对象输出流
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(new Person("tom"));

            //字节输出流
            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            //读取对象输出流
            ObjectInputStream ois = new ObjectInputStream(bais);
            Person person = (Person) ois.readObject();

            System.out.println(person);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void charO() {
        Writer out = null;
        try {
            out = new FileWriter("a");
            out.write("hi");
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != out) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public void charIO2() {
        Reader in = null;
        Writer out = null;
        try {
            in = new FileReader("a");
            out = new FileWriter("b");
            char[] cbuf = new char[10];
            int len = -1;

            while ((len = in.read(cbuf)) != -1) {
                out.write(cbuf, 0, len);
            }
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != in) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //file output
    @Test
    public void fileOut() {
        OutputStream out = null;
        try {
            out = new FileOutputStream("a");
            out.write(new byte[]{65, 66, 67});
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != out) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //file input and output
    @Test
    public void fileIO() {
        InputStream in = null;
        OutputStream out = null;
        try {
            in = new FileInputStream("a");
            out = new FileOutputStream("b");
            /**
             * way1, read byte by byte
             */
            int b = 0;
            while ((b = in.read()) != -1) {
                out.write(b);
            }
            /**
             * way2, read by bytes[]
             */
//            byte[] b = new byte[20];
//            int len = -1;
//            while ((len = in.read(b)) != -1) {
//                out.write(b, 0, len);
//            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != out) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != in) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public void notBlockingServer() {
        try {
            ServerSocket ss = new ServerSocket(8081);

            while (true) {
                Socket socket = ss.accept();
                OutputStream out = socket.getOutputStream();
                out.write("hi".getBytes());

                out.close();
                socket.close();
            }

//            ss.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void notBlockingClient() {
        try {
            Socket socket = new Socket("127.0.0.1", 8081);
            InputStream in = socket.getInputStream();
            Scanner scan = new Scanner(in);

            while (scan.hasNext()) {
                System.out.println(scan.next());
            }

            in.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
