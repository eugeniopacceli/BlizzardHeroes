all:
	javac -d ./blizzardHeroesApp \
	./src/edu/blizzardheroes/app/*.java \
	./src/edu/blizzardheroes/gameplay/*.java \
	./src/edu/blizzardheroes/gui/*.java \
	./src/edu/blizzardheroes/model/actors/*.java \
	./src/edu/blizzardheroes/model/cards/*.java && \
	mkdir blizzardHeroesApp/edu/blizzardheroes/assets/ && \
	cp -r ./src/edu/blizzardheroes/assets/* blizzardHeroesApp/edu/blizzardheroes/assets && \
	cp ./src/edu/blizzardheroes/gui/MainWindow.fxml blizzardHeroesApp/edu/blizzardheroes/gui \

clean:
	rm -rf ./blizzardHeroesApp/edu

run:
	cd ./blizzardHeroesApp && java edu.blizzardheroes.app.BlizzardHeroes
