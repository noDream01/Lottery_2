function loadLotteries() {
    fetch("/start-registration", {
        method: "get"
    }).then(
        resp => resp.json()
    ).then(lotteries => {
        for(const lottery of lotteries) {
            addLottery(lottery);
        }
    });
}

function addLottery(lottery) {

    const tr =document.createElement("tr");
    tr.innerHTML =`
        <td>${lottery.id}</td>
        <td>${lottery.title}</td>
        <td>${lottery.limit}</td>
        <td>${lottery.createdDate}</td>
        <td>${lottery.endDate}</td>
        <td>${lottery.regStatus === true ? "Open" : "Close"}</td>
        <td>

            <!--<button class="btn btn-primary" onclick=window.location.href = -->
                    <!--&gt;register</button>-->
            <a href="newParticipant.html">Register</a>
        </td>


    `;
    document.getElementById("table-body").appendChild(tr);
}

function createUser() {
    // const assignedLotteryId = new URL(window.location.href).searchParams.get("assignedLotteryId");
    const email = document.getElementById("email").value;
    const age = document.getElementById("age").value;
    const code = document.getElementById("code").value;
    const assignedLotteryId = document.getElementById("assignedLotteryId").value;

    fetch("/register", {
        method: "post",
        body: JSON.stringify({
            assignedLotteryId: assignedLotteryId,
            email: email,
            age: age,
            code: code
        }),
        headers: {
            "Content-Type": "application/json;charset=UTF-8"
        }
    }).then(resp => resp.json())
        .then(response => {
            if (response.status === "OK") {
                window.location.href = "/lotteryList.html";
            } else {
                alert("Reason: " + response.reason);
            }
        });
}

