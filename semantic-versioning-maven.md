# ☕ Semantic Versioning avec Maven (Java) – Workflow Git & Maven

Ce document décrit comment gérer un processus de versionnement sémantique (SemVer) pour un projet Java utilisant Maven, de façon manuelle ou semi-automatisée.

---

## 🧩 Emplacement de la version dans Maven

Dans le fichier `pom.xml` :

```xml
<project>
  ...
  <version>1.0.0</version>
</project>
```

Exemples valides avec SemVer :

- `1.0.0-SNAPSHOT` → version de développement
- `1.0.0-alpha`, `1.0.0-beta`, `1.0.0-rc1` → versions pré-stables
- `1.0.0` → version stable

---

## ✅ Workflow manuel (avec Git)

| Étape | Action | Commande ou modification |
|-------|--------|---------------------------|
| 1️⃣ | Commit du code | `git commit -m "feat: ajout de la méthode getTotal()"` |
| 2️⃣ | Modifier la version dans `pom.xml` | `<version>1.0.0-beta</version>` |
| 3️⃣ | Mettre à jour le `CHANGELOG.md` | Manuellement |
| 4️⃣ | Commit du bump de version | `git commit -am "chore: bump version to 1.0.0-beta"` |
| 5️⃣ | Ajouter un tag Git | `git tag v1.0.0-beta` |
| 6️⃣ | Pousser vers GitHub | `git push origin main --follow-tags` |

---

## 🔧 Workflow semi-automatisé avec `versions-maven-plugin`

### 📦 Installation

Dans le `pom.xml` :

```xml
<build>
  <plugins>
    <plugin>
      <groupId>org.codehaus.mojo</groupId>
      <artifactId>versions-maven-plugin</artifactId>
      <version>2.15.0</version>
    </plugin>
  </plugins>
</build>
```

### ⚙️ Commandes utiles

| Commande | Description |
|----------|-------------|
| `mvn versions:set -DnewVersion=1.0.0-rc1` | Modifie la version dans `pom.xml` |
| `mvn versions:commit` | Applique définitivement la nouvelle version |
| `mvn versions:display-dependency-updates` | Affiche les dépendances obsolètes |

### 🧪 Exemple complet

```bash
mvn versions:set -DnewVersion=1.0.0-rc1
mvn versions:commit

git commit -am "chore: bump to version 1.0.0-rc1"
git tag v1.0.0-rc1
git push origin main --follow-tags
```

---

## 🎯 Objectif

Permettre une gestion propre et traçable des versions de projet Java en utilisant les conventions SemVer et les outils natifs Git/Maven.