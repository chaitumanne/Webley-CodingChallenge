# Webley-CodingChallenge
A coding challenge from Webley to process the data from csv files and find the combination of dishes from a menu to exactly match the target price

Firstly, pull or download the code from github and save it on your local machine.

Steps to run the project:
-------------------------
1. Install Java on Ubuntu
	a. sudo apt-get install default-jre
	b. sudo apt-get install default-jdk
2. Download the project and save it on your local machine.
3. You have to download the jar file and place in /usr/local folder. For your convenient purpose I have placed it on github. Install log4j file and set the path in case to have log files generated
	* $ export CLASSPATH=$CLASSPATH:/usr/local/apache-log4j-1.2.17/log4j-1.2.17.jar
	* $ export PATH=$PATH:/usr/local/apache-log4j-1.2.17/
4. Open terminal and move to the project folder and file by using command
	cd <file-path>
5. For compilation use javac MenuPuzzle.java
6. For execution java MenuPuzzle
7. Now, you can see the output on the terminal if you find any combinzations with logs generated, in case 	if you want to check in a seperate file you can find in the same location named with menupuzzle.txt


Notes:
------
1. Code can be executed on windows and linux machines you can find the appropriate folders on the github
2. Programs accepts a input file in csv format, reads the information and extracts the date based on the criteria provided.
