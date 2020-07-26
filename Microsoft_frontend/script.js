
let allCells = document.querySelectorAll("#grid .cell");
let dest;
let src;
let obstacles = [];
for (let i = 0; i < allCells.length; i++) {
    allCells[i].addEventListener("click", function () {
        let rid = Number(allCells[i].getAttribute("rid"));
        let cid = Number(allCells[i].getAttribute("cid"));
        if (!src) {
            allCells[i].style.backgroundColor = "#2ecc71"
            src = [rid, cid];

        } else if (!dest) {
            allCells[i].style.backgroundColor = "#e74c3c"
            dest = [rid, cid];

        } else {
            allCells[i].style.backgroundColor = "#bdc3c7"
            obstacles.push([rid, cid]);
        }
    })
}
let search = document.querySelector(".search");
let nxtPage = document.querySelector(".next-page");

if (search) {
    search.addEventListener("click", async function (e) {
        e.preventDefault();
        if (dest && src && obstacles) {
            let res = await axios.post("/solve", { src, dest, obstacles });
            let input = res.data.result;
            console.log(input)

            let inputTemp = input.split(",")
            inputTemp.pop();
            input = inputTemp.join(",");
            let inputArr=input.split(" ");

            //3.  loop 
           
            for (let i = 0; i < inputArr.length; i++) {
                let rcArr = inputArr[i].split(",");
                if(rcArr[0]==dest[0]&&rcArr[1]==dest[1]){
                    break;
                }
                document.querySelector(`#grid .cell[rid="${rcArr[0]}"][cid="${rcArr[1]}"]`).style.backgroundColor = "#2980b9";
            }
        } else {
            alert("Please input src or dest")
        }
    })
}
if (nxtPage) {
    // 1.
    // select
    let allAlgoType = document.querySelectorAll(".algotype");
    let allTypeHeuristic = document.querySelectorAll(".typeHeuristic");
    let allIsDiagonal = document.querySelectorAll(".isdiagonal");
    let algoType;
    let isDiagonal;
    let typeHeuristic;
    for (let i = 0; i < allAlgoType.length; i++) {
        allAlgoType[i].addEventListener("click", function (e) {
            algoType = e.currentTarget.getAttribute("value");
        })
    }

    for (let i = 0; i < allIsDiagonal.length; i++) {
        allIsDiagonal[i].addEventListener("click", function (e) {
            isDiagonal = e.currentTarget.getAttribute("value");
        })
    }

    for (let i = 0; i < allTypeHeuristic.length; i++) {
        allTypeHeuristic[i].addEventListener("click", function (e) {
            typeHeuristic = e.currentTarget.getAttribute("value");
        })
    }

    nxtPage.addEventListener("click", async function (e) {
        e.preventDefault();
        // value 
        if (typeHeuristic == undefined) {
            typeHeuristic = false;
        }
        console.log(algoType, typeHeuristic, isDiagonal);
        await axios.post("/grid", { algoType, typeHeuristic, isDiagonal });
        location.assign("/grid");
    })

}