const express = require("express");
const app = express()
const cp = require("child_process");
const fs = require("fs");
app.use(express.static("Microsoft_frontend"));
app.use(express.json());
app.set('views', 'view')
app.set('view engine', 'ejs')
// let matrix = [];
let algoType = null, isDiagonal = null, typeHeuristic = null;
app.get("/grid", function (req, res) {
    res.render("grid.ejs")
})

app.post("/grid", function (req, res) {
    algoType = req.body.algoType;
    isDiagonal = req.body.isDiagonal;
    typeHeuristic = req.body.typeHeuristic;
    console.log(algoType + " " + isDiagonal + " " + typeHeuristic);
    res.json({
        status: "res.json"
    });
})

app.post("/solve", function (req, res) {
    // 2. JSon input

    let { src, dest, obstacles } = req.body;
    let dataString = `${src} ${dest} ${obstacles} ${algoType} ${isDiagonal} ${typeHeuristic}                
    `;
    fs.writeFileSync("./Microsoft_backend/file.txt", dataString);
    // console.log(dataString)
let parent=    cp.spawnSync("javac", ["mainfun.java"], { encoding: 'utf8', cwd: "Microsoft_backend" });

    console.log("Complied");
    if(parent.error) {
        console.log("ERROR: ",parent.error);
    }
    console.log("stdout: ",parent.stdout);
    console.log("stderr: ",parent.stderr);
    console.log("exist code: ",parent.status);
    let child = cp.spawnSync("java", ["mainfun"], { encoding: 'utf8', cwd: "Microsoft_backend" });
    if(child.error) {
        console.log("ERROR: ",child.error);
    }
    console.log("stdout: ",child.stdout);
    console.log("stderr: ",child.stderr);
    console.log("exist code: ",child.status);
    res.json({ result: child.stdout });

})

app.listen(3000, function () {
    console.log("Server listening at port 3000");
});
