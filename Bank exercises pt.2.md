**Exercice 7**

Dans le dossier target apparaissent les dossiers suivants: classes avec les classes compilées, test-classes avec les classes de test compilées, generated-test-sources & generated-sources avec des annotations, maven-status, maven-archiver, surefire-reports et le jar de l’application.

mvn test fait les tests sans créer le jar tandis que package réalise les tests puis crée le jar de l’application.  
mvn verify a l’air de faire les même étapes que package.

**Exercice 9**

La méthode convertToText de la classe Bank.java n’est pas testée. Avant le couverture de la classe Bank.java par les tests est de 73% dans le rapport Jacoco.

Après l’implémentation des tests sur cette méthode, la couverture est passée à 82%