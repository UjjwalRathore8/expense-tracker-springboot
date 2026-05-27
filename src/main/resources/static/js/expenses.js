const token = localStorage.getItem("token");

if (!token) {
    window.location.href = "/html/login.html";
}

const expenseForm = document.getElementById("expenseForm");

async function loadCategories() {

    const response = await fetch("/api/categories", {
        headers: {
            "Authorization": `Bearer ${token}`
        }
    });

    console.log("CATEGORY STATUS:", response.status);

    if (!response.ok) {
        alert("Cannot load categories");
        return;
    }

    const categories = await response.json();

    const categorySelect =
        document.getElementById("expenseCategory");

    categorySelect.innerHTML =
        `<option value="">Select Category</option>`;

    categories.forEach(category => {

        categorySelect.innerHTML += `

            <option value="${category.id}">
                ${category.name}
            </option>

        `;
    });
}

expenseForm.addEventListener("submit", async (e) => {

    e.preventDefault();

    const title =
        document.getElementById("expenseTitle").value;

    const amount =
        document.getElementById("expenseAmount").value;

    const date =
        document.getElementById("expenseDate").value;

    const categoryId =
        document.getElementById("expenseCategory").value;

    if (!categoryId) {
        alert("Please select category");
        return;
    }

    const response = await fetch("/api/expenses", {

        method: "POST",

        headers: {
            "Content-Type": "application/json",
            "Authorization": `Bearer ${token}`
        },

        body: JSON.stringify({

            title: title,

            amount: Number(amount),

            date: date,

            category: {
                id: Number(categoryId)
            }
        })
    });

    console.log("POST Status:", response.status);

    if (!response.ok) {

        const error = await response.text();

        console.log(error);

        alert("Expense not added");

        return;
    }

    expenseForm.reset();

    await loadExpenses();
});

async function loadExpenses() {

    const response = await fetch("/api/expenses", {
        headers: {
            "Authorization": `Bearer ${token}`
        }
    });

    console.log("GET Status:", response.status);

    if (!response.ok) {
        alert("Cannot load expenses");
        return;
    }

    const expenses = await response.json();

    const wrapper =
        document.getElementById("expenseListWrapper");

    const totalDisplay =
        document.getElementById("totalAmountDisplay");

    const countDisplay =
        document.getElementById("expenseCountDisplay");

    wrapper.innerHTML = "";

    let total = 0;

    if (expenses.length === 0) {

        wrapper.innerHTML = `

            <div class="empty-message">
                ✨ No expenses yet.
            </div>

        `;
    }

    expenses.forEach(expense => {

        total += Number(expense.amount);

        wrapper.innerHTML += `

            <div class="expense-row">

                <div>

                    <div class="expense-title">
                        ${expense.title}
                    </div>

                    <div class="expense-meta">
                        📅 ${expense.date}
                    </div>

                </div>

                <div class="expense-amount">
                    ₹${expense.amount}
                </div>

                <div class="action-group">

                    <button
                        class="delete-btn"
                        onclick="deleteExpense(${expense.id})">
                        Delete
                    </button>

                </div>

            </div>

        `;
    });

    totalDisplay.innerText =
        "₹" + total;

    countDisplay.innerText =
        expenses.length;
}

async function deleteExpense(id) {

    const response = await fetch(`/api/expenses/${id}`, {

        method: "DELETE",

        headers: {
            "Authorization": `Bearer ${token}`
        }
    });

    if (!response.ok) {
        alert("Delete failed");
        return;
    }

    await loadExpenses();
}

loadCategories();

loadExpenses();