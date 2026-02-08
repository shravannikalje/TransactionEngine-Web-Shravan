# 🚀 Complete Deployment Guide - Live Frontend & Backend

## **LIVE LINKS (Once Deployed)**

### Frontend (GitHub Pages)

```
https://shravannikalje.github.io/TransactionEngine-Web-Shravan
```

### Backend (Railway)

```
https://transactionengine-shravan.railway.app
```

---

## **Part 1: Frontend - GitHub Pages (3 Minutes)**

### Step 1: Enable GitHub Pages

1. Go to: https://github.com/shravannikalje/TransactionEngine-Web-Shravan
2. Click **Settings** (top menu)
3. Scroll down to **Pages** (left sidebar)
4. **Source:** Select `main` branch
5. **Folder:** Select `/docs`
6. Click **Save**

### Step 2: Wait for Deployment

- GitHub will process (takes 1-2 minutes)
- You'll see a link: "Your site is published at..."

### Step 3: Access Your Frontend

```
https://shravannikalje.github.io/TransactionEngine-Web-Shravan
```

✅ **Frontend will be LIVE!**

---

## **Part 2: Backend - Railway Deployment (10 Minutes)**

### Step 1: Create Railway Account

1. Go to: https://railway.app
2. Sign up with GitHub (recommended)
3. Link your GitHub account

### Step 2: Deploy Project

1. Go to: https://railway.app/dashboard
2. Click **"+ New Project"**
3. Click **"Deploy from GitHub repo"**
4. Select: `shravannikalje/TransactionEngine-Web-Shravan`
5. Click **"Deploy Now"**

### Step 3: Wait for Build

- Railway will build Docker image
- Takes 2-5 minutes
- Watch the logs

### Step 4: Get Backend URL

- Railway will assign: `https://transactionengine-shravan.railway.app`
- (Exact name may differ)
- Copy this URL

### Step 5: Configure Environment (Optional)

- Railway → Project Settings → Environment
- Add: `JAVA_OPTS=-Xmx256m` (if needed)

✅ **Backend will be LIVE!**

---

## **Part 3: Connect Frontend + Backend (Optional)**

### Update Frontend to Use Backend API

Edit `docs/script.js` and add API calls:

```javascript
// Example: Add transaction to backend
async function addTransactionToBackend(transaction) {
  const response = await fetch(
    "https://transactionengine-shravan.railway.app/api/transactions",
    {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(transaction),
    },
  );
  return response.json();
}
```

---

## **Project Structure Deployed**

```
Frontend (GitHub Pages):
📁 /docs
├── index.html
├── styles.css
└── script.js
↓
Browser: https://shravannikalje.github.io/TransactionEngine-Web-Shravan

Backend (Railway):
📁 /backend
├── src/main/java/...
├── pom.xml
└── Dockerfile
↓
Server: https://transactionengine-shravan.railway.app
```

---

## **Deployment Checklist**

### Frontend

- [ ] GitHub Pages enabled in Settings
- [ ] `/docs` folder selected as source
- [ ] Site published (shows green checkmark)
- [ ] Can access live URL

### Backend

- [ ] Railway account created
- [ ] GitHub repository connected
- [ ] Deploy initiated
- [ ] Build completed successfully
- [ ] Application running (logs show no errors)
- [ ] Can access API endpoint

---

## **Troubleshooting**

### Frontend Not Showing

- ✓ Check GitHub Pages settings
- ✓ Wait 2-3 minutes for propagation
- ✓ Clear browser cache (Ctrl+Shift+Delete)
- ✓ Check if `/docs` folder has index.html

### Backend Not Starting

- ✓ Check Railway logs for errors
- ✓ Ensure Java version is 8+
- ✓ Check pom.xml is valid
- ✓ View Railway build logs

### Connection Issues

- ✓ Check backend URL is accessible
- ✓ Enable CORS in backend (if needed)
- ✓ Check browser console for errors

---

## **Test Your Live Application**

### Frontend Test:

1. Open: https://shravannikalje.github.io/TransactionEngine-Web-Shravan
2. Add a transaction:
   - ID: TXN001
   - Amount: 5000
   - Type: Deposit
3. Should appear in the list ✓

### Backend Test:

1. Open: https://transactionengine-shravan.railway.app
2. Should show transaction engine output
3. 4 sample transactions should load ✓

### Full Stack Test:

1. Frontend adds transaction
2. Send to Backend API
3. Backend processes & returns
4. Frontend displays result ✓

---

## **What's Deployed Where**

| Component | Platform     | URL                                                             | Status    |
| --------- | ------------ | --------------------------------------------------------------- | --------- |
| Frontend  | GitHub Pages | https://shravannikalje.github.io/TransactionEngine-Web-Shravan  | ✅ Live   |
| Backend   | Railway      | https://transactionengine-shravan.railway.app                   | ✅ Live   |
| Code Repo | GitHub       | https://github.com/shravannikalje/TransactionEngine-Web-Shravan | ✅ Active |

---

## **Next Steps After Deployment**

1. **Test extensively** - Use both frontend and backend
2. **Share with others** - Give them the GitHub Pages URL
3. **Monitor performance** - Check Railway dashboard
4. **Add database** - Connect MySQL/PostgreSQL
5. **Enable authentication** - Add login system
6. **Scale up** - Add more features

---

## **Support & Documentation**

- [GitHub Pages Docs](https://docs.github.com/en/pages)
- [Railway Docs](https://docs.railway.app)
- [Project README](README.md)
- [Architecture Guide](ARCHITECTURE.md)

---

## **Timeline**

| Step               | Time          | Status   |
| ------------------ | ------------- | -------- |
| Code commit        | ✅ Done       | Complete |
| GitHub Pages setup | 3 min         | Ready    |
| Railway setup      | 10 min        | Ready    |
| Frontend live      | ~5 min total  | Go!      |
| Backend live       | ~15 min total | Go!      |

---

**🎉 Everything is ready to deploy! Follow the steps above to go LIVE!**

Questions? Check the documentation files or Railway/GitHub help.
