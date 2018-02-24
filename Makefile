LIB = lib/
BIN = out/production/JGraphT-GSOC2018-WarmUp/
SRC = src/

JGT_CORE = $(LIB)jgrapht-core-1.1.1-20180219.093426-27.jar
JGT_IO = $(LIB)jgrapht-io-1.1.1-20180219.093431-27.jar

.phony: clean all

all:
	make compile
	make run ${ARGS}

compile:
	javac -Xlint -cp $(JGT_CORE):$(JGT_IO) -d $(BIN) $(SRC)org/jgrapht/GSOC2018/warmup/Main.java

run:
	java -cp $(BIN):$(JGT_CORE):$(JGT_IO) org.jgrapht.GSOC2018.warmup.Main ${ARGS}

clean:
	rm -rf $(BIN)* *.class
