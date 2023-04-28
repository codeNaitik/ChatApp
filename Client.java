import java.net.*;

import java.io.*;

class Client {

    ServerSocket server;
    Socket socket;

    // BufferedReader for reading
    BufferedReader br;

    // for writing
    PrintWriter out;

    public Client() {
        try {
            System.out.println("req to server");
            socket = new Socket("127.0.0.1", 7777);
            System.out.println("connetion done");

            // input lese read karse
            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // write
            out = new PrintWriter(socket.getOutputStream());

            startReading();
            startWriting();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // forreading
    public void startReading() {

        // thread -> read karse data aptu rehse
        Runnable r1 = () -> {
            System.out.println("reader strated");
            while (true) {

                try {

                    String msg = br.readLine();
                    if (msg.equals("exit")) {
                        System.out.println("server therminated");
                    }

                    System.out.println("client : " + msg);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        };

        new Thread(r1).start();

    }

    // for writing
    public void startWriting() {
        // thread -> data lese ane sent karse client ne
        System.out.println("writing start");
        Runnable r2 = () -> {
            while (true) {
                try {

                    // to take content

                    BufferedReader br1 = new BufferedReader(new InputStreamReader(System.in));

                    String content = br1.readLine();

                    out.println(content);
                    // The flush() method of OutputStream class is used to flush the content of the
                    // buffer to the output stream
                    out.flush();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        };

        new Thread(r2).start();
    }

    public static void main(String[] args) {
        System.out.println("this is client");
        // calling construtor
        new Client();
    }
}
