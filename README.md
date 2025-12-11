

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

## 2\. Architecture de Persistance et Modélisation

### Le Modèle Relationnel Clé

La modélisation est conçue pour gérer le carnet d'adresses et l'inventaire sans redondance (normalisation).

  * **`Car` :** Entité centrale. Contient des clés étrangères vers `Brand`, `Model`, et `Seller`.
      * **Référence Automatique :** Le champ `ref` (7 chiffres) est généré automatiquement via `@PrePersist`.
      * **Statut par Défaut :** Le champ `status` a une valeur par défaut prédéfinie.
  * **`Seller` / `SellerName` :** Relation **One-to-Many** permettant à plusieurs enregistrements de détails de vendeur d'être liés au même nom commercial/vendeur principal.

### Points d'Attention sur les Entités

  * **Génération de `ref` :** La génération de l'identifiant unique (7 chiffres) est effectuée au niveau de l'entité `Car` via la fonction annotée `@PrePersist`. **Ne jamais définir le champ `ref` manuellement dans le service.**
  * **Default Values :** Le champ `status` a une valeur par défaut définie directement en Java (ex: `"EN_ATTENTE"`) pour garantir la cohérence des nouvelles entrées.

-----

## 3\.  Gestion des Ressources Statiques (Images)

Le serveur est configuré pour servir les images d'inventaire directement depuis le disque.

### Configuration du Chemin (Linux)

L'accès est configuré dans `com.autocash.config.MvcConfig` pour lire les fichiers depuis :
`/home/daniyale/Bureau/intership/AutoCash_Backend-1/src/main/resources/static/upload/`

### Accès Client (React Native)

Pour accéder à ces images depuis l'émulateur ou l'appareil physique :

  * Vous devez remplacer `localhost` par l'**adresse IP locale** de votre machine (ex: `192.168.X.X`).
  * **Format d'URL requis :** `http://[VOTRE_IP]:8000/upload/[dossier]/[nom_fichier.jpg]`

-----

## 4\. Débogage et Dépannage Rapide

| Problème | Cause Probable | Solution |
| :--- | :--- | :--- |
| `Table 'X' doesn't exist` (au démarrage) | Hibernate essaie de supprimer des tables qui n'existent pas encore. | **Ignorer le `WARN`.** La table sera créée juste après. |
| `Could not commit JPA transaction` | Violation de `NOT NULL` ou échec d'une contrainte de clé étrangère. | Vérifiez que tous les IDs dans votre DTO sont valides et que les champs obligatoires sont remplis. |

