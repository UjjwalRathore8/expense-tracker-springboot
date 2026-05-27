const token = localStorage.getItem("token");

if (!token) {
    window.location.href = "/html/login.html";
}

const balanceEl = document.getElementById("balance");
const totalIncomeEl = document.getElementById("totalIncome");
const totalExpenseEl = document.getElementById("totalExpense");
const ratioPercentEl = document.getElementById("ratioPercent");
const progressBar = document.getElementById("progressBar");
const insightMessageEl = document.getElementById("insightMessage");

async function loadBalance() {
    const headers = {
        "Authorization": `Bearer ${token}`
    };

    const incomeResponse = await fetch("/api/dashboard/total-income", {
        headers: headers
    });

    const expenseResponse = await fetch("/api/dashboard/total-expense", {
        headers: headers
    });

    const balanceResponse = await fetch("/api/dashboard/balance", {
        headers: headers
    });

    const totalIncome = await incomeResponse.json();
    const totalExpense = await expenseResponse.json();
    const balance = await balanceResponse.json();

    totalIncomeEl.innerText = "₹" + totalIncome;
    totalExpenseEl.innerText = "₹" + totalExpense;
    balanceEl.innerText = "₹" + balance;

    if (balance < 0) {
        balanceEl.style.color = "#e53e3e";
    } else if (balance > 0) {
        balanceEl.style.color = "#2f855a";
    } else {
        balanceEl.style.color = "#718096";
    }

    let ratio = 0;

    if (totalIncome > 0) {
        ratio = (totalExpense / totalIncome) * 100;
        ratio = Math.min(ratio, 100);
    }

    ratioPercentEl.innerText = Math.round(ratio) + "%";
    progressBar.style.width = ratio + "%";

    progressBar.classList.remove("warning", "danger");

    if (ratio >= 80) {
        progressBar.classList.add("danger");
    } else if (ratio >= 50) {
        progressBar.classList.add("warning");
    }

    if (totalIncome === 0 && totalExpense === 0) {
        insightMessageEl.innerText =
            "📭 No transactions yet. Add incomes and expenses.";
    } else if (ratio < 30) {
        insightMessageEl.innerText =
            "🎉 Excellent! You're saving well.";
    } else if (ratio < 50) {
        insightMessageEl.innerText =
            "👍 Good balance. Healthy financial habits.";
    } else if (ratio < 70) {
        insightMessageEl.innerText =
            "⚠️ Consider reviewing your expenses.";
    } else if (ratio < 100) {
        insightMessageEl.innerText =
            "🔔 Most income going to expenses.";
    } else {
        insightMessageEl.innerText =
            "🚨 Expenses exceed income. Review your budget.";
    }
}

loadBalance();