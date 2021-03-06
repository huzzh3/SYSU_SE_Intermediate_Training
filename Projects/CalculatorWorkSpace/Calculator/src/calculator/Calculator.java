package calculator;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Calculator {
	// Global variable
	final static int PLUS = 1;
	final static int MINUS = 2;
	final static int MUL = 3;
	final static int DIV = 4;
	static int operator = 1;
	static double res;
	
	public static double calculate(double num1, double num2) {
		if (operator == PLUS) {
			res = num1 + num2;
		}
		else if (operator == MINUS) {
			res = num1 - num2;
		}
		else if (operator == MUL) {
			res = num1 * num2;
		}
		else if (operator == DIV) {
			res = num1 / num2;
		}
		
		return res;
	}

	public static void main(String[] args) {

		
		// Set the num1TextField
		JTextField num1TextField = new JTextField(10);
		
		// Set the operatorLaebl
		JLabel operatorLabel = new JLabel("+");
		operatorLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		// Set the num2TextField
		JTextField num2TextField = new JTextField(10);
		
		// Set the assignLaebl
		JLabel assignLabel = new JLabel("=");
		assignLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		// Set the resultLabel
		JLabel resultLabel = new JLabel("res");
		resultLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		// Set the 4 operatorButtons
		JButton plusButton  = new JButton("+");
		JButton minusButton = new JButton("-");
		JButton mulButton   = new JButton("*");
		JButton divButton   = new JButton("/");
		
		// Set the Button Listener
		plusButton.addActionListener((ActionListener) new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				operatorLabel.setText("+");
				operator = PLUS;
			}
		});
		minusButton.addActionListener((ActionListener) new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				operatorLabel.setText("-");
				operator = MINUS;
			}
		});
		mulButton.addActionListener((ActionListener) new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				operatorLabel.setText("*");
				operator = MUL;
			}
		});
		divButton.addActionListener((ActionListener) new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				operatorLabel.setText("/");
				operator = DIV;
			}
		});
		
		// Set OK Button
		JButton OKButton    = new JButton("OK");
		
		OKButton.addActionListener((ActionListener) new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				double num1 = Double.valueOf(num1TextField.getText());
				double num2 = Double.valueOf(num2TextField.getText());
				
				res = calculate(num1, num2);
				
				resultLabel.setText(Double.toString(res));
			}
		});
		
		// Set the window
		JFrame calculatorWindow = new JFrame("Calculator");
		calculatorWindow.setBounds(100, 100, 400, 200);
		calculatorWindow.setVisible(true);
		calculatorWindow.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	
		// Set the panel
		JPanel calculatorPanel = new JPanel();
		calculatorPanel.setLayout(new GridLayout(2, 5, 5, 5));
		
		calculatorPanel.add(num1TextField);
		calculatorPanel.add(operatorLabel);
		calculatorPanel.add(num2TextField);
		calculatorPanel.add(assignLabel);
		calculatorPanel.add(resultLabel);
		calculatorPanel.add(plusButton);
		calculatorPanel.add(minusButton);
		calculatorPanel.add(mulButton);
		calculatorPanel.add(divButton);
		calculatorPanel.add(OKButton);
		
		// Make up
		calculatorWindow.add(calculatorPanel);
	}

}
