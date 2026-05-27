const token = localStorage.getItem("token");
const userId = localStorage.getItem("userId");

if (!token || !userId) {
    window.location.href = "/html/login.html";
}

const form = document.getElementById("incomeForm");

form.addEventListener("submit", async (e) => {
    e.preventDefault();

    const source = document.getElementById("source").value;
    const amount = document.getElementById("amount").value;
    const date = document.getElementById("date").value;

    const response = await fetch("/api/incomes", {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
            "Authorization": `Bearer ${token}`
        },
        body: JSON.stringify({
            source: source,
            amount: amount,
            date: date,
            user: {
                id: Number(userId)
            }
        })
    });

    console.log("POST Status:", response.status);

    if (!response.ok) {
        alert("Income not added. Status: " + response.status);
        return;
    }

    form.reset();
    loadIncomes();
});

async function loadIncomes() {
    const response = await fetch("/api/incomes", {
        headers: {
            "Authorization": `Bearer ${token}`
        }
    });

    console.log("GET Status:", response.status);

    if (!response.ok) {
        alert("Cannot load incomes. Status: " + response.status);
        return;
    }

    const incomes = await response.json();

    const incomeList = document.getElementById("incomeList");
    incomeList.innerHTML = "";

    let total = 0;

    if (incomes.length === 0) {
        incomeList.innerHTML =
            `<div class="empty-state">📭 No income recorded.</div>`;
    }

    incomes.forEach(income => {
        total += income.amount;

        incomeList.innerHTML += `
            <div class="income-item">
                <div>
                    <div class="income-title">${income.source}</div>
                    <div class="income-date">${income.date}</div>
                </div>

                <div class="income-amount">₹${income.amount}</div>
            </div>
        `;
    });

    document.getElementById("totalIncome").innerText = "₹" + total;
    document.getElementById("incomeCount").innerText = incomes.length;
}

loadIncomes();