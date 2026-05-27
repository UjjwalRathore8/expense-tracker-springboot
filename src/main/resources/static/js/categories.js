const token = localStorage.getItem("token");

if (!token) {
    window.location.href = "/html/login.html";
}

const categoryForm = document.getElementById("categoryForm");
const categoryList = document.getElementById("categoryList");
const categoryCount = document.getElementById("categoryCount");
const nameInput = document.getElementById("name");

categoryForm.addEventListener("submit", async (e) => {
    e.preventDefault();

    const name = nameInput.value.trim();

    if (name === "") {
        alert("Category name is required");
        return;
    }

    const response = await fetch("/api/categories", {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
            "Authorization": `Bearer ${token}`
        },
        body: JSON.stringify({
            name: name
        })
    });

    console.log("POST Status:", response.status);

    if (!response.ok) {
        const error = await response.text();
        console.log(error);
        alert("Category not added");
        return;
    }

    nameInput.value = "";
    loadCategories();
});

async function loadCategories() {
    const response = await fetch("/api/categories", {
        headers: {
            "Authorization": `Bearer ${token}`
        }
    });

    console.log("GET Status:", response.status);

    if (!response.ok) {
        categoryList.innerHTML = `<div class="empty-message">Unable to load categories</div>`;
        return;
    }

    const categories = await response.json();

    categoryCount.innerText = categories.length;

    if (categories.length === 0) {
        categoryList.innerHTML = `<div class="empty-message">No categories found</div>`;
        return;
    }

    categoryList.innerHTML = "";

    categories.forEach(category => {
        categoryList.innerHTML += `
            <div class="category-item">
                <div class="category-info">
                    <span class="category-name">📌 ${category.name}</span>
                    <span class="category-meta">ID: ${category.id}</span>
                </div>

                <div class="category-actions">
                    <button class="edit-btn" onclick="updateCategory(${category.id}, '${category.name}')">
                        ✏️ Rename
                    </button>

                    <button class="delete-btn" onclick="deleteCategory(${category.id})">
                        🗑 Delete
                    </button>
                </div>
            </div>
        `;
    });
}

async function deleteCategory(id) {
    const confirmDelete = confirm("Delete this category?");

    if (!confirmDelete) {
        return;
    }

    const response = await fetch(`/api/categories/${id}`, {
        method: "DELETE",
        headers: {
            "Authorization": `Bearer ${token}`
        }
    });

    console.log("DELETE Status:", response.status);

    if (!response.ok) {
        const error = await response.text();
        console.log(error);
        alert("Category not deleted");
        return;
    }

    loadCategories();
}

async function updateCategory(id, oldName) {
    const newName = prompt("Enter new category name", oldName);

    if (newName === null || newName.trim() === "") {
        return;
    }

    const response = await fetch(`/api/categories/${id}`, {
        method: "PUT",
        headers: {
            "Content-Type": "application/json",
            "Authorization": `Bearer ${token}`
        },
        body: JSON.stringify({
            name: newName.trim()
        })
    });

    console.log("PUT Status:", response.status);

    if (!response.ok) {
        const error = await response.text();
        console.log(error);
        alert("Category not updated");
        return;
    }

    loadCategories();
}

loadCategories();