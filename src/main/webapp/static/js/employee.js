// *** Employee page behaviors ***
// implements viewEmployeeInfo,	[X]
// updateEmployeeInfo,			[X]
// getAllReimbursementStatus,	[X]
// submitReimbursementRequest,	[X]
// and Logout					[]

window.onload = () => {
	console.log("Window onload"); //remove later
	getEmplInfo();
	getReimbInfo();
	document.getElementById("reimbRqst").addEventListener("click", sendReimbRqst);
	document.getElementById("updateInfo").addEventListener("click", updateInfo);
	document.getElementById("logoutClick").addEventListener("click", logoutUser);
}

function getEmplInfo() {
	let xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function(){
		
		if ((xhr.readyState == 4) && (xhr.status == 200)){
			console.log("Got employee info");			
			let row = document.createElement("tr");
			let idCol = document.createElement("td");
			let fnameCol = document.createElement("td");
			let lnameCol = document.createElement("td");
			let emailCol = document.createElement("td");
			
			let obj = JSON.parse(xhr.responseText);
			
			idCol.textContent = obj.jobID;
			fnameCol.textContent = obj.firName;
			lnameCol.textContent = obj.lasName;
			emailCol.textContent = obj.emailAddr;
			
			row.appendChild(idCol);
			row.appendChild(fnameCol);
			row.appendChild(lnameCol);
			row.appendChild(emailCol);
			
			document.getElementById("hpEmpInfo").appendChild(row);
		}
	};
	xhr.open("get","http://localhost:8085/ExpenseReimbursementSystem/employee-info");
 	xhr.send();
}

function getReimbInfo() {
	let xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function(){
		console.log("Got reimbursement info");	
		if ((xhr.readyState == 4) && (xhr.status == 200)){
			
			let obj = JSON.parse(xhr.responseText);	
			
			for (var i = 0, l = obj.length; i < l; i++) {
                var obj1 = obj[i];
                fillEmplInfo(obj1);
            }
		}
	};
	console.log("filled reimbursement info");	
	xhr.open("get","http://localhost:8085/ExpenseReimbursementSystem/employee-reimbursements");
 	xhr.send();
}

function fillEmplInfo(obj1){
	let row = document.createElement("tr");
	let ridCol = document.createElement("td");
	let rTyoeCol = document.createElement("td");
	let rCostCol = document.createElement("td");
	let rStatusCol = document.createElement("td");

	ridCol.textContent = obj1.reimbID;
	rTyoeCol.textContent = obj1.reimbType;
	rCostCol.textContent = obj1.reimbCost;
	rStatusCol.textContent = obj1.reimbStatus;

	row.appendChild(ridCol);
	row.appendChild(rTyoeCol);
	row.appendChild(rCostCol);
	row.appendChild(rStatusCol);

	document.getElementById("tdata2").appendChild(row);
}

function sendReimbRqst(){
	console.log("submitted reimbursement request")
	let rType = document.getElementById("reimbTypeSelect").value;
	let rCost = document.getElementById("reimbCost").value;
	let reimbObj = {
			reimbType: rType, 
			reimbCost: rCost
	};
	
	let reimbJSON = JSON.stringify(reimbObj);
			
	let xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function(){
		if ((xhr.readyState == 4) && (xhr.status == 200)){
			window.location = "http://localhost:8085/ExpenseReimbursementSystem/static/ers_employee.html";
            }
		};
	xhr.open("post","http://localhost:8085/ExpenseReimbursementSystem/employee-submit-reimb");
	xhr.setRequestHeader("Content-Type", "application/json");
 	xhr.send(reimbJSON);

}

function updateInfo() {
	console.log("updating user info")
	let eFname = document.getElementById("emplFname").value;
	let eLname = document.getElementById("emplLname").value;
	let eEmail = document.getElementById("emplEmail").value;
	let eUname = document.getElementById("emplUname").value;
	let ePword = document.getElementById("emplPword").value;
	let emplObj = {
			firName: eFname, 
			lasName: eLname, 
			emailAddr: eEmail, 
			usrName: eUname, 
			passWord: ePword, 
	};
	
	let emplJSON = JSON.stringify(emplObj);
			
	let xhr = new XMLHttpRequest();
	xhr.open("post","http://localhost:8085/ExpenseReimbursementSystem/employee-update-info");
	xhr.setRequestHeader("Content-Type", "application/json");
	console.log('JSON being sent');
 	xhr.send(emplJSON);
}

function logoutUser() {
	let xhr = new XMLHttpRequest();
	xhr.open("post","http://localhost:8085/ExpenseReimbursementSystem/logout");
	xhr.setRequestHeader("Content-Type", "application/json");
	xhr.send();
};
