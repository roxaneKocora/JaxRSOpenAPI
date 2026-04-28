# Nom du projet

## Lancer le projet
Une fois le projet cloné
1. faire git **checkout new_branch** depuis la racine du projet.
2. Configurer la base de données dans `persistence.xml` avec un serveur mysql : si le port de votre server est different ou si vous voulez changer le nom de la bd
3. Ou sinon creer juste une BD nommée mydatabase dans votre SGBD et c'est bon.4. Déployer sur le serveur en faisant un RUN dans la classe RestServer

## Endpoints
| Méthode | URL | Description |
|---------|-----|-------------|
| POST | http://localhost:8080/client/inscription | s'inscrire comme client |
| POST | http://localhost:8080/managr/ajouter | enreegistrer un manager |
| POST | http://localhost:8080/event/create | Créer un événement |
| GET | http://localhost:8080/event/all/{managerId} | Événements par manager |
| POST | http://localhost:8080/ticket/acheter | acheter un ticket |

## Swagger
La documentation complète est disponible à :
`http://localhost:8080/api/`