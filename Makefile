JAVAC=/usr/bin/javac
SRCDIR=src
BINDIR=bin

.SUFFIXES: .java .class

$(BINDIR)/%.class:$(SRCDIR)/%.java
	$(JAVAC) -d $(BINDIR)/ -cp $(BINDIR) $<

CLASSES= \
		Data.class \
		Node.class \
		GenericsKbAVLApp.class \
		GenericsKbAVLAppTester.class

CLASS_FILES=$(CLASSES:%.class=$(BINDIR)/%.class)

default: compile

compile: $(CLASSES:%.class=$(BINDIR)/%.class)

clean:
	rm $(BINDIR)/*.class

run:	$(CLASS_FILES)
	java -cp bin GenericsKbAVLAppTester
	
javadoc:
	javadoc -d doc -cp bin -sourcepath src $(SRCDIR)/*.java