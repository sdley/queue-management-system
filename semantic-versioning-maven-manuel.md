# 🧩 Semantic Versioning avec Maven – Méthode manuelle (sans plugin)

Cette approche décrit comment gérer les versions SemVer avec Maven **sans utiliser de plugin** comme `versions-maven-plugin`. Elle est fiable, simple et compatible avec tous les environnements.

---

## ✅ Étapes du workflow manuel

### 1️⃣ Modifier manuellement la version dans `pom.xml`

```xml
<project>
  ...
  <version>1.1.0</version> <!-- nouvelle version -->
</project>
```

---

### 2️⃣ Committer les modifications

```bash
git add pom.xml
git commit -m "chore(release): bump version to 1.1.0"
```

---

### 3️⃣ Créer un tag Git correspondant à la version

```bash
git tag v1.1.0 -m "Release version 1.1.0"
```

---

### 4️⃣ Pousser les modifications et le tag sur GitHub

```bash
git push origin main --follow-tags
```

> Le tag permet de créer automatiquement une release sur GitHub si tu utilises les GitHub Releases.

---

## 🧠 Pourquoi cette méthode est efficace ?

- ✅ Ne dépend **d’aucun plugin externe**
- ✅ Fonctionne avec **tout outil de CI/CD** (GitHub Actions, GitLab CI, etc.)
- ✅ Facile à intégrer dans un `CHANGELOG.md` ou dans un processus de release manuel

---

## 📦 Astuce bonus : Script Bash pour automatiser le bump

```bash
#!/bin/bash
echo "Enter new version (e.g. 1.2.0): "
read version
sed -i "s/<version>.*<\/version>/<version>$version<\/version>/" pom.xml
git add pom.xml
git commit -m "chore(release): bump version to $version"
git tag v$version
git push origin main --follow-tags
```

---

🎯 **Objectif :** Fournir une solution simple, robuste et compatible pour gérer manuellement les versions dans un projet Maven.