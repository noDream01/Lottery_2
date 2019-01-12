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
        
    `;
    document.getElementById("table-body").appendChild(tr);
}

function createLottery() {
    const title = document.getElementById("title").value;
    const limit = document.getElementById("limit").value;

    fetch("/start-registration", {
        method: "post",
        body: JSON.stringify({
            title: title,
            limit: limit,
        }),
        headers: {
            "Content-Type": "application/json;charset=UTF-8"
        }
    }).then(() => {
        window.location.href = "/lotteryAdmin.html";
    });
}