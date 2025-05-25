# 🚀 Démo Semantic Versioning avec Maven – Étapes pratiques

Cette démonstration illustre la création progressive de versions SemVer (`patch`, `alpha`, `beta`, `rc`, `stable`) dans un projet Java/Maven.

---

## 🪜 Étape 0 – Incrémentation de version (Patch ou Minor)

Avant de commencer la série de pré-versions, on incrémente d'abord une version **patch** ou **minor**.

```bash
mvn versions:set -DnewVersion=1.1.0
mvn versions:commit
git commit -am "chore: bump to version 1.1.0"
git tag v1.1.0
git push origin main --follow-tags
```

---

## 🔬 Étape 1 – Version alpha

```bash
mvn versions:set -DnewVersion=1.2.0-alpha.0
mvn versions:commit
git commit -am "chore(release): alpha version 1.2.0-alpha.0"
git tag v1.2.0-alpha.0
git push origin main --follow-tags
```

Utilisation : version expérimentale, instable.

---

## 🧪 Étape 2 – Version beta

```bash
mvn versions:set -DnewVersion=1.2.0-beta.0
mvn versions:commit
git commit -am "chore(release): beta version 1.2.0-beta.0"
git tag v1.2.0-beta.0
git push origin main --follow-tags
```

Utilisation : version testable mais pas encore stable.

---

## ✅ Étape 3 – Release Candidate (RC)

```bash
mvn versions:set -DnewVersion=1.2.0-rc.0
mvn versions:commit
git commit -am "chore(release): rc version 1.2.0-rc.0"
git tag v1.2.0-rc.0
git push origin main --follow-tags
```

Utilisation : prête pour production après validation finale.

---

## 🏁 Étape 4 – Première version stable

```bash
mvn versions:set -DnewVersion=1.2.0
mvn versions:commit
git commit -am "chore(release): stable version 1.2.0"
git tag v1.2.0
git push origin main --follow-tags
```

Utilisation : version officielle et stable.

---

## 📌 Récapitulatif des versions

| Étape         | Version            |
|---------------|---------------------|
| Initialisation | `1.1.0`              |
| Alpha         | `1.2.0-alpha.0`     |
| Beta          | `1.2.0-beta.0`      |
| RC            | `1.2.0-rc.0`        |
| Stable        | `1.2.0`             |

---

🎯 **But de la démo** : simuler un cycle de publication progressif en Java/Maven, en suivant les conventions SemVer manuellement avec Git.