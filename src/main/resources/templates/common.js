const user={};
function login()
	{
		var uname = document.getElementById("email").value;
		var pwd = document.getElementById("password").value;
		console.log(uname)
		console.log(pwd)
		postData(uname,pwd)
		
	}
	

async function postData(uname,pwd) {
  const url = "http://localhost:8080/login?email="+uname+"&password="+pwd;
  const response = await fetch(url, {
    method: 'POST', 
    mode: 'cors', 
    cache: 'no-cache', 
    credentials: 'same-origin', 
    headers: {
      'Content-Type': 'application/json'
     
    }
		
})
	console.log(response);
}
		