package sample;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Controller implements Initializable {

    public TextField txtfield_port, txtfield_user, txtfield_ip, txtfield_message;

    public Button btn_send, btn_connect;

    public ListView lstview_messageContent;

    public ChoiceBox choiseBox_reciever;

    public Text txt_alert;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        basicSetting();
        //event
//        new GetUser(choiseBox_reciever).start();
        new CheckFieldThread(txtfield_port, txtfield_user, btn_connect).start();
        btn_connect.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> btn_connectHandle());
//        btn_send.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> btn_sendHandle());
        choiseBox_reciever.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> loadOnlineUser());

    }

    public void basicSetting() {
        txtfield_ip.setText("Localhost");
        txtfield_ip.setDisable(true);
        btn_send.setDisable(true);
        txt_alert.setText("");
    }

    public static final String FAIL = "FAIL";
    public static final String SUCCESS = "SUCCESS";

    public void btn_connectHandle() {
        new SendMessThread(Integer.parseInt(txtfield_port.getText())
                , txtfield_user.getText()
                , choiseBox_reciever
                , txtfield_message
                , txt_alert
                , btn_send
                , lstview_messageContent).start();
    }

    public void btn_sendHandle() {

    }

    public void yahh(int port, String name, String reciever) {
        try {
            Socket socket = new Socket("localhost", 3001);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            System.out.println(in.readLine());
            //set username
            String result = FAIL;
            while (result.equals(FAIL)) {
//                System.out.print("Your name: ");
                out.println(name);
                result = in.readLine();
//                System.out.println(result.equals(SUCCESS) ? "Login Success" : "Name already exists!");
            }
            //set reviever
            System.out.println(in.readLine());
            while (true) {
                System.out.print("Name receiver: ");
                String nameReceiver = (new Scanner(System.in)).nextLine();
                if (nameReceiver.trim().equalsIgnoreCase(name.trim())) {
                    System.out.println("You cant sent massage for yourseft");
                    continue;
                }
                out.println(nameReceiver);
                String res = in.readLine();
                System.out.println(res);
                if (res.equals(FAIL)) {
                    System.out.println("Not find out user or user not online");
                } else {
                    System.out.println("Connect to each another successful!");
                    break;
                }
            }
            in.readLine();
            (new Thread(() -> {
                try {
                    while (true) {
                        System.out.println(in.readLine());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            })).start();
            do {
                System.out.print("sent: ");
                String zxc = (new Scanner(System.in)).nextLine();
                out.println(zxc);
            } while (true);
        } catch (IOException e) {
            System.out.println(e);
//            e.printStackTrace();
        }
    }

    public void loadOnlineUser() {
        new Thread(() -> {
            try {
                Socket socket = new Socket("localhost", 3003);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String receivers = in.readLine();
//            Object preValue = choiseBox_reciever.getSelectionModel().getSelectedItem();
                choiseBox_reciever.getItems().clear();
                choiseBox_reciever.getItems().addAll(receivers.substring(1, receivers.length() - 1).split(","));
//                choiseBox_reciever.setValue(preValue);

                socket.close();
                in.close();
            } catch (IOException e) {
            }
        }).start();
    }
}

class CheckFieldThread extends Thread {
    TextField txtfield_A, txtfield_B;
    Button btn;

    public CheckFieldThread(TextField txtfield_A, TextField txtfield_B, Button btn) {
        this.txtfield_A = txtfield_A;
        this.txtfield_B = txtfield_B;
        this.btn = btn;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(500);
                if (txtfield_A.getText().isEmpty() || txtfield_B.getText().isEmpty()) {
                    synchronized (btn) {
                        btn.setDisable(true);
                    }
                } else {
                    synchronized (btn) {
                        btn.setDisable(false);
                    }
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class SendMessThread extends Thread {

    int port;
    String nameUser;
    TextField txtField_mess;
    ChoiceBox choiseBox_receiver;
    Button buttonSendAction;
    Text alertArea;
    BufferedReader in;
    PrintWriter out;
    ListView listView_messContain;

    public SendMessThread(
            int port
            , String name
            , ChoiceBox choiseBox_receiver
            , TextField txtField_mess
            , Text alertArea
            , Button buttonSendAction
            , ListView listView_messContain) {
        this.port = port;
        this.nameUser = name;
        this.choiseBox_receiver = choiseBox_receiver;
        this.txtField_mess = txtField_mess;
        this.alertArea = alertArea;
        this.buttonSendAction = buttonSendAction;
        this.listView_messContain = listView_messContain;
    }

    @Override
    public void run() {
        try {
            System.out.println("à lố");
            Socket socket = new Socket("localhost", this.port);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
            System.out.println(nameUser.trim() + "" + choiseBox_receiver.getSelectionModel().getSelectedItem());
//            System.out.println(nameUser + " " + in.readLine());
            if (choiseBox_receiver.getSelectionModel().getSelectedItem() == null
                    || String.valueOf(choiseBox_receiver.getSelectionModel().getSelectedItem()).isEmpty()) {
                out.println(Controller.FAIL);
                alertArea.setText("quên chọn người nhận kìa");
                alertArea.setFill(Color.RED);
                buttonSendAction.setDisable(true);
                socket.close();
                in.close();
                out.close();
                this.stop();
                return;
            } else if (in.readLine().contains(nameUser.trim())
                    && nameUser.trim().equals((choiseBox_receiver.getSelectionModel().getSelectedItem() + "").trim())) {
                out.println(Controller.FAIL);
                alertArea.setText("trùng username kìa");
                alertArea.setFill(Color.RED);
                buttonSendAction.setDisable(true);
                socket.close();
                in.close();
                out.close();
                this.stop();
                return;
            }
            //success
            alertArea.setText("Connect success");
            alertArea.setFill(Color.GREEN);
            //sent name and reciever to server
            out.println(nameUser);
            out.println(String.valueOf(choiseBox_receiver.getSelectionModel().getSelectedItem()));
            //post
            buttonSendAction.setDisable(false);
            buttonSendAction.addEventHandler(MouseEvent.MOUSE_CLICKED,
                    event -> {
                        if (!txtField_mess.getText().isEmpty()) {
                            out.println(txtField_mess.getText());
                            synchronized (listView_messContain) {
                                listView_messContain.getItems().add("Me: " + txtField_mess.getText());
                            }
                        }
                    });
            //get
            while (true) {
                listView_messContain.getItems().add(in.readLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    synchronized public void sendMessage() {

    }

}