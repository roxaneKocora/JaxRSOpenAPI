# E-Ticket

## Description
Il s'agit d'une application de gestion de tickets :  permettre aux managers de mettre des concerts en ligne, aux clients d'acheter et

## Modèle métier

### Diagramme de classe
![Diagramme de classe](docs/diagramme.png)

### Entités
- **User** : représente un utilisateur de l'application (Client, Manager, Admin)
- **Manager** : hérite de User, gère les événements
- **Admin** : hérite de User, valide les événements
- **Client** : hérite de User, achete les tickets
- **Event** : un concert créé par un Manager
- **Ticket** : acheté par un Client pour un Event

### Regles de gestion 
- Un client ne peut acheter qu'un ticket pour un evenement
- Un **Manager** peut créer plusieurs **Events**
- Un **Ticket** est lié à un seul **Event** pour un seul client!

## Lancer le projet
Une fois le projet cloné
1. Faire **git checkout back** depuis la racine du projet.
2. Faire un Maven build du projet (qui se trouve dans Run As)
2. Creer juste une BD nommée mydatabase dans votre SGBD ou Configurer la base de données dans "src/main/resources/META-INF/persistence.xml" 
	avec les donnees de votre serveur mysql
3. Lancer le Run de la classe "src/main/java/fr.istic..../jpa/JpaTest"
4. Déployer le serveur en faisant un Run de la classe "src/main/java/fr.istic..../rest/RestServer"

## Quelque Endpoints
| Méthode | URL | Description |
|---------|-----|-------------|
| POST | http://localhost:8080/client/inscription | s'inscrire comme client |
| POST | http://localhost:8080/managr/ajouter | enregistrer un manager |
| POST | http://localhost:8080/event/create | Créer un événement |
| GET | http://localhost:8080/event/all/{managerId} | Événements par manager |
| POST | http://localhost:8080/ticket/acheter | acheter un ticket |

## Swagger
La documentation complète est disponible à :
" http://localhost:8080/api/ "








