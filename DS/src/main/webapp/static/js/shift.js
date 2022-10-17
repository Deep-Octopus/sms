// //   function ajaxGet(){
// // //    对象
// //     let req = new XMLHttpRequest();
// //     //请求
// //       let username = $("#userName").val();
// //      let password = $("#password").val();
// //      req.onreadystatechange=function (){
// //           if (req.readyState==4&&req.status==200){
// //               alert("ok")
// //           }
// //       }
// //       let url = "/student/login?username="+username+"&password="+password;
// //     req.open("GET",url,true);
// //     req.send();
// //
// // }
//
// // export const a = 1
// //
// // export {
// //     ajaxGet
// // }
// //
// function ajaxGet(url,type,data,success,error){
//
//     $.ajax({
//         url:url,
//         type:type,
//         data:data,
//         dataType:"json",
//         success:success,
//         error:error
//     })
// }
//
// function login(){
//     let username = $("#userName").val();
//     let password = $("#password").val();
//     //json形式存储
//     let user={
//         username:username,
//         password:password
//     }
//     let url = "http://localhost:8081/student/login";
//     ajaxGet(url,"get",user,
//         function (resp){
//         console.log("请求成功",resp);
//         alert("成功")
//         isSuccess(resp);
//         },function (err){
//         console.log("请求失败",err)
//         })
// }
// function isSuccess(resp){
//     if (resp.result===1){
//         alert("登录成功");
//         location.href = 'http://localhost:8081/student/static/html/frontPage.html';
//     }
//     else {
//         alert("登录失败");
//     }
// }
// // 监听滑动
// function listening() {
//     const login = document.getElementById("login_div");
//     const register = document.getElementById("register_div");
//     const cover = document.getElementById("id_cover");
//     // ("#id_cover").add;
//     // cover.classList.replace("cover","newCover");
//     //左滑
//     const bt = document.getElementById("btOfCover");
//     if (bt.value === "Register") {
//         cover.style.animation = "moveLeft 1s";
//         // cover.style.marginLeft = "-30px";
//         cover.style.transform="translate(-150%,-50%)"
//         login.style.animation = "shrink 1s";
//         register.style.animation = "enlarge 1s";
//
//         register.style.width = "400px";
//         register.style.display = "inline";
//         login.style.width = "200px";
//         // bt.style.content=;
//         bt.value = "Login";
//     } else {
//         cover.style.animation = "moveRight 1s";
//         // cover.style.marginLeft = "370px";
//         cover.style.transform="translate(50%,-50%)"
//         register.style.animation = "shrink 1s";
//         login.style.animation = "enlarge 1s";
//         login.style.width = "400px";
//         login.style.display = "inline";
//         register.style.width = "200px";
//         // bt.style.content="Register";
//         bt.value = "Register";
//     }
//     bt.onclick = function () {
//         listening();
//     };
//     cover.innerHTML;
// }
//
//
// const emailStandard = /^[\dA-z]{6,10}@[a-zA-Z\d]+\.([a-zA-Z]{2,4})$/;
// const userNameStandard = /^[\da-zA-Z]{3,15}$/;
// const pwdStandard = /^[a-zA-Z\d]{6,20}$/;
// const nickNameStandard = /^[\u4e00-\u9fa5a-zA-Z\d]{3,20}$/;
//
//
// //注册
// function register() {
//     if (typeof (Storage) !== "undefined") {
//         let userName = document.getElementById("user_name").value;
//         let nickName = document.getElementById("nick_name").value;
//         let email = document.getElementById("email").value;
//         let first_pwd = document.getElementById("first_pwd").value;
//         let second_pwd = document.getElementById("sec_pwd").value;
//
//         let array;
//         if (window.localStorage.userArr) {//判断是否存在
//             array = JSON.parse(window.localStorage.userArr);
//         } else {
//             array = [];//创建一个新数组
//         }
//
//         if (checkUserName(userName, array) && checkNickName(nickName)
//             && checkEmail(email, array) && checkPassword(first_pwd, second_pwd)) {
//             const obj = {
//                 nickName: nickName,
//                 userName: userName,
//                 email: email,
//                 password: second_pwd,
//                 isOn: false
//             };
//             array.push(obj);
//             window.localStorage.userArr = JSON.stringify(array);
//             alert("Registration successful!");
//             document.getElementById('user_name').value = "";
//             document.getElementById('nick_name').value = "";
//             document.getElementById('email').value = "";
//             document.getElementById('first_pwd').value = "";
//             document.getElementById('sec_pwd').value = "";
//
//         }
//     } else {
//         document.getElementById("result").innerHTML = "Sorry, your browser does not support web storage.";
//     }
// }
//
// //检查用户名格式
// function checkUserName(userName, array) {
//     if (!userNameStandard.test(userName)) {
//         alert("Username does not conform to the format! Please re-enter!");
//         return false;
//     } else {
//         //检查用户名
//         //遍历数组进行匹配
//         for (let i = 0; i < array.length; i++) {
//             //判断是否有相同用户名
//             if (userName === array[i].userName) {
//                 alert("The username already exists");
//                 return false;
//             }
//         }
//     }
//     return true;
// }
//
// function checkNickName(nickName) {
//     //检查昵称格式
//     if (!nickNameStandard.test(nickName)) {
//         alert("The nickname format does not conform to the format! Please re-enter!");
//         return false;
//     }
//     return true;
// }
//
// function checkEmail(email, array) {
//     //检查邮箱格式
//     if (!emailStandard.test(email)) {
//         alert("Mailbox doesn't match the format! Please re-enter!");
//         return false;
//     } else {
//         //检查邮箱
//         //遍历数组进行匹配
//         for (let i = 0; i < array.length; i++) {
//             //判断是否有相同邮箱
//             if (email === array[i].email) {
//                 alert("The email address is already registered!");
//                 return false;
//             }
//         }
//
//     }
//     return true;
// }
//
// function checkPassword(first_pwd, second_pwd) {
//     //检查密码格式
//     if (!pwdStandard.test(first_pwd)) {
//         alert("The password does not conform to the format! Please re-enter!");
//         return false;
//     } else {
//         if (first_pwd !== second_pwd) {
//             alert("The password entered twice is not the same!");
//             return false;
//         }
//     }
//     return true;
// }
//
// // let on_user;
// // let isOn_user;
// //登录
// // function loginooo() {
// //     if (typeof (Storage) !== "undefined") {
// //         const userName = document.getElementById('userName').value;
// //         const password = document.getElementById('password').value;
// //         let array;
// //         if (window.localStorage.userArr) {//判断是否存在
// //             array = JSON.parse(window.localStorage.userArr);
// //         } else {
// //             array = [];//创建一个新数组
// //         }
// //         let isExist = false;//定义一个开关变量
// //         let index = 0; //定义一个下标确定用户
// //         //遍历数组进行匹配
// //         for (let i = 0; i < array.length; i++) {
// //             if (userName === array[i].userName) {//有这个账号
// //                 isExist = true;
// //                 index = i;
// //             }
// //         }
// //         if (isExist) {//如果存在
// //             if (password === array[index].password) {
// //                 alert("Login successful!");
// //                 //跳转
// //                 // on_user = userName;
// //                 setOn(userName,true);
// //                 setTimeout(function (){
// //                     window.location.href = '../html/frontPage.html';
// //                 },500);
// //
// //             } else {
// //                 alert("Wrong password!");
// //             }
// //         } else {//账号不存在或输入错误
// //             alert('The account number does not exist or is entered incorrectly!');
// //         }
// //     }else {
// //         document.getElementById("result").innerHTML = "Sorry, your browser does not support web storage.";
// //     }
// // }
// if (document.getElementById("log_out"))
//     document.getElementById("log_out").addEventListener("click",()=>{
//         logout();
// })
// function logout(){
//     let index = 0;
//     let array = JSON.parse(window.localStorage.userArr);
//     for (let i = 0; i < array.length; i++) {
//         if (array[i].isOn){
//             index = i;
//         }
//     }
//
//     if (confirm("Dear " + array[index].nickName + " , Are you sure to logout?")){
//         setOn(array[index].userName,false);
//         setTimeout(function (){
//
//             window.location.href = '../html/login.html';
//             // window.location.reload();
//         },500);
//
//     }
// }
// //设置登录状态
// function setOn(userName,onCase){
//         let userCase = JSON.parse(window.localStorage.userArr);
//         for (let i = 0; i < userCase.length; i++) {
//             if (userName === userCase[i].userName) {//有这个账号
//                 userCase[i].isOn = onCase;
//             }
//         }
//     window.localStorage.userArr = JSON.stringify(userCase);
// }
//
// function check(num){
//     let array;
//     if (window.localStorage.userArr){
//         array = JSON.parse(window.localStorage.userArr);
//         for (let i = 0; i < array.length; i++) {
//             if (array[i].isOn){
//                 if (num===0)
//                     window.location.href = '../html/frontPage.html';
//                 return;
//             }
//         }
//     }
//     window.location.href = '../html/login.html';
// }
//
//
// function change(num){
//     let changePwd = document.getElementById("changePwd");
//     let login = document.getElementById("log");
//     if (num === 0){
//         changePwd.style.animation="out 2s";
//         login.style.animation="in 2s";
//         changePwd.style.zIndex="2";
//         login.style.zIndex="-1";
//     }
//     else {
//         login.style.animation="out 1s";
//         changePwd.style.animation="in 1s";
//         login.style.zIndex="2";
//         changePwd.style.zIndex="-1";
//     }
// }
//
//
// //change password
// function checkV_code(array,index,newPwd,v_code){
//     if (v_code==="7364"){
//         array[index].password = newPwd;
//         window.localStorage.userArr = JSON.stringify(array);
//         alert("The password change was successful!");
//         let changePwd = document.getElementById("changePwd");
//         let login = document.getElementById("log");
//         login.style.animation="out 1s";
//         changePwd.style.animation="in 1s";
//         login.style.zIndex="2";
//         changePwd.style.zIndex="-1";
//     }else {
//         alert("The verification code is incorrect!");
//     }
// }
// function checkPwdOfChangePwd(array,index,oldPwd,newPwd,v_code){
//     if (array[index].password===oldPwd){
//         if (pwdStandard.test(newPwd)){
//             checkV_code(array,index,newPwd,v_code);
//         }else {
//             alert("The new password is malformed!")
//         }
//     }else {
//         alert("The old password is incorrect!");
//     }
// }
// function checkEmailOfChangePwd(email,oldPwd,newPwd,v_code){
//     if (emailStandard.test(email)){
//         let index = -1;
//         let array;
//         array = JSON.parse(window.localStorage.userArr);
//         for (let i = 0; i < array.length; i++) {
//             if (email===array[i].email){
//                 index = i;
//                 break;
//             }
//         }
//         if (index===-1) {
//             alert("The mailbox doesn't exist!");
//             return;
//         }
//         checkPwdOfChangePwd(array,index,oldPwd,newPwd,v_code);
//     }else{
//         alert("The mailbox is malformed!");
//     }
// }
// function changePwd(){
//     if (typeof (Storage) !== "undefined") {
//         let oldPwd = document.getElementById("old_pwd").value;
//         let newPwd = document.getElementById("new_pwd").value;
//         let email = document.getElementById("email_check").value;
//         let v_code = document.getElementById("v_code").value;
//         if (window.localStorage.userArr) {//判断是否存在
//             checkEmailOfChangePwd(email,oldPwd,newPwd,v_code);
//         }
//         else
//             alert("No account exists!");
//     }
//     else {
//         document.getElementById("result").innerHTML = "对不起，您的浏览器不支持 web 存储。";
//     }
// }