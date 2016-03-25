

//StudentID: your id
//Lecture: your lecture
//Lab Section: your lab section
//Description:


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;

public class Graph extends JPanel
{
String input;

Color color;
public Graph()
{
setBackground(color.white);
setPreferredSize(new Dimension(200,200));
repaint();
}

public void paintComponent(Graphics page)
{
super.paintComponent(page);
page.setColor(color.black);
page.drawLine(100,200,100,0);
page.drawLine(0,100,200,100);
for(int i = 0; i <=200; i = i + 10){page.drawLine(95,i,105,i);page.drawLine(i,95,i,105);} 
page.setColor(color);
}
public void graphs(int x1, int x2, int y1, int y2, Color color1, String t2)
{
paintComponent(this.getGraphics());
	
color = color1;


for(int i = 0; i<=2000;i++)
{
double translation = (i/100.0)-10;
double v = number(Operation(t2,translation),translation)*(-10)+100;
this.getGraphics().drawLine(i/10,(int)v,i/10,(int)v);
}
}

public double number(String function,double variable)           
{
try{
double now=0;
char sine = 's';
char tangent ='t';                                
char cosi = 'c';
char varia = 'x';
char current = function.charAt(0);
if(current == '-') return (-1) * number(function.substring(1,function.length()),variable);
if(current == '-'||current=='l'||current==cosi||current==sine||current==tangent||current==varia)
{
if(current == 'l' && function.charAt(3) !='x')return Math.log(number(function.substring(3,function.length()-1),variable));
if(current == 'l' && function.charAt(3)=='x')now = Math.log(variable);
if(current == cosi && function.charAt(4) !='x')return Math.cos(number(function.substring(4,function.length()-1),variable));
if(current == cosi && function.charAt(4)=='x')now = Math.cos(variable);
if(current == sine && function.charAt(4) !='x')return Math.sin(number(function.substring(4,function.length()-1),variable));
if(current == sine && function.charAt(4)=='x')now = Math.sin(variable);
if(current == tangent && function.charAt(4) !='x')return Math.tan(number(function.substring(4,function.length()-1),variable));
if(current == tangent && function.charAt(4)=='x')now = Math.tan(variable);
if(current == varia)now = variable;
}
else now = Double.parseDouble(String.valueOf(function));
return now;
}

catch(Exception exc){return 0;}


}      

public String Operation(String inputString,double position)
{
if(inputString.charAt(0)=='-')inputString = "0+" + inputString;
JOptionPane msg = new JOptionPane();
DecimalFormat dcm = new DecimalFormat("0.#####");
char plus = '+';
char subtract = '-';
char multiply = '*';
char divide = '/';
char exponent = '^';
char operators[] = {exponent,multiply,divide,plus,subtract};

if(inputString.indexOf("[")>=0)
for(int i = inputString.length()-1; i>=0;i--)
{
int start = 0;
int end = 0;
if(inputString.charAt(i)=='['){start = i;
end = inputString.indexOf("]",start);
StringBuffer buff = new StringBuffer(inputString);
buff.delete(start,end+1);
String replace = Operation(inputString.substring(start+1,end),position);
buff.insert(start," " + replace + "$");

inputString = buff.toString();i=inputString.length()-1;}

}

if(inputString.indexOf("(")>=0)
for(int i =inputString.length()-1; i>=0;i--)
{
int start = 0;
int end = 0;
if(inputString.charAt(i)=='('){start = i;
end = inputString.indexOf(")",i);
StringBuffer buff = new StringBuffer(inputString);
buff.delete(start,end+1);
buff.insert(start,Operation(inputString.substring(start+1,end),position));
inputString = buff.toString();i=inputString.length()-1;}
}

for(int count = 0; count<=4;count++)
{

String t1 = new String();
String t2 = new String();
//if(inputString.indexOf(operators[count]) > 0)
for(int i = 1; i<inputString.length();i++)
{
	if(inputString.charAt(i) == operators[count])
	{	
		//msg.showMessageDialog(this,inputString);
		int begin = 0;
		int star = i;
		int end  = inputString.length();

		for(int j = star-1; j>0; j--)
		        if(inputString.charAt(j) == exponent||inputString.charAt(j) == plus || inputString.charAt(j) == subtract || inputString.charAt(j) == multiply || inputString.charAt(j) == divide)
			{begin = j+1;j=0;}


		for(int p = star+2;p<inputString.length();p++)
		        if(inputString.charAt(p) == exponent || inputString.charAt(p) == plus || inputString.charAt(p) == subtract || inputString.charAt(p) == multiply || inputString.charAt(p) == divide)
			{end = p; p=inputString.length();}



		t1 = inputString.substring(begin,star);


		t2 = inputString.substring(star+1,end);


		StringBuffer buff = new StringBuffer(inputString);
		buff.delete(begin,end);
		String answer = new String();


		if(count == 0)answer = String.valueOf(Math.pow(number(t1,position),number(t2,position)));
		if(count == 0 && answer.compareTo("NaN")==0)return "?";
		if(count == 0 && answer.compareTo("NaN")!=0)buff.insert(begin,String.valueOf(dcm.format(Math.pow(number(t1,position),number(t2,position)))));
		if(count == 1)buff.insert(begin,String.valueOf(dcm.format(number(t1,position)*number(t2,position))));

		if(t2.charAt(0)!='x' && count==2)
		{
		if(number(t2,position)!=0)buff.insert(begin,String.valueOf(dcm.format(number(t1,position)/number(t2,position))));
		if(number(t2,position)==0)buff.insert(begin,String.valueOf(number(t1,position)/number(t2,position)));
		}

		if(t2.charAt(0)=='x' && count==2)
		{
		if(position!=0)buff.insert(begin,String.valueOf(dcm.format(number(t1,position)/number(t2,position))));
		if(position==0)buff.insert(begin,String.valueOf(number(t1,position)/number(t2,position)));
		}

		if(count == 3)buff.insert(begin,dcm.format(number(t1,position)+number(t2,position)));
		if(count == 4)buff.insert(begin,dcm.format(number(t1,position)-number(t2,position)));

		inputString = buff.toString();i=0;

	}
}
  }

return inputString;
}	
	
  
}





//parenthesis=0
//exponents=1
//multiplication=2
//division=3
//addition=4
//subtraction=5
