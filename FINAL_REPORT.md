# Rapport Final de Projet - Todo API

**Date** : 14 Janvier 2026
**Sujet** : Mise en production d'une API Spring Boot avec approche DevSecOps.

---

## 1. Introduction
Ce projet avait pour objectif de créer de zéro une API REST "Todo" performante et sécurisée, tout en respectant des contraintes strictes de simplicité (code backend < 150 lignes) et en intégrant des pratiques modernes d'ingénierie logicielle (Observabilité, Sécurité, CI/CD).

## 2. Architecture Technique
Nous avons choisi une stack éprouvée et robuste :
- **Langage** : Java 17 LTS.
- **Framework** : Spring Boot 3.2.1 pour sa facilité de configuration (`spring-boot-starter-web`).
- **Persistance** : Stockage en mémoire (`CopyOnWriteArrayList`) pour garantir la thread-safety sans la lourdeur d'une base de données externe pour ce POC.

## 3. Qualité et Intégration Continue (CI/CD)
Un pipeline complet a été mis en place via **GitHub Actions** :
- **Build** : Compilation Maven et création de l'image Docker.
- **Validation** : Le pipeline vérifie systématiquement que l'application démarre et que les endpoints d'observabilité répondent avant de valider un build.

## 4. Sécurité (DevSecOps)
La sécurité a été intégrée "by design" dans le cycle de vie :
- **SAST (Static Application Security Testing)** : Avec **GitHub CodeQL**, le code est analysé statiquement pour détecter les failles d'injection ou de mauvaise pratique Java.
- **DAST (Dynamic Application Security Testing)** : Avec **OWASP ZAP**, l'application en cours d'exécution est scannée pour détecter les vulnérabilités HTTP (headers manquants, fuite d'informations).

## 5. Observabilité
L'application n'est pas une "boite noire" :
- **Métriques** : Exposition des métriques JVM et HTTP au format **Prometheus**.
- **Tracing** : Intégration de Micrometer Tracing et OpenTelemetry. Chaque log contient désormais un `traceId` unique, permettant de corréler les logs d'une même requête à travers le système.

## 6. Déploiement Kubernetes
L'application est prête pour le cloud ("Cloud Native") :
- Fichiers manifestes `Deployment` et `Service` créés.
- Utilisation de **Probes** (Liveness/Readiness) basées sur Actuator pour assurer la haute disponibilité.

## 7. Leçons Apprises
- **Simplicité vs Fonctionnalité** : Il est possible de créer une application "production-ready" (avec métriques, sécurité, Docker) avec très peu de code métier (< 100 lignes).
- **L'importance du pipeline** : L'intégration immédiate des scans de sécurité (SAST/DAST) évite la "dette technique de sécurité".
- **Automatisation** : Tout, du build au scan de vulnérabilité, est automatisé, réduisant le risque d'erreur humaine.

---
*Ce projet démontre qu'une approche rigoureuse et outillée est applicable même aux plus petits microservices.*
