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

/**
 * 登录
 * **/
function login() {
    let username = $("#userName").val();
    let password = $("#password").val();
    //json形式存储
    let user = {
        username: username,
        password: password
    }
    let url = "http://localhost:8081/student/login";
    ajaxGet(url, "post", user,
        function (resp) {
            console.log("请求成功", resp);
            isSuccess(resp);
        }, function (err) {
            console.log("请求失败", err)
        })
}

// let userIsOn = []
function isSuccess(resp) {
    console.log(resp)
    if (resp.result === "1") {
        alert("登录成功");
        window.sessionStorage.setItem("onLineUser", resp.nickname);
        location.href = 'http://localhost:8081/student/static/html/frontPage.html';
    }
    if (resp.result === "-1") {
        alert("该账号已经登录");
        location.href = 'http://localhost:8081/student/static/html/frontPage.html';
    }
    if (resp.result === "0") {
        alert("账号或密码错误");
    }
}

/**
 * 页面滑动效果
 * **/
// 监听滑动
function listening() {
    const login = document.getElementById("login_div");
    const register = document.getElementById("register_div");
    const cover = document.getElementById("id_cover");
    // ("#id_cover").add;
    // cover.classList.replace("cover","newCover");
    //左滑
    const bt = document.getElementById("btOfCover");
    if (bt.value === "Register") {
        cover.style.animation = "moveLeft 1s";
        // cover.style.marginLeft = "-30px";
        cover.style.transform = "translate(-150%,-50%)"
        login.style.animation = "shrink 1s";
        register.style.animation = "enlarge 1s";

        register.style.width = "400px";
        register.style.display = "inline";
        login.style.width = "200px";
        // bt.style.content=;
        bt.value = "Login";
    } else {
        cover.style.animation = "moveRight 1s";
        // cover.style.marginLeft = "370px";
        cover.style.transform = "translate(50%,-50%)"
        register.style.animation = "shrink 1s";
        login.style.animation = "enlarge 1s";
        login.style.width = "400px";
        login.style.display = "inline";
        register.style.width = "200px";
        // bt.style.content="Register";
        bt.value = "Register";
    }
    bt.onclick = function () {
        listening();
    };
    cover.innerHTML;
}

/**
 * 注册
 * **/
//正则表达式表单验证标准
const emailStandard = /^[\dA-z]{6,10}@[a-zA-Z\d]+\.([a-zA-Z]{2,4})$/;
const userNameStandard = /^[\da-zA-Z]{3,15}$/;
const pwdStandard = /^[a-zA-Z\d]{6,20}$/;
const nickNameStandard = /^[\u4e00-\u9fa5a-zA-Z\d]{2,20}$/;

function register() {
    //获取值
    let username = document.getElementById("user_name").value;
    let nickname = document.getElementById("nick_name").value;
    let email = document.getElementById("email").value;
    let first_pwd = document.getElementById("first_pwd").value;
    let second_pwd = document.getElementById("sec_pwd").value;

    //检查格式
    if (checkUserName(username) && checkNickName(nickname)
        && checkEmail(email) && checkPassword(first_pwd, second_pwd)) {
        let user = {
            nickname: nickname,
            username: username,
            email: email,
            password: second_pwd,
        };
        let url = "http://localhost:8081/student/register"
        ajaxGet(url, "post", user,
            function (resp) {
                console.log("连接成功", resp)
                //    HashMap<String,String>
                if (resp.result === "1") {
                    alert("注册成功");
                    //清空输入框
                    document.getElementById('user_name').value = "";
                    document.getElementById('nick_name').value = "";
                    document.getElementById('email').value = "";
                    document.getElementById('first_pwd').value = "";
                    document.getElementById('sec_pwd').value = "";
                } else {
                    alert(resp.result);
                }
            },
            function (err) {
                console.log("连接失败", err)
            })

    }
}

//检查用户名格式
function checkUserName(userName) {
    if (!userNameStandard.test(userName)) {
        alert("Username does not conform to the format! Please re-enter!");
        return false;
    }
    return true;
}

function checkNickName(nickName) {
    //检查昵称格式
    if (!nickNameStandard.test(nickName)) {
        alert("The nickname format does not conform to the format! Please re-enter!");
        return false;
    }
    return true;
}

function checkEmail(email) {
    //检查邮箱格式
    if (!emailStandard.test(email)) {
        alert("Mailbox doesn't match the format! Please re-enter!");
        return false;
    }
    return true;
}

function checkPassword(first_pwd, second_pwd) {
    //检查密码格式
    if (!pwdStandard.test(first_pwd)) {
        alert("The password does not conform to the format! Please re-enter!");
        return false;
    } else {
        if (first_pwd !== second_pwd) {
            alert("The password entered twice is not the same!");
            return false;
        }
    }
    return true;
}

/**
 * 退出登录
 * **/
if (document.getElementById("log_out"))
    document.getElementById("log_out").addEventListener("click", () => {
        logout();
    })

function logout() {
    if (confirm("Dear " + window.sessionStorage.getItem("onLineUser") + " , Are you sure to logout?")) {
        let url = "http://localhost:8081/student/logout";
        ajaxGet(url,"get",null,
            function (resp){
            if (resp.result === "1"){
                alert("拜拜，记得想我！！！");
                window.sessionStorage.removeItem("onLineUser");
                window.location.href = 'http://localhost:8081/student/';
            }else{
                console.log("退出失败");
            }
            },
            function (err){
            console.log("错误",err);
            })

    }
}


//改密码
function change(num){
    let changePwd = document.getElementById("changePwd");
    let login = document.getElementById("log");
    if (num === 0){
        changePwd.style.animation="out 2s";
        login.style.animation="in 2s";
        changePwd.style.zIndex="2";
        login.style.zIndex="-1";

    }
    else {
        login.style.animation="out 1s";
        changePwd.style.animation="in 1s";
        login.style.zIndex="2";
        changePwd.style.zIndex="-1";
    }
}
//
$("#code_img").click(function () {
    // 在事件响应的 function 函数中有一个 this 对象。这个 this 对象，是当前正在响应事件的 dom 对象
    // src 属性表示验证码 img 标签的 图片路径。它可读，可写
    this.src = "http://localhost:8081/student/kaptcha.jpg?d=" + new Date();
});
//change password
//请求后端生成验证码传回，存到session，生成验证码图片

function changePwd(){
        //根据email找到用户更改密码
        let newPwd = document.getElementById("new_pwd").value;
        let email = document.getElementById("email_check").value;
        let v_code = document.getElementById("v_code").value;
        if(checkPassword(newPwd,newPwd)&&checkEmail(email)){
            let changeData = {
                email:email,
                newPwd:newPwd,
                v_code:v_code
            }
            let url = "http://localhost:8081/student/change";
            ajaxGet(url,"get",changeData,
                function (resp){
                    alert(resp.result);
                },
                function (err){
                    console.log("错误",err);
                })
        }
}
