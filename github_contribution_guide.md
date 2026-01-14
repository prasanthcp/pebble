
git init
git add .
git commit -m "Initial commit"
git remote add origin https://github.com/prasanthcp/pebble.git
git branch -M main
git push -u origin main

# GitHub Open Source Contribution — Complete Command Guide

## 1. Fork the Project on GitHub
- Open the original repository.
- Click **Fork** → create your own fork.

---

## 2. Clone Your Fork Locally
```bash
git clone <forked-repo-url>
cd <repo>
```

---

## 3. Add the Original Project as Upstream
```bash
git remote add upstream <original-repo-url>
git remote -v   # verify
```

---

## 4. Always Create a New Branch for Your Work
```bash
git checkout -b feature/my-change
```

---

## 5. Make Your Changes
- Edit files
- Add new files

Stage and commit:
```bash
git add .
git commit -m "Describe your change"
```

---

## 6. Sync Your Fork Before Pushing (AVOID conflicts)
Fetch latest original repo code:
```bash
git fetch upstream # fetch changes to upstream remote main
```

Update your local main:
```bash
git checkout main
git merge upstream/main  # merges upstream changes to local main 
```

Rebase your feature branch on updated main:
```bash
git checkout feature/my-change
git rebase main
```

---

## Fix Conflicts
```bash
git add <file>
git rebase --continue
```

---

## 7. Push Your Branch to Your Fork [only feature branch can be --force]
```bash
git push origin feature/my-change
```

---

## 8. Create Pull Request
- Go to GitHub → your fork
- GitHub detects the new branch
- Click **Compare & Pull Request**
- Select:
  - base repo = original repo
  - base branch = main (or relevant branch)
  - compare branch = your feature branch

Submit PR.

---

## 9. Respond to Review Comments
If reviewers ask changes:
```bash
# edit
git add .
git commit -m "Address review comments"
git push origin feature/my-change
```
Push again → PR updates automatically.

---

## 10. Keep Your Fork Updated Over Time
```bash
git checkout main
git fetch upstream
git merge upstream/main # git pull = git fetch + git merge (from origin/main)¯
git push origin main
```
---

## Summary (One-Page Command List)

```bash
git clone <forked-url>
cd <repo>
git remote add upstream <original-url>
git checkout -b feature/my-change
git add .
git commit -m "msg"

git fetch upstream
git checkout main
git merge upstream/main
git checkout feature/my-change
git rebase main

git push origin feature/my-change
```

---

This guide contains every essential command and sequence for real open‑source contribution.
