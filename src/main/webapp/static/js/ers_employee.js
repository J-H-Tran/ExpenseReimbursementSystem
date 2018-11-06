// *** Employee page behaviors ***
// implements viewEmployeeInfo,	[X]
// updateEmployeeInfo,			[]
// getAllReimbursementStatus,	[X]
// submitReimbursementRequest,	[]
// and Logout					[]

window.onload = () => {
	console.log("Window onload"); //remove later
	getEmplInfo();
	getReimbInfo();
	document.getElementById("reimbRqst").addEventListener("click", sendReimbRqst);
	
}

//let btnGet = document.getElementById("getInfo");
//
// btnGet.addEventListener("click", () => {
// 	var xhr = new XMLHttpRequest();
//
// 	xhr.onreadystatechange = function(){
// 		if ((xhr.readyState == 4) && (xhr.status == 200)){
// 			let original = document.getElementById("origInfo");
// 			let x = JSON.parse(xhr.responseText);
// 			console.log(x);
// 			original.innerHTML = x;
// 		}
// 	};
// 	xhr.open("get","http://localhost:8085/ExpenseReimbursementSystem/employee-info");
// 	xhr.send();
// });

function getEmplInfo() {
	var xhr = new XMLHttpRequest();
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
			
			document.getElementById("tdata1").appendChild(row);
		}
	};
	xhr.open("get","http://localhost:8085/ExpenseReimbursementSystem/employee-info");
 	xhr.send();
}

function getReimbInfo() {
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function(){
		
		if ((xhr.readyState == 4) && (xhr.status == 200)){
			
			let obj = JSON.parse(xhr.responseText);	
			
			for (var i = 0, l = obj.length; i < l; i++) {
                var obj1 = obj[i];
                fillEmplInfo(obj1);
            }
		}
	};
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
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function(){
		if ((xhr.readyState == 4) && (xhr.status == 200)){
			
			let rType = document.getElementById("reimbTypeSelect").value;
			let rCost = document.getElementById("reimbCost").value;
//	console.log(rType + " " + rCost);
			let reimbObj = {
					reimbType: rType, 
					reimbCost: rCost
			};
			
			let reimbJSON = JSON.stringify(reimbObj);
		}
		
	};
	xhr.open("get","http://localhost:8085/ExpenseReimbursementSystem/employee-submit-reimb");
 	xhr.send(reimbJSON);

}

























