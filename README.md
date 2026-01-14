# Spring Boot Todo API

Microservice minimaliste de gestion de tâches (Todo) développé avec Spring Boot, conçu avec une approche **DevSecOps** complète.

## 🏗 Architecture

- **Backend** : Java 17, Spring Boot 3.2
- **Base de données** : In-Memory (CopyOnWriteArrayList) pour la simplicité et la performance
- **Sécurité** : Scan SAST (CodeQL) & DAST (OWASP ZAP)
- **Observabilité** : Micrometer, Prometheus, OpenTelemetry Tracing
- **Déploiement** : Docker & Kubernetes

## 🚀 Démarrage Rapide

### Prérequis
- Java 17+
- Maven
- Docker (optionnel pour le run local)

### Lancer en local
```bash
git clone https://github.com/votre-user/todo-api.git
cd todo-api
mvn spring-boot:run
```
L'application sera accessible sur `http://localhost:8080`.

### Lancer avec Docker
```bash
docker build -t todo-api .
docker run -p 8080:8080 todo-api
```

## ☸️ Kubernetes (Minikube / Kind)

1. **Charger l'image** (si utilisation d'image locale) :
   ```bash
   # Minikube
   minikube image load todo-api:latest
   # Kind
   kind load docker-image todo-api:latest
   ```

2. **Déployer** :
   ```bash
   kubectl apply -f k8s/
   ```

3. **Accéder à l'API** :
   ```bash
   # Minikube
   minikube service todo-api
   # Ou via NodePort direct (localhost:30080 si configuré)
   curl http://localhost:30080/todos
   ```

## 🛠 CI/CD & Sécurité

Le projet utilise **GitHub Actions** pour l'intégration continue.

| Workflow | Description | Fréquence |
|----------|-------------|-----------|
| **Java CI** | Compilation, Tests Unitaires, Build Docker | Push & PR |
| **CodeQL** | Analyse statique de code (SAST) | Hebdomadaire & PR |
| **ZAP Scan** | Analyse dynamique (DAST) | Push & PR |

## 📊 Observabilité

Endpoints Actuator disponibles :
- **Santé** : `GET /actuator/health`
- **Métriques** : `GET /actuator/metrics`
- **Prometheus** : `GET /actuator/prometheus`

Les logs incluent automatiquement le **TraceID** pour le traçage distribué :
```text
INFO [todo-api,65eb...,...] - GET /todos - Status: 200
```

## 📝 API Usage (Exemples)

**Créer une tâche :**
```bash
curl -X POST -H "Content-Type: application/json" -d '{"title":"Apprendre K8s", "completed":false}' http://localhost:8080/todos
```

**Lister les tâches :**
```bash
curl http://localhost:8080/todos
```
