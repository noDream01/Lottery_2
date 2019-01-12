function loadUsers() {
    fetch("/register", {
        method: "get"
    }).then(
        resp => resp.json()
    ).then(users => {
        for(const user of users) {
            addUser(user);
        }
    });
}


function addUser(user) {
    const tr =document.createElement("tr");
    tr.innerHTML =`
        <td>${user.id}</td>
        <td>${user.email}</td>
        <td>${user.age}</td>
        <td>${user.code}</td>
        
    `;
    document.getElementById("table-body").appendChild(tr);
}

function createUser() {
    const email = document.getElementById("email").value;
    const age = document.getElementById("age").value;
    const code = document.getElementById("code").value;

    fetch("/register", {
        method: "post",
        body: JSON.stringify({
            email: email,
            age: age,
            code: code
        }),
        headers: {
            "Content-Type": "application/json;charset=UTF-8"
        }
    });
}