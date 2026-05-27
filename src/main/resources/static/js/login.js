const form = document.getElementById("loginForm");

const emailInput =
    document.getElementById("email");

const passwordInput =
    document.getElementById("password");

const messageDiv =
    document.getElementById("message");

form.addEventListener("submit", async (e) => {

    e.preventDefault();

    const email = emailInput.value.trim();

    const password = passwordInput.value;

    if (!email) {

        messageDiv.innerText =
            "Please enter email";

        return;
    }

    if (!password) {

        messageDiv.innerText =
            "Please enter password";

        return;
    }

    try {

        const response = await fetch("/api/login", {

            method: "POST",

            headers: {
                "Content-Type": "application/json"
            },

            body: JSON.stringify({
                email: email,
                password: password
            })
        });

        const data = await response.json();

        console.log(data);

        if(response.ok) {

            localStorage.setItem(
                "token",
                data.token
            );

            localStorage.setItem(
                "userId",
                data.id
            );

            localStorage.setItem(
                "expense_user_email",
                email
            );

            messageDiv.style.color = "green";

            messageDiv.innerText =
                "Login successful";

            setTimeout(() => {

                window.location.href =
                    "/html/dashboard.html";

            }, 1000);

        } else {

            messageDiv.style.color = "red";

            messageDiv.innerText =
                data.message || "Login failed";
        }

    } catch(error) {

        console.log(error);

        messageDiv.style.color = "red";

        messageDiv.innerText =
            "Server Error";
    }
});