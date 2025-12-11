

-----

#  AutoCash Backend : Guide d'Architecture et Démarrage Rapide

Bienvenue dans le dépôt du backend AutoCash. Ce service est le cœur de notre application, gérant la persistance des données (inventaire, vendeurs, historique).

##  Vision & Philosophie du Projet

Notre objectif est la **robustesse** et l'**évolutivité**. Nous avons choisi une architecture classique mais puissante pour garantir la performance des API mobiles.

  * **Philosophie Clean Code :** Séparation stricte des responsabilités (Controller, Service, Repository). Le service est la seule source de vérité pour la logique métier.
  * **Performance Mobile :** Optimisation des requêtes JPA et utilisation de DTOs pour minimiser la charge réseau.

##  Stack Technique (Le Noyau)

| Technologie | Rôle Architectural | Note |
| :--- | :--- | :--- |
| **Spring Boot 3.x / Java 17** | Cadre de développement et Serveur embarqué. | Performance et Standardisation. |
| **Spring Data JPA & Hibernate**| Couche ORM. | Simplifie l'accès à MySQL et la gestion des transactions. |
| **MySQL 8.0+** | Base de Données. | Moteur transactionnel fiable. |
| **Maven** | Gestion du cycle de vie (Build, Test, Déploiement). | |

-----

## 1\.  Mise en Place Locale (Getting Started)

### Prérequis

Assurez-vous d'avoir installé et configuré :
 
1.  JDK 17+.
2.  Maven.
3.  Un serveur MySQL local **lancé** (port par défaut 3306).

### Configuration de la Base de Données

1.  **Création du Schéma :** Créez une base de données nommée `autocash_db`.

    ```sql
    CREATE DATABASE autocash_db;
    ```

2.  **Connexion :** Ouvrez `src/main/resources/application.properties` et **remplacez** `votre_mot_de_passe` par votre mot de passe MySQL `root`.

    ```properties
    spring.datasource.url=jdbc:mysql://localhost:3306/autocash_db?useSSL=false&serverTimezone=UTC
    spring.jpa.hibernate.ddl-auto=update 
    spring.datasource.username=root 
    spring.datasource.password=votre_mot_de_passe
    ```

### Démarrage

Exécutez l'application via votre IDE (exécution de la classe principale) ou via le terminal :

```bash
mvn spring-boot:run
```

Le serveur écoute sur `http://localhost:8000`.

-----
