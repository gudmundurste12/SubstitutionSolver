# SubstitutionSolver
A program that solves substitution ciphers. After a solution has been found the results will be stored in res/messages/solutions

#How to use
Open the project in Intellij
Create a run configuration using SubstitutionSolver as the main class.
Set the program arguments as specified in the parameters section
Run the program

#Parameters
##Required
The first parameter is the name of the file to be decrypted. The program assumes the file is in the res/messages directory

##Optional
Optional parameters are the following flags
<br />
-a: If this flag is not used the program will just find the first solution and stop searching. If this flag is used the user will be prompted whether or not he wants to keep searching after each new solution is found.
<br />
-u: Specify a profile to use. A profile is a directory in res/dictionaries/userSpecified. The program will read every file in the directory and read every line in them as a word that the program accepts as a valid word.

##Example
The parameters:
message1.txt -a -u profile1
mean that the program will
* decrypt message1.txt, located in res/messages.
* search for new solutions until the user chooses to stop, or every solution has been found
* use every file in res/dictionaries/userSpecified/profile1 as a list of valid words