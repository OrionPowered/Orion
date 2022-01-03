setup:
	cd anvil/native && ./build.sh
	cd anvil/java  && mvn clean package
	mkdir -p run