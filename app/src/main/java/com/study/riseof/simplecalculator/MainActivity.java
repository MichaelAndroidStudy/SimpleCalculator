package com.study.riseof.simplecalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    enum Operand{
        FIRST,
        SECOND
    }

    enum Operation{
        ADDITION,
        SUBTRACTION,
        MULTIPLICATION,
        DIVISION,
        NULL
    }

    private final int BUTTONS_QUANTITY = 18;
    private final Double ZERO_DOUBLE=0d;
    private final String ZERO ="0";
    private final String ONE="1";
    private final String TWO ="2";
    private final String THREE ="3";
    private final String FOUR ="4";
    private final String FIVE ="5";
    private final String SIX ="6";
    private final String SEVEN ="7";
    private final String EIGHT ="8";
    private final String NINE="9";
    private final String EMPTY_STRING="";
    private final String MINUS="-";
    private final String PLUS="+";
    private final String ASTERISK="*";
    private final String SLASH="/";
    private final String EQUAL_SIGN="=";
    private final String POINT = ".";

    private  Button buttonsArr[]=new Button[BUTTONS_QUANTITY];
    private  Button button0;
    private  Button button1;
    private  Button button2;
    private  Button button3;
    private  Button button4;
    private  Button button5;
    private  Button button6;
    private  Button button7;
    private  Button button8;
    private  Button button9;
    private  Button buttonAdditional;
    private  Button buttonC;
    private  Button buttonChangeSign;
    private  Button buttonComma;
    private  Button buttonDivide;
    private  Button buttonMultiplication;
    private  Button buttonResult;
    private  Button buttonSubtraction;
    private TextView operationSign;
    private TextView resultSign;
    private TextView firstNumber;
    private TextView secondNumber;
    private TextView resultNumber;

    private Operand curentOperand = Operand.FIRST;
    private Operation curentOperation =Operation.NULL;
    private String firstOperand=EMPTY_STRING;
    private String secondOperand=EMPTY_STRING;
    private String calculationResult=EMPTY_STRING;
    private boolean needToUpdateText=false;
    private boolean operationSelected=false;
    private boolean resultIsExist=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initiateButtons();
        initiateTextFields();
        setButtonsOnClickListeners();
    }

    @Override
    public void onClick(View view) {
        handleClicks(view);
    }

    private void initiateButtons(){
        // как сделать это автоматически не додумался
        buttonsArr[0] = button0 = (Button) findViewById(R.id.button0);
        buttonsArr[1] = button1 = (Button) findViewById(R.id.button1);
        buttonsArr[2] = button2 = (Button) findViewById(R.id.button2);
        buttonsArr[3] = button3 = (Button) findViewById(R.id.button3);
        buttonsArr[4] = button4 = (Button) findViewById(R.id.button4);
        buttonsArr[5] = button5 = (Button) findViewById(R.id.button5);
        buttonsArr[6] = button6 = (Button) findViewById(R.id.button6);
        buttonsArr[7] = button7 = (Button) findViewById(R.id.button7);
        buttonsArr[8] = button8 = (Button) findViewById(R.id.button8);
        buttonsArr[9] = button9 = (Button) findViewById(R.id.button9);
        buttonsArr[10] = buttonAdditional = (Button) findViewById(R.id.buttonAdditional);
        buttonsArr[11] = buttonC = (Button) findViewById(R.id.buttonC);
        buttonsArr[12] = buttonChangeSign = (Button) findViewById(R.id.buttonChangeSign);
        buttonsArr[13] = buttonComma = (Button) findViewById(R.id.buttonComma);
        buttonsArr[14] = buttonDivide = (Button) findViewById(R.id.buttonDivide);
        buttonsArr[15] = buttonMultiplication = (Button) findViewById(R.id.buttonMultiplication);
        buttonsArr[16] = buttonResult = (Button) findViewById(R.id.buttonResult);
        buttonsArr[17] = buttonSubtraction = (Button) findViewById(R.id.buttonSubtraction);
    }

    private void setButtonsOnClickListeners(){
        for (int i=0;i<BUTTONS_QUANTITY;i++){
            buttonsArr[i].setOnClickListener(this);
        }
    }

    private void initiateTextFields(){
        operationSign = (TextView) findViewById(R.id.operationSign);
        resultSign = (TextView) findViewById(R.id.resultSign);
        firstNumber = (TextView) findViewById(R.id.firstNumber);
        secondNumber = (TextView) findViewById(R.id.secondNumber);
        resultNumber = (TextView) findViewById(R.id.resultNumber);
        operationSign.setText(EMPTY_STRING);
        resultSign.setText(EMPTY_STRING);
    }

    private void handleClicks(View view){
        needToUpdateText=false;
        if(resultIsExist){
            clean();
        }
        switch (view.getId()) {
            case R.id.button0:
                addDigitToOperand(ZERO);
                break;
            case R.id.button1:
                addDigitToOperand(ONE);
                break;
            case R.id.button2:
                addDigitToOperand(TWO);
                break;
            case R.id.button3:
                addDigitToOperand(THREE);
                break;
            case R.id.button4:
                addDigitToOperand(FOUR);
                break;
            case R.id.button5:
                addDigitToOperand(FIVE);
                break;
            case R.id.button6:
                addDigitToOperand(SIX);
                break;
            case R.id.button7:
                addDigitToOperand(SEVEN);
                break;
            case R.id.button8:
                addDigitToOperand(EIGHT);
                break;
            case R.id.button9:
                addDigitToOperand(NINE);
                break;
            case R.id.buttonChangeSign:
                Toast.makeText(this, "Нажатие \"+/-\" пока не обрабатывается",Toast.LENGTH_SHORT).show();
                break;
            case R.id.buttonComma:
                Toast.makeText(this, "Нажатие \",\" пока не обрабатывается",Toast.LENGTH_SHORT).show();
                break;
                default:
                    break;
        }
        if(needToUpdateText){
            showNumbers();
            return;
        }

        if(!operationSelected) {
            switch (view.getId()) {
                case R.id.buttonAdditional:
                    curentOperation = Operation.ADDITION;
                    selectOperationAndShowingSign(PLUS, true);
                    break;
                case R.id.buttonSubtraction:
                    curentOperation = Operation.SUBTRACTION;
                    selectOperationAndShowingSign(MINUS, true);
                    break;
                case R.id.buttonDivide:
                    curentOperation = Operation.DIVISION;
                    selectOperationAndShowingSign(SLASH, true);
                    break;
                case R.id.buttonMultiplication:
                    curentOperation = Operation.MULTIPLICATION;
                    selectOperationAndShowingSign(ASTERISK, true);
                    break;
                default:
                    break;
            }
        }
        switch (view.getId()) {
            case R.id.buttonC:
                clean();
                break;
            case R.id.buttonResult:
                showEqualsSign(true);
                calculationResult=calculation();
                resultNumber.setText(calculationResult);
                resultIsExist=true;
                break;
            default:
                break;
        }
    }

    private void addDigitToOperand(String digit){
        needToUpdateText=true;
        switch (curentOperand){
            case FIRST:
                if(firstOperand.equals(ZERO)){
                    firstOperand= digit;
                }
                else {
                    firstOperand= firstOperand+digit;
                }
                break;
            case SECOND:
                if(secondOperand.equals(ZERO)){
                    secondOperand= digit;
                }
                else {
                    secondOperand= secondOperand+digit;
                }
                break;
            default:
                break;
        }
    }

    private void addPointToOperand(){
        needToUpdateText=true;
        switch (curentOperand){
            case FIRST:
                if(firstOperand.equals(EMPTY_STRING)){
                    firstOperand= ZERO+POINT;
                }
                else {
                    firstOperand= firstOperand+POINT;
                }
                break;
            case SECOND:
                if(secondOperand.equals(EMPTY_STRING)){
                    secondOperand= ZERO+POINT;
                }
                else {
                    secondOperand= secondOperand+POINT;
                }
                break;
            default:
                break;
        }
    }

    private void changeOperandSign(){
        needToUpdateText=true;
        Double operand=0d;
        switch (curentOperand){
            case FIRST:
                operand=Double.valueOf(firstOperand);
                operand*=-1;
                firstOperand= operand.toString();
                break;
            case SECOND:
                operand=Double.valueOf(secondOperand);
                operand*=-1;
                secondOperand= operand.toString();
                break;
            default:
                break;
        }
    }

    private void showNumbers() {
        switch (curentOperand) {
            case FIRST:
                firstNumber.setText(firstOperand);
                break;
            case SECOND:
                secondNumber.setText(secondOperand);
                break;
            default:
                break;
        }
    }

    private void selectOperationAndShowingSign(String sign, boolean isSelect){
        if(isSelect){
            operationSign.setText(sign);
            operationSelected=true;
            if(firstOperand.equals(EMPTY_STRING)){
                firstOperand=ZERO;
                showNumbers();
            }
            curentOperand=Operand.SECOND;
        }
        else {
            resultSign.setText(EMPTY_STRING);
            operationSelected=false;
            curentOperand=Operand.FIRST;
        }
    }

    private void clean(){
        curentOperand = Operand.FIRST;
        curentOperation =Operation.NULL;
        firstOperand=EMPTY_STRING;
        secondOperand=EMPTY_STRING;
        calculationResult=EMPTY_STRING;
        needToUpdateText=false;
        operationSelected=false;
        resultIsExist=false;
        operationSign.setText(EMPTY_STRING);
        resultSign.setText(EMPTY_STRING);
        firstNumber.setText(EMPTY_STRING);
        secondNumber.setText(EMPTY_STRING);
        resultNumber.setText(EMPTY_STRING);
    }

    private void showEqualsSign(boolean isShowing){
        if(isShowing){
            resultSign.setText(EQUAL_SIGN);
        }
        else {
            resultSign.setText(EMPTY_STRING);
        }
    }

    private String calculation() {
        Double operandA=0d;
        Double operandB=0d;
        Double result=0d;

        try {
            if(curentOperation==Operation.NULL||firstOperand.equals(EMPTY_STRING)){
                resultIsExist=false;
                return EMPTY_STRING;
            }
            operandA=Double.valueOf(firstOperand);
            if(secondOperand.equals(EMPTY_STRING)){
                operandB=ZERO_DOUBLE;
            }
            else {
                operandB=Double.valueOf(secondOperand);
            }
            resultIsExist=true;
            curentOperand=Operand.FIRST;

            switch (curentOperation) {
                case ADDITION:
                    result = operandA + operandB;
                    break;
                case DIVISION:
                    if(operandB.equals(ZERO_DOUBLE)){
                        throw new Exception("Деление на ноль");
                    }
                    result = operandA / operandB;
                    break;
                case SUBTRACTION:
                    result = operandA - operandB;
                    break;
                case MULTIPLICATION:
                    result = operandA * operandB;
                    break;
                default:
                    resultIsExist = false;
                    break;
            }
        }
        catch (ArithmeticException ex){
            Toast.makeText(this, ex.getMessage()+" ArithmeticException",Toast.LENGTH_SHORT).show();
            clean();
            return EMPTY_STRING;
        }
        catch (NumberFormatException ex){
            Toast.makeText(this, ex.getMessage()+" NumberFormatException",Toast.LENGTH_SHORT).show();
            clean();
            return EMPTY_STRING;
        }
        catch (Exception ex) {
            Toast.makeText(this, ex.getMessage()+" Exception",Toast.LENGTH_SHORT).show();
            clean();
            return EMPTY_STRING;
        }
        return result.toString();
    }
}
