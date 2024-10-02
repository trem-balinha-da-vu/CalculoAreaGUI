package main.java;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class AreaCalculator extends Application {

    private TextField input1, input2;
    private ComboBox<String> shapeSelector;
    private Label resultLabel;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Calculadora de Área");

        // Layout principal com GridPane
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(10);
        grid.setHgap(10);

        // ComboBox para selecionar a figura
        shapeSelector = new ComboBox<>();
        shapeSelector.getItems().addAll("Círculo", "Retângulo", "Triângulo");
        shapeSelector.setValue("Círculo");
        grid.add(new Label("Selecione a figura:"), 0, 0);
        grid.add(shapeSelector, 1, 0);

        // Campo de entrada para o primeiro parâmetro
        input1 = new TextField();
        grid.add(new Label("Valor 1 (raio/base):"), 0, 1);
        grid.add(input1, 1, 1);

        // Campo de entrada para o segundo parâmetro (caso necessário)
        input2 = new TextField();
        grid.add(new Label("Valor 2 (altura):"), 0, 2);
        grid.add(input2, 1, 2);

        // Label para exibir o resultado
        resultLabel = new Label("Resultado: ");
        grid.add(resultLabel, 1, 4);

        // Botão de calcular
        Button calculateButton = new Button("Calcular Área");
        grid.add(calculateButton, 1, 3);

        // Ação do botão
        calculateButton.setOnAction(e -> calculateArea());

        // Exibir a interface
        Scene scene = new Scene(grid, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void calculateArea() {
        String selectedShape = shapeSelector.getValue();
        double area = 0;

        try {
            input2.setDisable(selectedShape.equals("Círculo")); // Desativa o campo 2 se for círculo

            switch (selectedShape) {
                case "Círculo" -> {
                    double radius = Double.parseDouble(input1.getText());
                    area = Math.PI * radius * radius;
                    input2.clear();
                }
                case "Retângulo" -> {
                    double width = Double.parseDouble(input1.getText());
                    double height = Double.parseDouble(input2.getText());
                    area = width * height;
                }
                case "Triângulo" -> {
                    double base = Double.parseDouble(input1.getText());
                    double height = Double.parseDouble(input2.getText());
                    area = (base * height) / 2;
                }
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
