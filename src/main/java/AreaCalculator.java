package main.java;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AreaCalculator extends JFrame implements ActionListener {
    private JTextField input1, input2;
    private JButton calculateButton;
    private JComboBox<String> shapeSelector;
    private JLabel resultLabel;

    public AreaCalculator() {
        setTitle("Calculadora de Área");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 2, 10, 10)); // Grid layout com espaçamento

        // ComboBox para selecionar a figura
        String[] shapes = {"Círculo", "Retângulo", "Triângulo"};
        shapeSelector = new JComboBox<>(shapes);
        add(new JLabel("Selecione a figura:")); // Label acima da ComboBox
        add(shapeSelector);

        // Campo de entrada para o primeiro parâmetro
        input1 = new JTextField(10);
        add(new JLabel("Valor 1 (raio/base):")); // Label acima do campo
        add(input1);

        // Campo de entrada para o segundo parâmetro (caso necessário)
        input2 = new JTextField(10);
        add(new JLabel("Valor 2 (altura):")); // Label acima do campo
        add(input2);

        // Botão de calcular
        calculateButton = new JButton("Calcular Área");
        calculateButton.addActionListener(this);
        add(calculateButton);

        // Label para exibir o resultado
        resultLabel = new JLabel("Resultado: ");
        add(resultLabel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String selectedShape = (String) shapeSelector.getSelectedItem();
        double area = 0;

        try {
            // Limpar o campo 2 para Círculo
            input2.setEnabled(selectedShape.equals("Retângulo") || selectedShape.equals("Triângulo"));

            if (selectedShape.equals("Círculo")) {
                double radius = Double.parseDouble(input1.getText());
                area = Math.PI * radius * radius;
                input2.setText(""); // Limpar o campo 2
            } else if (selectedShape.equals("Retângulo")) {
                double width = Double.parseDouble(input1.getText());
                double height = Double.parseDouble(input2.getText());
                area = width * height;
            } else if (selectedShape.equals("Triângulo")) {
                double base = Double.parseDouble(input1.getText());
                double height = Double.parseDouble(input2.getText());
                area = (base * height) / 2;
            }

            resultLabel.setText("Resultado: " + area);
        } catch (NumberFormatException ex) {
            resultLabel.setText("Por favor, insira números válidos.");
        }
    }

    public static void main(String[] args) {
        AreaCalculator calculator = new AreaCalculator();
        calculator.setVisible(true);
    }
}
