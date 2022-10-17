//导入ajaxGet方法
function ajaxGet(url, type, data, success, error) {
    $.ajax({
        url: url,
        type: type,
        data: data,
        dataType: "json",
        success: success,
        error: error
    })
}


let students;
let numOfStudent;
let numPage;
let numOfThisPage = 0;
let currentPage = 1;
const showUrl = "http://localhost:8081/student/show";
const addUrl = "http://localhost:8081/student/add";
const deleteUrl = "http://localhost:8081/student/delete";
const updateUrl = "http://localhost:8081/student/update";
const quireUrl = "http://localhost:8081/student/quire";

// let allStudents = [];
window.onload = (function () {
    began();
});

function began() {
    //获取全部学生，保存到一个数组
    let need = {
        beginIndex: "0",
        num: "all"
    }
    ajaxGet(showUrl, "get", need,
        function (resp) {
            console.log(resp);
            students = resp;
            init();
            showPage(currentPage);
        },
        function (err) {
            console.log("错误", err);
        })
}

function init() {
    // students = allStudents;
    //学生总数
    numOfStudent = students.length;
    //向上取整
    numPage = Math.ceil(numOfStudent / 10);
    if (numPage === 0) numPage = 1;
}

function showPage(page) {

    //清空已经有的行
    let rows = table.getElementsByTagName("tr");
    if (rows.length !== 0)
        for (let i = 1; i < numOfThisPage + 1; i++) {
            table.removeChild(rows[1]);
        }
    if (numOfStudent === 0) return;

    //注意“!=”和“!=="的区别
    if (page != numPage) numOfThisPage = 10;
    else if (numOfStudent % 10 === 0) numOfThisPage = 10;
    else numOfThisPage = numOfStudent % 10;
    console.log("numOfThisPage" + numOfThisPage)
    console.log("page:" + page)
    console.log("numPage:" + numPage);
    console.log("")
    if (page > numPage && currentPage > 1) {
        currentPage--;
        page--;
    }
    console.log(students)
    console.log("page:" + page)
    for (let i = (page - 1) * 10; i < (page - 1) * 10 + numOfThisPage; i++) {
        console.log(i)
        createRow(students[i].id, students[i].name, students[i].gender, students[i].telephoneNumber, students[i].specialize);
    }
    currentPage = parseInt(page);
    pageField.value = currentPage + "/" + numPage;
    //    获取当页选择框
    document.getElementById("select_all").checked = false;
    deleteBoxes = document.getElementsByName("select_box");
    listenDelete();
    listenUpdate();
    listenQuire();
    listenSelect();
}

let addBox = document.getElementById("add_box");
document.getElementById("add").addEventListener("click", () => {
    addBox.style.display = "inline";
    document.getElementById("mask").style.display = "inline-block";
    upInsert = -1;
})


document.getElementById("ok").addEventListener("click", () => {
    if (upInsert === -1) {
        if (addStudent(document.getElementById("id").value,
            document.getElementById("name").value,
            gender,
            document.getElementById("telephoneNumber").value,
            document.getElementById("specialize").value)) {
            document.getElementById("mask").style.display = "none";
            addBox.style.display = "none";
            init("");
            showPage(currentPage);
        }
    } else {
        let oldId;
        let id = document.getElementById("id").value;
        let name = document.getElementById("name").value;
        getGender();
        let telephoneNumber = document.getElementById("telephoneNumber").value;
        let specialize = document.getElementById("specialize").value;
        if (checkUpdateId(id, upInsert) && checkName(name) && checkGender(gender) && checkTele(telephoneNumber) && checkSpecialize(specialize)) {
            console.log("insert " + upInsert);
            oldId = students[upInsert].id;
            students[upInsert].id = id;
            students[upInsert].name = name;
            students[upInsert].gender = gender;
            students[upInsert].telephoneNumber = telephoneNumber;
            students[upInsert].specialize = specialize;

            let updateData = {
                tagId: oldId,
                id: id,
                name: name,
                gender: gender,
                telephoneNumber: telephoneNumber,
                specialize: specialize,
            }
            ajaxGet(updateUrl, "get", updateData,
                function (resp) {
                    alert(resp.result);
                },
                function (err) {
                    console.log("错误", err)
                })
            showPage(currentPage);
            addBox.style.display = "none";
            document.getElementById("mask").style.display = "none";
        }
    }
})


let gender;
let genders = document.getElementsByName("gender");
document.getElementById("reset").addEventListener("click", () => {
    genders[0].checked = false;
    genders[1].checked = false;
    getGender();
})

function getGender() {
    let man = genders[0];
    let woman = genders[1];
    if (man.checked) {
        gender = "男";
        document.getElementById("man_img").style.color = "blue";
        document.getElementById("woman_img").style.color = "black"
    } else if (woman.checked) {
        gender = "女";
        document.getElementById("woman_img").style.color = "red";
        document.getElementById("man_img").style.color = "black";
    } else {
        document.getElementById("woman_img").style.color = "black";
        document.getElementById("man_img").style.color = "black";
        gender = "";
    }
}

// function getGender(){
//
//     for (let i = 0; i < genders.length; i++) {
//         if (genders[i])
//     }
// }
//获取table标签
let table = document.getElementById("table");

function addStudent(id, name, gender, telephoneNumber, specialize) {
    if (checkAddId(id) && checkName(name) && checkGender(gender) && checkTele(telephoneNumber) && checkSpecialize(specialize)) {
        init();
        //加到临时数组
        let student = {
            id: id,
            name: name,
            gender: gender,
            telephoneNumber: telephoneNumber,
            specialize: specialize
        }
        students.push(student)
        //加到数据库
        ajaxGet(addUrl, "get", student,
            function (resp) {
                alert(resp.result);
            },
            function (err) {
                console.log("错误", err)
            })

        numOfStudent++;

        if (numOfThisPage === 10) {
            init();
            showPage(numPage);
            return true;
        }
        createRow(id, name, gender, telephoneNumber, specialize);
        numOfThisPage++;
        return true;
    }
    return false;
}

document.getElementById("exit_add").addEventListener("click", () => {
    document.getElementById("add_box").style.display = "none";
    document.getElementById("mask").style.display = "none";
})
//页码监听
let pageField = document.getElementById("page")
pageField.addEventListener("change", () => {
    // if (e.keyCode===13){
    if (pageField.value > 0) {
        if (pageField.value <= numPage) {
            init("");
            showPage(pageField.value);
        } else {
            alert("没有这么多页");

        }
    } else {
        alert("输入格式有误");
    }
    pageField.value = currentPage;
    // }
    // e.preventDefault();
})
document.getElementById("page_up").addEventListener("click", () => {
    if (currentPage > 1) {
        init("");
        showPage(currentPage - 1);
    }
});
document.getElementById("page_down").addEventListener("click", () => {
    if (currentPage < numPage) {
        init("");
        showPage(currentPage + 1);
    }
});

//生成格子<td>(不止td)
function createLabel(type, value) {
    let newLabel = document.createElement(type);
    let newValue = document.createTextNode(value);

    newLabel.appendChild(newValue);

    return newLabel;
}

// function addChildElement(parentElement,childElement){
//
// }
function createRow(id, name, gender, telephoneNumber, specialize) {
    let newRow = document.createElement("tr");
    newRow.name = "row";
    //单选框---删除
    let selectBox = document.createElement("input");
    selectBox.type = "checkBox";
    selectBox.name = "select_box";
    selectBox.checked = false;

    newRow.appendChild(selectBox);
    newRow.appendChild(createLabel("td", id));
    newRow.appendChild(createLabel("td", name));
    newRow.appendChild(createLabel("td", gender));
    newRow.appendChild(createLabel("td", telephoneNumber));
    newRow.appendChild(createLabel("td", specialize));
//    按钮
    newRow.appendChild(document.createElement("td").appendChild(createBt()));
    table.appendChild(newRow);
    table.innerHTML;

}

function createBt() {
    let div = document.createElement("div");
    let btQuire = document.createElement("input")
    let btUpdate = document.createElement("input");
    let btDelete = document.createElement("input");

    div = addClass(div, "bt_delete_update");
    btUpdate.type = "button";
    btUpdate.value = "修改";
    btUpdate.name = "update";

    btQuire.type = "button";
    btQuire.value = "查看";
    btQuire.name = "quire";

    btDelete.type = "button";
    btDelete.value = "删除";
    btDelete.name = "delete";
    div.appendChild(btUpdate);
    div.appendChild(btQuire);
    div.appendChild(btDelete)
    return div;
}

function addClass(element, newClassName) {
    if (element.className) {
        let oldClassName = element.className;
        element.className = oldClassName + " " + newClassName;
    } else {
        element.className = newClassName
    }
    return element;
}


const idMatch = /\d{6,11}/;
const nameMatch = /^[^\x00-\xff]{2,5}$|^[a-zA-Z]{1,15}\s?[a-zA-Z]{1,16}$/;
const genderMatch = /^[男|女]$/;
const telephoneNumberMatch = /^\d{6,11}$/;
const specializeMatch = /^[^\x00-\xff]{2,10}$/;

function checkAddId(id) {
    if (!idMatch.test(id)) {
        alert("学号不符合格式")
        return false;
    }
    console.log("add")
    for (let i = 0; i < students.length; i++) {
        if (students[i].id === id) {
            alert("该学号已经存在");
            return false;
        }
    }
    return true;
}

function checkUpdateId(id, ins) {
    if (!idMatch.test(id)) {
        alert("学号不符合格式")
        return false;
    }
    console.log("up")
    init("");
    console.log("ins  " + ins);
    for (let i = 0; i < students.length; i++) {
        if (i === ins) continue;
        if (students[i].id === id) {
            alert("该学号已经存在");
            return false;
        }
    }
    return true;
}

function checkName(name) {
    if (!nameMatch.test(name)) {
        alert("姓名不符合格式")
        return false;
    } else
        return true;
}

function checkGender(gender) {
    if (!gender) {
        alert("您还未选择性别")
        return false;
    } else
        return true;
}

function checkTele(telephoneNumber) {
    if (!telephoneNumberMatch.test(telephoneNumber)) {
        alert("电话号码不符合格式")
        return false;
    } else
        return true;
}

function checkSpecialize(specialize) {
    if (!specialize) {
        alert("您还未选择专业")
        return false;
    } else
        return true;
}

/**
 * 查询
 * @param insert
 * @param id
 */
function listenQuire() {
    let quireBts = document.getElementsByName("quire");
    for (let i = 0; i < quireBts.length; i++) {
        quireBts[i].addEventListener("click", (e) => {
            document.getElementById("quire").style.display = "inline";
            document.getElementById("mask").style.display = "inline";
            let id = e.target.parentNode.parentNode.childNodes[1].firstChild.nodeValue;
            let name = e.target.parentNode.parentNode.childNodes[2].firstChild.nodeValue;
            let gender = e.target.parentNode.parentNode.childNodes[3].firstChild.nodeValue;
            let telephoneNumber = e.target.parentNode.parentNode.childNodes[4].firstChild.nodeValue;
            let specialize = e.target.parentNode.parentNode.childNodes[5].firstChild.nodeValue;

            //    填入
            let fields = document.getElementById("quire").getElementsByTagName("p");
            fields[1].innerHTML = name;
            fields[2].innerHTML = id;
            fields[3].innerHTML = gender;
            fields[4].innerHTML = telephoneNumber;
            fields[5].innerHTML = specialize;
        })
    }
}

document.getElementById("exit_quire").addEventListener("click", () => {
    document.getElementById("quire").style.display = "none";
    document.getElementById("mask").style.display = "none";

})

//删除
function deleteStudent(insert, id) {
    init("");
    let rows = document.getElementsByName("delete");
    let data = {
        id: id
    }
    ajaxGet(deleteUrl, "get", data,
        function (resp) {
            alert(resp.result);
            students.splice(insert, 1);
            if (rows.length === 1) {
                if (currentPage > 1)
                    currentPage--;
                if (numPage > 1)
                    numPage--;
            }
            init("");
            showPage(currentPage);
        },
        function (err) {
            console.log("错误", err)
        })

}

function listenDelete() {
    let rows = document.getElementsByName("delete");
    for (let i = 0; i < rows.length; i++) {
        rows[i].addEventListener("click", (e) => {
            if (confirm("你确定要删除这位学生吗?")) {
                for (let j = 0; j < rows.length; j++) {
                    rows[i].removeEventListener("click", () => {
                    });
                }
                //找下标
                let id = e.target.parentNode.parentNode.childNodes[1].firstChild.nodeValue;
                let insert;
                for (let j = 0; j < students.length; j++) {
                    if (students[j].id === id) insert = j;
                }
                deleteStudent(insert, id);
            }
        })

    }

}

let upInsert;


function listenUpdate() {
    let rows = document.getElementsByName("update");
    for (let i = 0; i < rows.length; i++) {
        rows[i].addEventListener("click", () => {
            upInsert = i + (currentPage - 1) * 10;
            updateStudent(upInsert);
        })
    }
}

function updateStudent(insert) {
    let addBox = document.getElementById("add_box");
    addBox.style.display = "inline";
    document.getElementById("mask").style.display = "inline";
    let inputs = addBox.getElementsByTagName("input");
    let select = document.getElementById("specialize");
    inputs[0].value = students[insert].id;
    inputs[1].value = students[insert].name;
    if (students[insert].gender === "男")
        inputs[2].checked = true;
    else inputs[3].checked = true;
    inputs[4].value = students[insert].telephoneNumber;
    select.value = students[insert].specialize;
}


//magnifier
let search = document.getElementById("search");
let isAccurate = document.getElementById("isAccurate");
isAccurate.addEventListener("click", () => {
    if (isAccurate.checked) {
        alert("精确模式已开启");
    } else {
        alert("精确模式已关闭");
    }
})
//可选择搜索
let centerValue = 0;

function listenSearchType() {
    let bts = document.getElementById("circle_select").getElementsByTagName("li");
    for (let i = 0; i < bts.length; i++) {
        bts[i].addEventListener("click", (e) => {
            document.getElementById("circle_select").setAttribute("center", bts[i].getElementsByTagName("a")[0].childNodes[0].nodeValue)
            centerValue = i + 1;
        })
    }
}

document.getElementById("magnifier").addEventListener("click", () => {
    search.style.width = "100%";
    document.getElementById("circle_select").style.display = "inline";
    listenSearchType();
    search.onblur = () => {
        let searchContent = search.value;
        if (searchContent === "") {
            search.style.width = "0";
            document.getElementById("circle_select").style.display = "none";
        }
    };
    search.addEventListener("input", (e) => {
            let searchContent = search.value;
            let searchType;
            addExit();
            setTimeout(function () {
                switch (centerValue) {
                    case 0: {
                        searchType = "all";
                        break;
                    }
                    case 1: {
                        searchType = "id";
                        break;
                    }
                    case "2": {
                        searchType = "name";
                        break;
                    }
                    case 3: {
                        searchType = "gender";
                        break;
                    }
                    case 4: {
                        searchType = "telephoneNumber";
                        break;
                    }
                    case 5: {
                        searchType = "specialize";
                        break;
                    }
                }

                quire(searchType, searchContent);
            }, 800);
        }
    )

})

function quire(type, value) {
    let quireData = {
        type: type,
        value: value
    }
    ajaxGet(quireUrl, "get", quireData,
        function (resp) {
            students = resp;
            init();
            showPage(1);
        },
        function (err) {
            console.log("错误", err)
        })
}

function addExit() {
    let bt = document.getElementById("bt_exit");
    bt.style.display = "inline";

    bt.addEventListener("click", () => {
        currentPage = 1;
        location.reload();
    })
}

/**
 * 批量删除
 * @type {*[]}
 */
let deleteBoxes = [];
document.getElementById("batch_delete").addEventListener("click", (e) => {
    //自选

    if (deleteBoxes.length !== 0) {
        let hasDelete = false;
        for (let i = 0; i < deleteBoxes.length; i++) {
            if (deleteBoxes[i].checked) {
                hasDelete = true;
                break;
            }
        }
        if (hasDelete) {
            let id;
            if (confirm("你确定删除选中的学生吗？")) {
                // let length = ;
                let index = 0;
                for (let i = 0; i < deleteBoxes.length; i++) {
                    if (deleteBoxes[i].checked) {
                        id = students[i + (currentPage - 1) * 10].id;
                        console.log("id:" + id);

                        length--;
                        index++;
                        // allStudents.splice(i + (currentPage - 1) * 10,1);
                        let data = {
                            id: id
                        }
                        ajaxGet(deleteUrl, "get", data,
                            function (resp) {
                                console.log(resp.result)
                            },
                            function (err) {
                                console.log("错误", err);
                            })
                    }
                }
                // else{
                //     i++;
                //     index++;
                // }

                // index++;
                // i++;

                // //重新加载
                began();
                // init();
                // showPage(currentPage);

            }
        }

    }

})
//全选当页
document.getElementById("select_all").addEventListener("change", (e) => {
    deleteBoxes = document.getElementsByName("select_box");
    if (deleteBoxes.length !== 0) {
        for (let i = 0; i < deleteBoxes.length; i++) {
            deleteBoxes[i].checked = e.target.checked;
        }
    }
})

//监听当页全部选择框
let isSelectAll;

function listenSelect() {
    deleteBoxes = document.getElementsByName("select_box");
    for (let i = 0; i < deleteBoxes.length; i++) {
        deleteBoxes[i].addEventListener("change", (e) => {
            if (e.target.checked) {
                isSelectAll = true;
                for (let j = 0; j < deleteBoxes.length; j++) {
                    if (!deleteBoxes[j].checked) {
                        isSelectAll = false;
                        console.log(isSelectAll)
                        break;
                    }
                }
            } else {
                isSelectAll = false;
            }
            document.getElementById("select_all").checked = isSelectAll;
        })
    }
}


