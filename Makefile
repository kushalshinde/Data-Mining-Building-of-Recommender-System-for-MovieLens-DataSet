JFLAGS = -g
JC = javac
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
	*.java 

Main = main

default: classes

classes: $(CLASSES:.java=.class)
	$(RM) output.txt
clean:
	$(RM) *.class