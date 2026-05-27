const token = localStorage.getItem("token");

if (!token) {
    window.location.href = "/html/login.html";
}

document.getElementById("currentDate").innerText =
    new Date().toDateString();

async function loadDashboard() {

    const headers = {
        "Authorization": `Bearer ${token}`
    };

    try {
        const incomeResponse = await fetch("/api/dashboard/total-income", {
            headers: headers
        });

        console.log("Income status:", incomeResponse.status);

        const expenseResponse = await fetch("/api/dashboard/total-expense", {
            headers: headers
        });

        console.log("Expense status:", expenseResponse.status);

        const balanceResponse = await fetch("/api/dashboard/balance", {
            headers: headers
        });

        console.log("Balance status:", balanceResponse.status);

        if (!incomeResponse.ok || !expenseResponse.ok || !balanceResponse.ok) {
            console.log("Dashboard API error");
            return;
        }

        const totalIncome = await incomeResponse.json();
        const totalExpense = await expenseResponse.json();
        const balance = await balanceResponse.json();

        document.getElementById("totalIncome").innerText =
            "₹" + totalIncome;

        document.getElementById("totalExpense").innerText =
            "₹" + totalExpense;

        document.getElementById("balance").innerText =
            "₹" + balance;

    } catch (error) {
        console.log("Dashboard loading error:", error);
    }
}

loadDashboard();