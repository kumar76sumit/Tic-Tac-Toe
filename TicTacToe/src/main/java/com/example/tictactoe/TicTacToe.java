package com.example.tictactoe;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class TicTacToe extends Application {
    private Button[][] buttons=new Button[3][3];
    private Label playerXScoreLabel,playerOScoreLabel;
    private boolean playerOneTurn=true;
    private int playerXScore=0,playerOScore=0;
    private BorderPane createContent()
    {
        BorderPane root=new BorderPane();

        Label title=new Label("▀█▀ █ █▀▀   ▀█▀ ▄▀█ █▀▀   ▀█▀ █▀█ █▀▀\n" +
                "░█░ █ █▄▄   ░█░ █▀█ █▄▄   ░█░ █▄█ ██▄\n\n\n\n");
        title.setStyle("-fx-font-size : 10pt; -fx-font-weight : bold;");
        root.setTop(title);
        BorderPane.setAlignment(title, Pos.CENTER);
        root.setPadding(new Insets(30));

        Image image1=new Image("C:\\Users\\Sumit\\IdeaProjects\\TicTacToe\\src\\main\\tictactoe.gif");
        ImageView ticTacToe=new ImageView(image1);
        ticTacToe.setTranslateX(115);
        ticTacToe.setTranslateY(10);
        ticTacToe.setFitHeight(100);
        ticTacToe.setFitWidth(300);

        Image image=new Image("C:\\Users\\Sumit\\IdeaProjects\\TicTacToe\\src\\main\\pexels-dlkr-5676478.jpg");
        ImageView bg=new ImageView(image);
        bg.setFitHeight(1000);
        bg.setFitWidth(1000);

//        Image image2=new Image("C:\\Users\\Sumit\\IdeaProjects\\TicTacToe\\src\\main\\83412-200.png");
//        ImageView twoPlayer=new ImageView(image2);
//        twoPlayer.setFitHeight(80);
//        twoPlayer.setFitWidth(80);
//        twoPlayer.setTranslateX(500);
//        twoPlayer.setTranslateY(510);

        root.getChildren().addAll(bg,ticTacToe);

        HBox scoreBoard=new HBox(20);
//        scoreBoard.setAlignment(Pos.CENTER);
        scoreBoard.setTranslateX(22);
        playerXScoreLabel=new Label("Player ✘ : 0  -");
        playerXScoreLabel.setStyle("-fx-font-size : 25pt; -fx-font-weight : bold");
        playerXScoreLabel.setTextFill(Color.BLACK);
        playerOScoreLabel=new Label("Player \uD835\uDCDE : 0");
        playerOScoreLabel.setStyle("-fx-font-size : 25pt; -fx-font-weight : bold");
        playerOScoreLabel.setTextFill(Color.BLACK);
        scoreBoard.getChildren().addAll(playerXScoreLabel,playerOScoreLabel);
        root.setBottom(scoreBoard);

        GridPane gridPane=new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
//        gridPane.setStyle("-fx-background-color: red");
//        gridPane.setAlignment(Pos.CENTER);
        for (int i = 0; i <3; i++) {
            for (int j = 0; j < 3; j++) {
                Button button=new Button();
                button.setPrefSize(150,150);
                button.setStyle("-fx-font-size : 45pt; -fx-font-weight : bold");
                button.setOnAction(Event -> buttonClicked(button));
                buttons[i][j]=button;
                gridPane.add(button,j,i);
            }
        }
        root.setCenter(gridPane);
        BorderPane.setAlignment(gridPane,Pos.CENTER);

        return root;
    }
    private void buttonClicked(Button button)
    {
        if(button.getText().equals(""))
        {
            if(playerOneTurn)
            {
                button.setText("✘");
                button.setTextFill(Color.web("#00FFFF"));
            }
            else {
                button.setText("\uD835\uDCDE");
                button.setTextFill(Color.web("#00FF00"));
            }
            playerOneTurn=!playerOneTurn;
            checkWinner();
        }
        return;
    }

    private void checkWinner()
    {
        for (int row = 0; row < 3; row++) {
            if(buttons[row][0].getText().equals(buttons[row][1].getText())
                    && buttons[row][1].getText().equals(buttons[row][2].getText())
                    && !buttons[row][0].getText().isEmpty())
            {
                String winner=buttons[row][0].getText();
                showWinnerDialog(winner);
                scoreBoard(winner);
                resetBoard();
                return;
            }
        }

        for (int col = 0; col < 3; col++) {
            if(buttons[0][col].getText().equals(buttons[1][col].getText())
                    && buttons[1][col].getText().equals(buttons[2][col].getText())
                    && !buttons[0][col].getText().isEmpty())
            {
                String winner=buttons[0][col].getText();
                showWinnerDialog(winner);
                scoreBoard(winner);
                resetBoard();
                return;
            }
        }

        if(buttons[0][0].getText().equals(buttons[1][1].getText())
                && buttons[1][1].getText().equals(buttons[2][2].getText())
                && !buttons[0][0].getText().isEmpty())
        {
            String winner=buttons[0][0].getText();
            showWinnerDialog(winner);
            scoreBoard(winner);
            resetBoard();
            return;
        }

        else if(buttons[0][2].getText().equals(buttons[1][1].getText())
                && buttons[1][1].getText().equals(buttons[2][0].getText())
                && !buttons[0][2].getText().isEmpty())
        {
            String winner=buttons[0][2].getText();
            showWinnerDialog(winner);
            scoreBoard(winner);
            resetBoard();
            return;
        }
        boolean tie = true;
        for(Button[] row:buttons)
        {
            for(Button button:row)
            {
                if(button.getText().isEmpty())
                {
                    tie=false;
                    break;
                }
            }
        }

        if(tie)
        {
            showTieDialog();
            resetBoard();
        }
    }
    private void showWinnerDialog(String winner)
    {
        Alert alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Winner");
        alert.setContentText("Congratulations " + winner + " ! You Won.");
        alert.setHeaderText("");
        alert.showAndWait();
    }

    private void showTieDialog()
    {
        Alert alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Tie");
        alert.setContentText("Game Over! It's a Tie");
        alert.setHeaderText("");
        alert.showAndWait();
    }

    private void scoreBoard(String winner)
    {
        if(winner.equals("✘"))
        {
            playerXScore++;
            playerXScoreLabel.setText("Player ✘ : " + playerXScore + "  -");
        }
        else {
            playerOScore++;
            playerOScoreLabel.setText("Player \uD835\uDCDE : " + playerOScore);
        }
    }

    private void resetBoard()
    {
        for(Button[] row:buttons)
        {
            for(Button button:row)
            {
                button.setText("");
            }
        }
    }
    @Override
    public void start(Stage stage) throws IOException {
        Scene scene = new Scene(createContent());
        stage.setTitle("Tic Tac Toe");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}