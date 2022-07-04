# Gallery App
## Choix technique
### Architecture 
##### MVVM :   Comme avantages de MVVM nous pouvons citer:


**La maintenabilité:** Avec une séparation claire des différents modules du projet, il y’a une meilleure organisation structurelle, le code est plus lisible, mieux architecturé car respectant des standards bien définis et donc plus facile à maintenir. Les fonctionnalités étant regroupées dans dans le ViewModel, cela permet d’avoir des vues propres, dépourvues de code ‘spaghettis’.

**La testabilité:** Avec MVVM chaque bout de code est écrit de façon granulaire, les dépendances internes et externes sont séparées de la logique métier et l’ensemble des fonctionnalités à tester. Ce qui rend le code bien plus testable.

**La réutilisabilité et l’extensibilité du code:** MVVM facilite la possibilité d’ajout et de réutilisation du code par le principe de faible couplage de classes, cela a pour bénéfice de diminuer les risques de lourdes dettes techniques lors de maintenances évolutives.
##### Test :
En Utilisant JUnit4 et Truth.</br>
Le test se fait pour les viewModels.
##### Clean Code: les principes SOLID
###### Single responsability: 
Par Exemple la méthode suivante a pour rôle la récupération des données dépuis un data source, donc la seul raison pour changer cette méthode par exemple c'est pour changer le data source.</br> </br><img width="566" alt="image" src="https://user-images.githubusercontent.com/25714864/177047593-af8ebd6a-9302-4402-a8aa-0599c79bf9ee.png">
###### Open/Closed : 
On va créer une interface ou une class abstraite : </br>
<img width="395" alt="image" src="https://user-images.githubusercontent.com/25714864/177048227-6ef09636-dcce-4edc-8355-51db3d7b6d02.png">
</br>
Maintenant si les autres classes implémentent (ou héritent) l'interface (ou la classe abstraite) ,elle doivent just redéfinir les méthodes et ajouter des autres méthodes s'il est nécessaire. </br>
<img width="575" alt="image" src="https://user-images.githubusercontent.com/25714864/177048363-9400a8f3-d63e-4ac1-a70c-29e549ae8f1c.png">
</br> 
###### Liskov substitution : 
Les classes parents sont remplaçable par les classes fils sans changement de comportement ( exemple précédant )
###### Interface Segregation Principle : 
On ne peut pas forcer les classes fils à implémenter des méthodes qui n'ont pas besoin.
###### Dependency Inversion Principle :
Il faut toujours dépendre de l'abstraction :</br>
<img width="178" alt="image" src="https://user-images.githubusercontent.com/25714864/177048895-f0b32488-8c0d-41c0-8f88-730548a97e00.png">
</br>
Si jamais on veut changer le backend par exemple ou bien mocker les données, on doit créer une autre implémentation tout simplement .
Dans notre cas Koin va injecter l'implémentation spécifique qu'on voulais.
### Choix des libraries: 
##### Koin :
Koin est un framework d'injection de dépendance pragmatique et léger pour les développeurs Kotlin, avec un peu de code et de configuration.
##### Picasso : 
Pour charger les images dans les ImageView.
##### Truth : 
Une bibliothèque crée par google pour les tests d'intégration et unitaire,elle rend nos assertions de test et nos messages d'échec plus lisibles.
##### Coroutines : 
Coroutine est une unité de traitement permettant d’exécuter du code non-bloquant et asynchrone. Sur le principe, il s’agit d’un Thread “allégé”. Son avantage étant qu’elle peut être suspendue et reprise plus tard. Une coroutine peut être suspendue dans un Thread et être reprise dans un autre. Elle ne dépend donc pas d’un Thread en particulier, ce qui apporte un avantage considérable lors de l’utilisation de plusieurs traitements asynchrones.
##### Retrofit : 
Retrofit est aujourd'hui une des façons les plus simples d'implémenter des appels à des webservices REST.
##### Navigation component : 
Ce composant met à disposition du code et des outils dont le but est de simplifier l'implémentation de la navigation dans une application Android.

## Informations sur le projet Gallery app
### Durée de l'implémentation : 
Samedi 02 juillet 2022 : 2 heures </br>
Dimanche 03/07/2022 : 6 heures
### Améliorations: 
- Des tests d'intégration : pour tester les navigations par exemple
- Améliorer le UI de l'application :1) Améliorer le top bar ou bien le remplacé. </br> 2) un nouveau design pour les items de recycleView.
- Détection de l'état de connexion pour notifier l'utilisateur: Méthode prète dans le fichier utils, il me reste que l'appeler dans les bons endroits.
- Swipe to refresh: Ajout dans XML puis on va implémenter le listener. 
##### Url de l'apk :
https://drive.google.com/file/d/1ZswkMZKWWZvAfPEScHtVrWOyFDVuaQvv/view?usp=sharing





