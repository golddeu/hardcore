# HardcoreRebirth Plugin — Instructions

## Ce que fait ce plugin
- À ta mort en Hardcore → génère automatiquement un nouveau monde avec une seed aléatoire unique
- Tu es téléporté dans ce nouveau monde pour repartir de zéro
- Chaque mort = un monde totalement différent

---

## Comment compiler et installer

### Option A — Compiler en ligne (GRATUIT, sans rien installer)
1. Va sur https://www.jdoodle.com ou https://replit.com
2. ... OU utilise GitHub Actions (voir Option B)

### Option B — Compiler avec GitHub Actions (recommandé)
1. Crée un compte sur https://github.com
2. Crée un nouveau repository
3. Mets les fichiers dans cette structure :
   ```
   src/main/java/com/hardcorerebirth/HardcoreRebirth.java
   src/main/resources/plugin.yml
   pom.xml
   ```
4. GitHub compilera automatiquement et te donnera le .jar

### Option C — Compiler sur ton PC (si tu as Java installé)
```bash
# Installe Maven : https://maven.apache.org/download.cgi
mvn package
# Le .jar sera dans target/HardcoreRebirth-1.0.jar
```

---

## Installer sur Aternos
1. Va dans **Fichiers → plugins**
2. Glisse-dépose le fichier `HardcoreRebirth-1.0.jar`
3. Redémarre le serveur

---

## Vérifier que ça marche
Dans la console tu devrais voir :
```
[HardcoreRebirth] HardcoreRebirth activé ! Bonne chance... 💀
```
