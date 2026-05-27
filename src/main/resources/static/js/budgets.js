const token = localStorage.getItem("token");

if (!token) {
    window.location.href = "/html/login.html";
}

const form = document.getElementById("budgetForm");
const categorySelect = document.getElementById("categoryId");

form.addEventListener("submit", async (e) => {

    e.preventDefault();

    const limitAmount =
        document.getElementById("limitAmount").value;

    const month =
        document.getElementById("month").value;

    const categoryId =
        categorySelect.value;

    await fetch("/api/budgets", {

        method: "POST",

        headers: {
            "Content-Type": "application/json",
            "Authorization": `Bearer ${token}`
        },

        body: JSON.stringify({

            limitAmount: Number(limitAmount),

            month: month,

            category: {
                id: Number(categoryId)
            }
        })
    });

    form.reset();

    await loadBudgets();
});

async function loadCategories() {

    const response = await fetch("/api/categories", {

        headers: {
            "Authorization": `Bearer ${token}`
        }
    });

    const categories = await response.json();

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

async function loadBudgets() {

    const response = await fetch("/api/budgets/summary", {

        headers: {
            "Authorization": `Bearer ${token}`
        }
    });

    const budgets = await response.json();

    const budgetList =
        document.getElementById("budgetList");

    budgetList.innerHTML = "";

    document.getElementById("budgetCount").innerText =
        budgets.length;

    let totalLimit = 0;

    budgets.forEach(budget => {

        totalLimit += Number(budget.limitAmount);

        const cardClass =
            budget.status.toLowerCase().replace(" ", "-");

        const remainingClass =
            budget.remaining < 0
                ? "remaining-red"
                : "remaining-green";

        const statusClass =
            budget.status === "Exceeded"
                ? "status-exceeded"
                : budget.status === "Near Limit"
                ? "status-near"
                : "status-safe";

        budgetList.innerHTML += `

            <div class="budget-card ${cardClass}">

                <h3>
                    ${budget.categoryName}
                </h3>

                <p>
                    Month:
                    <span class="amount">
                        ${budget.month}
                    </span>
                </p>

                <p>
                    Limit:
                    <span class="amount">
                        ₹${budget.limitAmount}
                    </span>
                </p>

                <p>
                    Spent:
                    <span class="amount">
                        ₹${budget.spent}
                    </span>
                </p>

                <p>
                    ${budget.remaining < 0
                        ? "Exceeded By"
                        : "Remaining"}:

                    <span class="${remainingClass}">
                        ₹${Math.abs(budget.remaining)}
                    </span>
                </p>

                <p>
                    Status:

                    <span class="${statusClass}">
                        ${budget.status}
                    </span>
                </p>

            </div>

        `;
    });

    document.getElementById("totalLimit").innerText =
        "₹" + totalLimit;
}

loadCategories();

loadBudgets();