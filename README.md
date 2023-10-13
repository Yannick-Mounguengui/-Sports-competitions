# Projet : Compétitions

## Membres du binôme :

* Chaïma BOUDEHANE
* Yannick MOUNGUENGUI


## How To  

## Récupérer le projet  

```bash
git clone git@gitlab-etu.fil.univ-lille.fr:chaima.boudehane.etu/Boudehane_Mounguengui-COO.git
```

*se placer dans le répertoire*  

```bash
cd Boudehane_Mounguengui-COO/Projet
```

# Avec le Makefile


**NB: vous pouvez faire la commande `make` directement pour générer la documentation et le jar en une seule fois**    


## Génération et consultation de la documentation  

*générer*

```bash
make doc
```  

*consulter*

Ouvrir le fichier **index.html** situé dans le dossier **/doc** dans le navigateur.    

## Compilation du code source  

```bash
make cls
```

## Génération et exécution de l'archive

*construction du jar*  

```bash
make competition.jar
```  

*exécution du jar*  

```bash
make competition
```

## Exécution des tests  

```bash
make test
```

## Nettoyer les fichiers  

```bash
make clean
```  
  

# Sans Makefile  

## Génération et consultation de la documentation  

*générer*  

```bash
javadoc -sourcepath src -d doc -subpackages main
```

*consulter*    

Ouvrir le fichier **index.html** situé dans le dossier **/doc** dans le navigateur.


## Compilation du code source  

```bash
javac -sourcepath src -d classes src/main/*.java
```

## Génération et exécution de l'archive

*construction du jar*  

```bash
jar cvfe competition.jar main.CompetitionMain -C classes main
```  

*exécution du jar*  

```bash
java -jar competition.jar
```

## Compilation et exécution des tests  

*compiler*  

```bash
javac -d classes -classpath ./lib/junit-platform-console-standalone-1.9.0.jar ./src/main/*.java  ./src/main/competitions/*.java  ./src/main/decorator/*.java ./src/main/strategy/*.java ./src/main/exceptions/*.java ./src/main/util/*.java ./src/main/match/*.java ./src/main/observer/*.java ./test/main/*.java ./test/main/competitions/*.java ./test/main/mocks/*.java ./test/main/strategy/*.java ./test/main/observer/*.java ./test/main/decorator/*.java
```

*exécuter*  

```bash
java -jar ./lib/junit-platform-console-standalone-1.9.0.jar -cp classes --scan-classpath --disable-banner
```  

## Nettoyer les fichiers  

```bash  
rm -r doc classes competition.jar
```

# Livrables  

## Livrable 1

![](uml/UML-v1.jpg)    

## Choix de conception  

### Méthode play dans Tournament

Nous complété les classes filles Tournament et League qui héritent de Competition, notamment leur méthode `play`.
Pour Tournament, nous avons utilisé une liste chaînée, qui semble être une solution logique pour pouvoir modéliser un tournoi. On récupère à chaque fois les 2 premiers éléments de la liste et on les fait s'affronter `(playMatch())` pour obtenir le gagnant. Enfin, nous ajoutons ce dernier en queue de liste pour pouvoir recommencer (voir le pseudo code ci-dessous).


```
Initialisation de la liste chaînée et affectation à la liste de compétiteurs
 TANT QUE la taille de la liste chaînée est différente de 1 :  
 |  Retirer l'élément en tête de liste et l'affecter au premier compétiteur
 |  Retirer l'élément en tête de liste et l'affecter au second compétiteur
 |  Récupérer le gagnant du match
 |  Ajouter ce gagnant en queue de liste
 |      
 Retirer le gagnant de la liste chaînée
 Afficher le gagnant du tournoi
```  


### Les différents types de match

Nous avons pensé qu'il serait plus judicieux de créer une interface `Match`, qui serait uniquement composée de 2 méthodes : `getWinner(c1 : Competitor, c2 : Competitor)` et `toString()`. Une classe nommée RandomWinner implémente cette interface afin de définir ces méthodes. Nous avons réfléchi sur le fait que plusieurs types de match, c'est à dire, de manières de déterminer le gagnant, peuvent changer par la suite. Et nous trouvons qu'il est préférable de préparer cette éventualité qui ainsi permettra de créer d'autres types de match différents de `RandomWinner`.
*Exemple : une classe CheatedWinner dont la méthode getWinner déterminera le gagnant à l'avance comme c1 par exemple*  


Nous avons créé une référence à Match dans Competition par le biais d'un attribut match (initialisé à `null` dans le constructeur).
La méthode selectMatch() permet à l'utilisateur de choisir le type de match qu'il souhaite pour n'importe quelle compétition (pour l'instant il n'y a qu'un type de match).  

Cette idée de conception a pour but de simplifier la méthode `playMatch`. Celle-ci n'aura qu'une ligne de code qui consistera à afficher le gagnant selon le type de match choisi par l'utilisateur : elle appelle la méthode getWinner spécifique au type de match.   


### Mocks et classes de tests abstraites  

Nous avons utilisé des Mocks afin de tester les appels de certaines méthodes : `MockCompetition` et `MockMatch` permettent de vérifier l'appel de méthodes telles que play, playMatch, displayMatchPossible, etc...

De plus, nous avons créé une classe de tests abstraite `CompetitionTest`, car nous savons que League et Tournament héritent de Competition dans le code source. Cela permettra à LeagueTest et TournamentTest d'hériter de ces méthodes de test qui, pour la plupart sont identiques.



## Livrable 2

![](uml/UML-v2.jpg)      


## Choix de conception  

### Design Pattern

#### Factory Method

Lors de l'écriture des tests, nous avons utilisé le pattern `factory method`. En effet, dans `CompetitionTest` nous avons défini une méthode abstraite `createOneCompetition()` que nous avons redéfinie dans les classes filles (LeagueTest, TournamentTest et MasterTest). Cette méthode a pour but de créer des compétitions (instances) propres aux classes héritant de CompetitionTest.


#### Strategy

Le pattern `Strategy` a été essentiel pour la sélection des compétiteurs en phase finale. Pour permettre la sélection des compétiteurs, nous avons créé une interface `Selection` implémentée par trois classes :   

* **SelectFirst** : sélectionne les premiers de chaque poule. *(Master 16)*
* **SelectTwoFirstThenTwoBestThird** : sélectionne les 2 premiers de chaque poule puis les 2 meilleurs troisièmes. *(Master 24)*
* **SelectTwoFirst** : sélectionne les 2 premiers de chaque poule. *(Master 32)*

Le type de sélection est précisé à la construction du Master afin que le nombre de compétiteurs en phase finale soit bien une puissance de 2.
Ainsi le type de sélection sera spécifique au nombre de compétiteurs du Master.




## Livrable 3

![](uml/UML-v3.jpg)  

## Choix de conception  


Comme indiqué sur le sujet, nous nous sommes inspirés du pattern ` Observer` et nous l'avons choisi pour la modélisation de cette troisième partie.  
Nous avons créé une interface `ObserverCompetition` qui sera implémentée par deux classes : `Journalist` et `Bookmaker`.
En effet, les journalistes et les bookmaker sont ceux qui vont observer les matchs de chaque compétition et ainsi y réagir. Ces deux instances auront alors une méthode commune `reactToCompetition` (implémentée grâce à l'interface) qui prend en parametre un match.
La méthode sera cependant différente : les journalistes diffuseront les résultats et les bookmaker feront évoluer la cote des compétiteurs.

`Competition` est la classe observée. `competitionDetected` est la methode qui permet à chaque observer de reagir en detectant les matchs d'une competition. On a trouvé plus facile de faire ajouter directement les observateurs via une competition que via un match car on ne savait pas comment ajouter des observateurs via un match apres dans ce cas ou pour eviter de dupliquer du code en le fesant dans notre classe match et dans competition.

On a utilisé le pattern `Decorator` pour permettre d'avoir deux journalistes  qui affichent des messages differents, de meme pour deux bookmakers qui affichent des côtes différentes en redefinisant la methode `reactToCompetition` dans les deux cas.
