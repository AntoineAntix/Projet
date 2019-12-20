# API Dofus

## 1- Présentation Générale 

Application mobile réalisée dans le cadre d'un projet d'étude en 4ème année à l'ESIEA. Le but étant d'appronfondir notre utilisation d'*Android Studio*, d'apprendre à utiliser un *RecyclerView*, de *charger une API*, et d'utiliser des fragments. Ainsi, cette application a pour but de réaliser une encyclopédie du jeu *Dofus*. 

## 2- Présentation Fonctionnelle

Ce projet comprend les features suivants : 

 - 7 activités : 
			 - **MainActivity** : Contient le menu principal.
			 - **EncyclopedieActivityFragment** : Gère les fragments contenant les listes des classes, des 								equipements et des armes.
			 - **3 DetailsActivity** : Contiennent les descitption des classes, équipements et armes. 
			 - **TutoActivityFragment** : Gère les fragments contenant les tutoriels d'XP et de Kamas.
			 - **Apropos** : Renseignements sur l'application.
 - Un **splash** avec une **ProgressBar** pour symboliser le **chargement de l'API**. 
 - Appel vers une **API Rest**. 
 - Différents **RecyclerView** pour afficher les différentes listes. 
 - **Stockage des données de l'API en cache** lors du premier chargement.
 - **Notification Push** via Firebase. 
 - **Fragment** pour l'affichage des différentes listes et les vidéos tutoriels.
 - **Collapsing Toolbar** pour les détails des différents objets.
 - **ViewPager** pour l'affichage des fragements. 
 - **NavigationDrawer** pour passer d'une activité à une autre.
 - **Video Player** affichage des vidéo youtube pour les tutoriels.
 - Fond d'écran et icône de l'application personnalisés.
 - **Barre de recherche** par nom des classes, des équipements et des armes.
 - **Musique** de fond - *"Dofus Astrub Theme Song"*.

Le projet comprend les architectures suivantes : 

 - **GitFlow**: branches master et develop.
 - Architecture **MVC** (Modèle - Vue - Controlleur).
 - Building l'APK de l'application.


## 3- Screenshots 

#### ProgressBar

> **Note:** Barre de progression personnalisée (*Image Dofus*) qui permet de visualiser le chargement de l'API.

<p align="center">
	<img src="https://github.com/AntoineAntix/Projet/blob/master/ScreenReadMe/progressBar.png" width="200" height="400" title="Screenshot ProgressBar">
</p>

#### Main Activity

> **Note:** Le Main Activity est la page d'accueil de l'application. Il y a 3 boutons pour accéder aux autres activités.

<p align="center">
<img src="https://github.com/AntoineAntix/Projet/blob/master/ScreenReadMe/homeActivity.png" width="200" height="400">
</p>

#### A propos Activity

> **Note:** Le A Propos est la page qui renseignements sur l'application.
<p align="center">
<img src="https://github.com/AntoineAntix/Projet/blob/master/ScreenReadMe/aPropos.png" width="200" height="400">
</p>

#### Ecyclopédie Activity

> **Note:** 3 fragments qui permettent d'accéder aux listes pour les classes, les armes et les équipements.
<p align="center">
<img src="https://github.com/AntoineAntix/Projet/blob/master/ScreenReadMe/encyclopedieActivity.png" width="200" height="400">
-> 
<img src="https://github.com/AntoineAntix/Projet/blob/master/ScreenReadMe/fragmentEquipement.png" width="200" height="400">
-> 
<img src="https://github.com/AntoineAntix/Projet/blob/master/ScreenReadMe/fragmentArme.png" width="200" height="400">
</p>

#### Barre de Recherche 

 > **Note:** Une barre de recherche qui permet de filtrer les armes par noms au fur et à mesure que l'utilisateur écrit. 
<p align="center">
<img src="https://image.noelshack.com/fichiers/2019/14/3/1554284418-56504204-324774531556742-5290224725878898688-n-min.png">
<img src="https://image.noelshack.com/fichiers/2019/14/3/1554284418-56330951-1628391143930101-3691094937132072960-n-min.png">
</p>

#### Notification Push

> **Note:** Permet de notifier l'utilisateur.

<p align="center">
<img width="200" height="400" src="https://image.noelshack.com/fichiers/2019/14/3/1554300285-55908435-352030422102876-5397388128463880192-n.png">
</p>
