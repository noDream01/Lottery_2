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
        
        <td>${lottery.title}</td>
        <td>${lottery.limit}</td>
        <td>${lottery.createdDate.substring(0, 10)}  ${lottery.createdDate.substring(12, 19)}</td>
        <td>${lottery.endDate === null ? "on Air" : lottery.endDate.substring(0, 11) + " " + lottery.endDate.substr(12,8)}</td>
        <td>${lottery.regStatus === true ? "Open" : "Close"}</td>
        <td>

            <!--<button class="btn btn-primary" onclick=window.location.href = -->
                    <!--&gt;register</button>-->
            <button class="btn btn-primary" onclick="registerUser(${lottery.id})" 
                    >User Registration</button>
        </td>
        <td>

            <button class="btn btn-primary" onclick="statsUser(${lottery.id})" 
                    >Get stats</button>
        </td>


    `;
    document.getElementById("table-body").appendChild(tr);
}

function createUser() {
    // const assignedLotteryId = new URL(window.location.href).searchParams.get("assignedLotteryId");
    const email = document.getElementById("email").value;
    const age = document.getElementById("age").value;
    const code = document.getElementById("code").value;
    const id = new URL(window.location.href).searchParams.get("id");

    fetch("/register", {
        method: "post",
        body: JSON.stringify({
            assignedLotteryId: id,
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
                alert("You are successfully registered")
                window.location.href = "/lotteryList.html";

            } else {
                alert("Reason: " + response.reason);
            }
        });


}

function registerUser(id) {
    window.open("/newParticipant.html?id=" + id,"_self");
}

function statsUser(id) {
    window.open("/lotteryResult.html?id=" + id,"_self");
}

function userLotStatus() {
    const id = new URL(window.location.href).searchParams.get("id");
    const email = document.getElementById("email").value;
    const code = document.getElementById("code").value;

    fetch("/status?id=" + id + "&email=" + email + "&code=" + code, {
        method: "get"
    }).then(
        resp => resp.json()
    ).then(response => {
        if (response.status === "WINNER") {
            alert("CONGRATULATIONS!!! You are a winner!!!")
            window.location.href = "/lotteryList.html";

        }else if(response.status === "PENDING"){
            alert("Lottery in process: Winner soon will be appointed")
            window.location.href = "/lotteryList.html";
        } else {
            alert("Reason: " + response.reason);
            window.location.href = "/lotteryList.html";
        }
    });
    
}

