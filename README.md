# 🏦 Bank Application

Application bancaire Java avec tests unitaires complets et couverture de code JaCoCo.

## 📋 Description

Cette application implémente un système bancaire simple avec des fonctionnalités de gestion de comptes, dépôts, retraits, et transferts entre comptes.

## 🚀 Fonctionnalités

- **Gestion des comptes bancaires** : Création, suppression, et recherche de comptes
- **Opérations bancaires** : Dépôts, retraits avec limite journalière
- **Gestion des titulaires** : Informations personnelles des clients
- **Transferts ACH** : Transferts entre comptes bancaires
- **Sérialisation** : Conversion des données en format texte

## 🛠️ Technologies

- **Java 17**
- **Maven** - Gestion des dépendances et build
- **JUnit 4** - Framework de tests unitaires
- **Hamcrest** - Matchers pour les assertions
- **Mockito** - Framework de mocking
- **JaCoCo** - Couverture de code

## 📦 Installation

### Prérequis

- Java JDK 17 ou supérieur
- Maven 3.6 ou supérieur

### Cloner le projet

```bash
git clone https://github.com/VOTRE_USERNAME/Bank_application.git
cd Bank_application
```

### Compiler le projet

```bash
mvn clean compile
```

### Exécuter les tests

```bash
mvn test
```

### Générer le rapport de couverture JaCoCo

```bash
mvn clean test
```

Le rapport sera disponible dans : `target/site/jacoco/index.html`

## 📊 Couverture de code

Le projet contient **90+ tests unitaires** couvrant :
- Tests des opérations de dépôt et retrait
- Tests des cas limites (edge cases)
- Tests de la sérialisation des données
- Tests des transferts entre comptes

## 🏗️ Structure du projet

```
src/
├── main/java/com/imt/mines/
│   ├── Bank.java              # Gestion de la banque
│   ├── BankAccount.java       # Compte bancaire
│   ├── Person.java            # Titulaire du compte
│   ├── ACHService.java        # Interface de transfert ACH
│   └── ACHServiceImpl.java    # Implémentation ACH
└── test/java/bankAccountApp/
    ├── BankTest.java          # Tests de Bank
    ├── BankAccountTest.java   # Tests de BankAccount
    ├── PersonTest.java        # Tests de Person
    └── ACHServiceTest.java    # Tests ACH
```

## 🧪 Tests

Les tests couvrent :

### Happy Paths (Cas normaux)
- ✅ Dépôts et retraits valides
- ✅ Création et suppression de comptes
- ✅ Transferts entre comptes

### Edge Cases (Cas limites)
- ✅ Retrait avec solde insuffisant
- ✅ Dépassement de la limite journalière
- ✅ Montants négatifs ou nuls
- ✅ Comptes inexistants

## 📝 Licence

Ce projet est développé dans un cadre éducatif.

## 👥 Auteurs

Projet réalisé dans le cadre du cours de développement logiciel.

---

**Date de dernière mise à jour** : 19 novembre 2025

