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
        <td>${lottery.usersQty}</td>
        <td>${lottery.createdDate}</td>
        <td>${lottery.endDate}</td>
        <td>${lottery.regStatus === true ? "Open" : "Close"}</td>
        <td>
            <input id="regStatus" class="form-control" type="text" placeholder=" Enter Lottery ID">
            <button class="btn btn-primary" onclick="closeReg(${lottery.id})" 
                    ${lottery.open === false ? "disabled" : ""}>Close Registration</button>
            
        </td>
        <td>
            <input id="regStatus" class="form-control" type="text" placeholder=" Enter Lottery ID">
            <button class="btn btn-primary" onclick="lotteryWinner(${lottery.id})" 
                    >Choose winner</button>
        </td>
        
    `;
    document.getElementById("table-body").appendChild(tr);
}

function closeReg(id) {

    fetch("/stop-registration", {
        method: "post",
        body: JSON.stringify({
            id: id
        }),
        headers: {
            "Content-Type": "application/json;charset=UTF-8"
        }
    }).then(resp => resp.json())
        .then(response => {
            if (response.status === "OK") {
                window.location.reload();
            } else {
                alert("Reason: " + response.reason);
            }
        });
}

function lotteryWinner(id) {

    fetch("/choose-winner", {
        method: "post",
        body: JSON.stringify({
            id: id
        }),
        headers: {
            "Content-Type": "application/json;charset=UTF-8"
        }
    }).then(resp => resp.json())
        .then(response => {
            if (response.status === "OK") {
                window.location.reload();
            } else {
                alert("Reason: " + response.reason);
            }
        });
}



function createLottery() {
    const title = document.getElementById("title").value;
    const limit = document.getElementById("limit").value;


    fetch("/start-registration", {
        method: "post",
        body: JSON.stringify({
            title: title,
            limit: limit
        }),
        headers: {
            "Content-Type": "application/json;charset=UTF-8"
        }
    }).then(() => {
        window.location.href = "/lotteryAdmin.html";
    });
}

function loadStatistics() {
    fetch("/stats", {
        method: "get"
    }).then(
        resp => resp.json()
    ).then(statistics => {
        for(const stats of statistics) {
            statisticsLoad(stats);
        }
    });
}

function statisticsLoad(stats) {
    const tr =document.createElement("tr");
    tr.innerHTML =`
        <td>${stats.id}</td>
        <td>${stats.title}</td>
        <td>${stats.limit}</td>
        <td>${stats.usersQty === null ? "0" : stats.usersQty}</td>
        <td>${stats.createdDate.substring(0, 10)}  ${stats.createdDate.substring(12, 19)}</td>
        <td>${stats.endDate === null ? "on Air" : stats.endDate.substring(0, 11) + " " + stats.endDate.substr(12,8)}</td>
        <td>${stats.regStatus === true ? "Open" : "Close"}</td>
        <td>${stats.winnerCode === null ? "Pending..": stats.winnerCode}</td>
      
        
    `;
    document.getElementById("table-body").appendChild(tr);
}

