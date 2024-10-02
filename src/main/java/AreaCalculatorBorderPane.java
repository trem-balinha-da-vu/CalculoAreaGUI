package main.java;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class AreaCalculatorBorderPane extends Application {

    private TextField input1, input2;
    private ComboBox<String> shapeSelector;
    private Label resultLabel;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Calculadora de Área com BorderPane");

        // Criando o BorderPane
        BorderPane borderPane = new BorderPane();

        // Criando a interface principal com GridPane no centro
        GridPane centerGrid = new GridPane();
        centerGrid.setPadding(new Insets(10, 10, 10, 10));
        centerGrid.setVgap(10);
        centerGrid.setHgap(10);

        // ComboBox para selecionar a figura
        shapeSelector = new ComboBox<>();
        shapeSelector.getItems().addAll("Círculo", "Retângulo", "Triângulo");
        shapeSelector.setValue("Círculo");
        centerGrid.add(new Label("Selecione a figura:"), 0, 0);
        centerGrid.add(shapeSelector, 1, 0);

        // Campo de entrada para o primeiro parâmetro
        input1 = new TextField();
        centerGrid.add(new Label("Valor 1 (raio/base):"), 0, 1);
        centerGrid.add(input1, 1, 1);

        // Campo de entrada para o segundo parâmetro
        input2 = new TextField();
        centerGrid.add(new Label("Valor 2 (altura):"), 0, 2);
        centerGrid.add(input2, 1, 2);

        // Label para exibir o resultado
        resultLabel = new Label("Resultado: ");
        centerGrid.add(resultLabel, 1, 4);

        // Botão de calcular no rodapé
        Button calculateButton = new Button("Calcular Área");
        calculateButton.setOnAction(e -> calculateArea());

        // Adicionando o GridPane ao centro do BorderPane e o botão no rodapé
        borderPane.setCenter(centerGrid);
        borderPane.setBottom(calculateButton);
        BorderPane.setMargin(calculateButton, new Insets(10)); // Espaçamento para o botão

        // Criando a cena
        Scene scene = new Scene(borderPane, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void calculateArea() {
        String selectedShape = shapeSelector.getValue();
        double area = 0;

        try {
            if (!selectedShape.equals("Círculo")) {
                if (selectedShape.equals("Retângulo")) {
                    double width = Double.parseDouble(input1.getText());
                    double height = Double.parseDouble(input2.getText());
                    area = width * height;
                } else if (selectedShape.equals("Triângulo")) {
                    double base = Double.parseDouble(input1.getText());
                    double height = Double.parseDouble(input2.getText());
                    area = (base * height) / 2;
                }
            } else {
                double radius = Double.parseDouble(input1.getText());
                area = Math.PI * radius * radius;
            }
            resultLabel.setText("Resultado: " + area);
        } catch (NumberFormatException ex) {
            resultLabel.setText("Por favor, insira números válidos.");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
