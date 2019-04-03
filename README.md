# API Dofus

## 1- Présentation Générale 

Application mobile réalisée dans le cadre d'un projet d'étude en 3ème année à l'ESIEA. Le but étant de nous familiariser avec *Android Studio*, d'apprendre à utiliser un *RecyclerView* et de *charger une API*. Ainsi, cette application a pour but de recenser toutes les armes du jeu *Dofus*. 

## 2- Présentation Fonctionnelle

Ce projet comprend les features suivants : 

 - 2 activités : 
			 - **MainActivity** : Contient la liste des Armes.
			 - **DetailsActivity** : Contient la description de l'arme sélectionnée. 
 - Une **ProgressBar** pour symboliser le **chargement de l'API**. 
 - Appel vers une **API Rest**. 
 - Un **RecyclerView** pour afficher la liste des armes. 
 - **Stockage des données de l'API en cache** lors du premier chargement.
 - **Notification Push** via Firebase. 
 - Fond d'écran et icône de l'application personnalisés.
 - Barre de recherche par nom des armes.
 - Musique de fond - *"Dofus Astrub Theme Song"*.
 - Possibilité de défiler l'écran du Détails Activity.

Le projet comprend l'architectures suivantes : 

 - **GitFlow**: branches master et develop.
 - Architecture **MVC** (Modèle - Vue - Controlleur).
 - Building l'APK de l'application.


## 3- Screenshots 

#### ProgressBar

> **Note:** Barre de progression personnalisée (*Image Dofus*) qui permet de visualiser le chargement de l'API.
<p align="center">
<img src="https://image.noelshack.com/fichiers/2019/14/3/1554284418-56328727-309032443106667-472793350018170880-n-min.png">
</p>

#### Les Activités 

> **Note:** À droite, le MainActivité qui charge le RecyclerView, à gauche le DetailsActivity qui affiche les détails de l'arme sélectionnée. 

<p align="center">
<img src="https://image.noelshack.com/fichiers/2019/14/3/1554284418-56531102-402490370549958-1066424011476434944-n-min.png">
-> 
<img src="https://image.noelshack.com/fichiers/2019/14/3/1554284418-55759977-581031015640449-922973340740616192-n-min.png">
</p>

#### Barre de Recherche 

 > **Note:** Une barre de recherche qui permet de filtrer les armes par noms au fur et à mesure que l'utilisateur écrit. 
<p align="center">
<img src="https://image.noelshack.com/fichiers/2019/14/3/1554284418-56504204-324774531556742-5290224725878898688-n-min.png">
<img src="https://image.noelshack.com/fichiers/2019/14/3/1554284418-56330951-1628391143930101-3691094937132072960-n-min.png">
</p>

#### Notification Push

> **Note:** Permet de notifier l'utilisateur sur l'ouverture de l'application.

<p align="center">
<img width="200" height="400" src="https://image.noelshack.com/fichiers/2019/14/3/1554300285-55908435-352030422102876-5397388128463880192-n.png">
</p>
