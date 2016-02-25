---
layout: post
title:  "Welcome to Calc-u-Later!"
date:   2016-02-19 10:29:40
categories: jekyll update
---
Hi there!

We are a group of students who have been 'hired' to build a calculator. This is for the course `2311`.

Go ahead and explore this site!

Here are our current documents:

[Tests][tests]

[User_manual][user_manual]

[Requirements][req]

[Design][design]

Features:

1. Math operations
2. History
3. GridLayout
4. Big Decimal

### 1. Example Math Operation:
{% highlight java %}
else if (button.equals("!")){
  System.out.println("factorial");
  String input = userValueText.getText();

  history.setText(his+","+input+button+"=");

  Double num1 = numbers.pop().doubleValue();
  Double ans = factorial(num1);
  System.out.println(ans);
  BigDecimal b = BigDecimal.valueOf(ans);
  numbers.push(b);
  calcText.setText(ans.toString());
  userValueText.setText("");

}
{% endhighlight %}

### 2.History:
{% highlight java %}
public static void setHistory(String value) {
    history.setText(value);
  }
{% endhighlight %}

### 3.GridLayout:
{% highlight java %}
pane.setLayout(new GridBagLayout());
GridBagConstraints c = new GridBagConstraints();
...
button =  new ButtonAdapter("sin") {
  public void pressed(){
    registerButton("sin", theController);
  }
};
c.gridx = 3;
c.gridwidth = 1;
c.gridy = 6;
pane.add(button, c);
{% endhighlight %}

### 4.BigDecimal:
{% highlight java %}
BigDecimal num1 = numbers.pop();
System.out.println(num1);
BigDecimal num2 = numbers.pop();
System.out.println(num2);

MathContext roundVal = new MathContext(5);

private static Stack<BigDecimal> numbers;
BigDecimal result = num2.divide(num1, roundVal);
findRoundingValue(result.toPlainString());
{% endhighlight %}

Highlights:

1. Stack
2. Testing (Incomplete right now)
3. Dispatcher in our View (needs refactoring)

### 1.Stack:
{% highlight java %}
private static Stack<BigDecimal> numbers;
this.numbers = new Stack();
{% endhighlight %}

### 2.Testing:
{% highlight java %}
...
{% endhighlight %}

### 3.Dispatcher:
{% highlight java %}
public static void registerButton(String button, CalcController theController) {  
    String previous;
    String his = history.getText();
    // right now this method is big, so when we refactor it we will put each button into its own controller method
    // furthermore, we will make the stack and history be part of the model
    if (!button.equals("+/-") && !button.equals(".")) {
      char lastChar = his.charAt(his.length() - 1);
      if (lastChar == '=') {
        String removeEquals = his.substring(0, his.length() - 1);
        history.setText(removeEquals);
      }
    }
    
    
    if (button.equals("+")) {
...
{% endhighlight %}

Plans:

1. Refactoring Majorly (ie: Change Stack, build thin controllers, logicless views, and bulky util/model files)
2. Enchancement of Documents
3. New requirements

Strech Goals:

1. Servlet
2. CLI

Please file all bugs/feature requests at [the Calc-u-later repo][jekyll-gh].

[tests]: https://s3-us-west-2.amazonaws.com/bookpdftest/tests.pdf
[User_manual]: https://s3-us-west-2.amazonaws.com/bookpdftest/User+Manual.pdf
[req]: https://s3-us-west-2.amazonaws.com/bookpdftest/req.pdf
[design]: https://s3-us-west-2.amazonaws.com/bookpdftest/design.pdf
[application]: https://s3-us-west-2.amazonaws.com/bookpdftest/Export1.jar
[jekyll-gh]:   https://github.com/raksonibs/Calc-u-later
[jekyll-help]: https://github.com/jekyll/jekyll-help
