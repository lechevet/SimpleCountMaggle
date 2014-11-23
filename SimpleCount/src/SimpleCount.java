
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Loic
 */
public class SimpleCount {
    
    private String buffer;
    private double actualNumber;
    private double bufferNumber;
    private double actualCalc;
    private double memoryCalc;
    private int    operator;
    private final DecimalFormat res;
    
    public SimpleCount()
    {
        buffer = "0";
        actualNumber = 0;
        bufferNumber = 0;
        actualCalc = 0;
        memoryCalc = 0;
        operator = 0;
        res = new DecimalFormat("#.########");
    }
    
    private String roundDecimal(double number)
    {
        try {
            actualNumber = res.parse(res.format(number)).doubleValue();
        } catch (ParseException ex) {
            Logger.getLogger(SimpleCount.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (res.format(number));
    }

    private double parseDouble(String buffer)
    {
        double d = 0;
        try {
            d = res.parse(buffer).doubleValue();
        } catch (ParseException ex) {
            Logger.getLogger(SimpleCount.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (d);
    }
    public void addNumeral(String numeral)
    {
        if (buffer.length() < 15)
        {
            if (buffer.contains(",") && buffer.length() - buffer.indexOf(',') > 8)
                return ;
            if ("ERROR".equals(buffer) || ("0".equals(buffer) && !".".equals(numeral)))
                buffer = numeral;
            else
            {
                if ("-0".equals(buffer))
                    buffer = "-";
                buffer += numeral;
            }
        
            actualNumber = parseDouble(buffer);
        }
    }
    
    public void clearBuffer()
    {
        buffer = "0";
        actualNumber = 0;
    }
    
    public void clearTotally()
    {
        buffer = "0";
        actualNumber = 0;
        bufferNumber = 0;
        actualCalc = 0;
        operator = 0;
    }
    public String getActualBuffer()
    {
        boolean b = Pattern.matches("[^0123456789,]", buffer);

        if (buffer.length() > 15 || b == true)
        {
            clearTotally();
            buffer = "ERROR";
        }
         return (buffer);
    }
    
    public void actualToBuffer()
    {
        bufferNumber = actualNumber;
        actualCalc = bufferNumber;
        clearBuffer();
    }
    
    public void invertSign()
    {
        actualNumber *= -1;
        if (buffer.contains("-") == false)
            buffer = "-" + buffer;
        else
            buffer = buffer.substring(1, buffer.length());
    }
    
    public void setOperator(int newOperator)
    {
        operator = newOperator;
    }
    
    public void setResult()
    {
        if (operator == 1)
            actualCalc += actualNumber;
        if (operator == 2)
            actualCalc -= actualNumber;
        if (operator == 3)
            actualCalc *= actualNumber;
        if (operator == 4)
            {
                if (actualNumber == 0)
                {
                    clearTotally();
                    buffer = "ERROR";
                    return ;
                }
                else
                    actualCalc /= actualNumber;
            }
        if (operator == 5)
        {
            if (actualNumber == 0)
                {
                    clearTotally();
                    buffer = "ERROR";
                    return ;
                }
                else
                    actualCalc %= actualNumber;
        } 
        actualNumber = actualCalc;
        buffer = roundDecimal(actualNumber);
    }
    
    public void clearAll()
    {
        bufferNumber = 0;
        operator = 0;        
    }
    
    public void inverseNumber()
    {
        if (actualNumber == 0)
        {
            clearTotally();
            buffer = "ERROR";
        }
        else
        {
            actualNumber = 1 / actualNumber;
        buffer = roundDecimal(actualNumber); 
        }
    }
    
    public void addToMemory()
    {

        memoryCalc += actualNumber;
    }
    
    public void subToMemory()
    {

        memoryCalc -= actualNumber;
    }

    public void getMemory()
    {
        actualNumber = memoryCalc;
        buffer = roundDecimal(actualNumber); 
    }
    
    public void clearMemory()
    {
        memoryCalc = 0;
    }
    
    public void cosinus()
    {
        actualNumber = Math.cos(Math.toRadians(actualNumber));
        buffer = roundDecimal(actualNumber); 
    }
    
    public void sinus()
    {
        actualNumber = Math.sin(Math.toRadians(actualNumber));
        buffer = roundDecimal(actualNumber); 
    }
    
    public void tangeante()
    {
        actualNumber = Math.tan(Math.toRadians(actualNumber));
        buffer = roundDecimal(actualNumber); 
    }
    
    public void square()
    {
        actualNumber *= actualNumber;
        buffer = roundDecimal(actualNumber); 
    }
    
    public void logarythm()
    {
        if (actualNumber <= 0)
        {
            clearTotally();
            buffer = "ERROR";
        }
        else
        {
            actualNumber = Math.log(actualNumber);
            buffer = roundDecimal(actualNumber);         
        }
    }
    
    public void exponential()
    {
        actualNumber = Math.exp(actualNumber);
        buffer = roundDecimal(actualNumber);
    }
    public void squareRootNumber()
    {
        if (actualNumber < 0)
        {
            clearTotally();
            buffer = "ERROR";
        }
        else
        {
            actualNumber = Math.sqrt(actualNumber);
            buffer = roundDecimal(actualNumber);        
        }
    }
    public int getOperator()
    {
        return (operator);
    }
}
