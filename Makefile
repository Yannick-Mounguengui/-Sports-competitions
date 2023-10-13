all: doc competition.jar

doc:
	# génération de la documentation
	javadoc -sourcepath src -d doc -subpackages main

cls:
	# compilation des classes
	javac -sourcepath src -d classes src/main/*.java

test: cls
	# tests
	javac -d classes -classpath ./lib/junit-platform-console-standalone-1.9.0.jar ./src/main/*.java  ./src/main/competitions/*.java ./src/main/strategy/*.java ./src/main/decorator/*.java ./src/main/exceptions/*.java ./src/main/util/*.java ./src/main/match/*.java ./src/main/observer/*.java ./test/main/*.java ./test/main/competitions/*.java ./test/main/mocks/*.java ./test/main/decorator/*.java ./test/main/strategy/*.java ./test/main/observer/*.java
	java -jar ./lib/junit-platform-console-standalone-1.9.0.jar -cp classes --scan-classpath --disable-banner

competition.jar: cls
	# construction du jar
	jar cvfe competition.jar main.CompetitionMain -C classes main
	mkdir jar
	mv competition.jar jar

competition:
	# execution du jar
	java -jar jar/competition.jar


clean:
	# nettoyage des fichiers
	rm -r doc classes jar
