# ControlledCheckingProcessor
The program is used for scanning and picking out the controlled classes and methods which marked with controlled annotation.

While comparing, the controlled classes and methods can be awared and recorded in a report file. 

This program includes two project:
* processor: the core function
* ControlledApp: an example

Referring to ControlledApp for the usage. To indicate the report location, using following maven command:
```
mvn "-Dcontrolled.list=<output file path>" compile
```