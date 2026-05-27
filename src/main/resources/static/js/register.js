const form = document.getElementById("registerForm");

form.addEventListener("submit", async (e) => {

    e.preventDefault();

    const name =
        document.getElementById("name").value;

    const email =
        document.getElementById("email").value;

    const password =
        document.getElementById("password").value;

    try {

        const response = await fetch("/api/users", {

            method: "POST",

            headers: {
                "Content-Type": "application/json"
            },

            body: JSON.stringify({
                name: name,
                email: email,
                password: password
            })
        });

        const data = await response.json();

        if(response.ok) {

            document.getElementById("message").innerText =
                "Registration Successful";

            window.location.href =
                "/html/login.html";

        } else {

            document.getElementById("message").innerText =
                data.message || "Registration Failed";
        }

    } catch(error) {

        document.getElementById("message").innerText =
            "Server Error";
    }
});