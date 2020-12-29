package sample;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
//import javafx.embed.swing.;

public class Main extends Application {

    private static final String TAG = Main.class.getName();


    public static void main(String[] args) {
        launch(args);
    }

    public static void mLog(String content) {
        System.out.println(content);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 580, 520));
        primaryStage.show();
    }

}

//    Group root = new Group();
//    ObservableList<Node> observableList = root.getChildren();
//
//    Scene scene = new Scene(root, 750, 560);
//        scene.setFill(Color.WHEAT);
//
////        test(observableList);
//
//                primaryStage.setTitle("hello there");
//                primaryStage.setScene(scene);
////        primaryStage.setFullScreen(true);
////        primaryStage.setOpacity(0.5);
//                primaryStage.show();
//    public static void test(ObservableList observableList) {
//
//        Line line = new Line();
//        line.setStartX(100);
//        line.setStartY(150);
//        line.setEndX(500);
//        line.setEndY(450);
//        observableList.add(line);
//
//        Text text = new Text();
//        text.setFont(new Font(16));
//        text.setX(50);
//        text.setY(120);
//        text.setText("Welcome to Tutorialspoint");
//        observableList.add(text);
//
//
//        Path path = new Path();
//        MoveTo moveto = new MoveTo(108, 71);
//        QuadCurveTo quadCurveTo = new QuadCurveTo(0, 0, 30, 45);
//        path.getElements().add(moveto);
//        path.getElements().addAll(quadCurveTo);
//        observableList.add(path);
//
//        Button button = new Button();
//        button.setText("nhấp mạnh lên!");
//
//        EventHandler<MouseEvent> mouseEventEventHandler = new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent event) {
//                mLog("đây là mouse event " + event.getEventType());
////                mLog(event.getSource().toString());
////                mLog(event.getTarget().toString());
//                mLog("x:" + event.getSceneX() + " y:" + event.getSceneY());
//                mLog("x:" + event.getX() + " y:" + event.getY());
//                Text text1 = (Text) event.getTarget();
//                text1.setText("thay đổi nào");
//                button.setText("à há");
//            }
//        };
////        text.addEventFilter(MouseEvent.MOUSE_CLICKED, mouseEventEventHandler);
//        text.addEventHandler(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
//            @Override
//            public void handle(KeyEvent event) {
//                mLog(event.getCharacter());
//            }
//        });
////        mLog("à");
//
//        button.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//                mLog("à sao củng dc");
//            }
//        });
//
//        observableList.add(button);
//
//        observableList.clear();
//        Box box = new Box();
//        box.setWidth(150);
//        box.setHeight(150);
//        box.setDepth(100);
//
//        box.setTranslateX(250);
//        box.setTranslateY(150);
//        box.setTranslateZ(50);
//
//        PhongMaterial phongMaterial = new PhongMaterial();
//        phongMaterial.setDiffuseColor(Color.DARKSLATEBLUE);
//        box.setMaterial(phongMaterial);
//
//        RotateTransition rotateTransition = new RotateTransition();
//        rotateTransition.setDuration(Duration.millis(2000));
//        rotateTransition.setNode(box);
//        rotateTransition.setAxis(Rotate.Y_AXIS);
//        rotateTransition.setByAngle(360);
//        rotateTransition.setCycleCount(50);
//        rotateTransition.setAutoReverse(false);
//        rotateTransition.play();
//        observableList.add(box);
//
//        Image image = new Image("/img/z-w-gu-9.jpg");
//        ImageView imageView = new ImageView(image);
//        imageView.setX(100);
//        imageView.setY(70);
//        imageView.setFitWidth(700);
//        imageView.setFitHeight(700);
//        imageView.setPreserveRatio(true);
//
//        Bloom bloom = new Bloom();
////        glow.setLevel(0.5);
//        bloom.setThreshold(100);
//
//        imageView.setEffect(bloom);
//
//        observableList.add(imageView);
//
//    }