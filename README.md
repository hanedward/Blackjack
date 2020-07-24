# Blackjack
A card game involving 2 players that compete against each other: the user and a dealer. The user and dealer draw cards to try to reach the highest sum of the cards' numerical values and not exceed a sum of 21.<br><br>

## How to Run the Game:
### Downloads/Installations
1. Download and install the Eclipse IDE: https://www.eclipse.org/downloads/
2. Download and install the JDK 11.0.2: http://jdk.java.net/archive/
3. Download JavaFX 11: https://gluonhq.com/products/javafx/<br><br>

### Download/Import Project
1. Download/Clone project repository to your local desktop
2. Open Eclipse IDE and import project into your workspace<br><br>
  
### Set up JRE, and JavaFX
1. Add a new JRE (Eclipse > Preferences > Java > Installed JREs > Add) and set the JDK 11.0.2 folder as the JRE Home Directory:
   
   <b>../jdk-11.0.2.jdk/Contents/Home</b>
   
2. Add external JARS from JavaFX 11 folder 
3. Attach the source attachment for each JavaFX jar file: <b>src.zip</b>, located in the JavaFX 11 "<b>lib</b>" folder (Source Attachment > External Location > External File)
4. Attach the Java Doc for each JavaFX jar file: https://openjfx.io/javadoc/11/
5. Configure project's build path (Right click project > Build Path > Configure Build Path > Libraries > Add Library > JRE System Library > Alternate JRE > JRE added in step 1)<br><br>

### Add Modules as VM Arguments
1. Navigate to Arguments Window (Run > Run Configurations > Arguments)
2. Add modules: 
   
   <i>--module-path ../javafx-sdk-11.0.2/lib --add-modules
   javafx.base,javafx.controls,javafx.fxml,javafx.graphics,javafx.media,javafx.swing,javafx.web</i><br><br>

### Run Project
1. Open and run GameController.java
2. Enjoy playing Blackjack!<br><br><br>


## Login Screen
<img src="https://github.com/hanedward/Blackjack/blob/master/Blackjack%20Screenshots/Login%20Screen.png" width="600" height="375"/><br><br>

## Create Account Screen
<img src="https://github.com/hanedward/Blackjack/blob/master/Blackjack%20Screenshots/Create%20Account%20Screen.png" width="600" height="375"/><br><br>

## Localization Example 1
<img src="https://github.com/hanedward/Blackjack/blob/master/Blackjack%20Screenshots/Localization%20Example%201.png" width="600" height="375"/><br><br>

## Localization Example 2
<img src="https://github.com/hanedward/Blackjack/blob/master/Blackjack%20Screenshots/Localization%20Example%202.png" width="600" height="375"/><br><br>

## Start Screen
<img src="https://github.com/hanedward/Blackjack/blob/master/Blackjack%20Screenshots/Start%20Screen.png" width="600" height="375"/><br><br>

## Player Win Screen
<img src="https://github.com/hanedward/Blackjack/blob/master/Blackjack%20Screenshots/Player%20Win%20Screen.png" width="700" height="382"/><br><br>

## Dealer Win Screen
<img src="https://github.com/hanedward/Blackjack/blob/master/Blackjack%20Screenshots/Dealer%20Win%20Screen.png" width="700" height="382"/><br><br>

## End Screen
<img src="https://github.com/hanedward/Blackjack/blob/master/Blackjack%20Screenshots/End%20Screen.png" width="600" height="375"/><br><br>
