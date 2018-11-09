// *** Employee page behaviors ***
// implements viewEmployeeInfo,	[]
// updateEmployeeInfo,			[]
// getAllReimbursementStatus,	[]
// submitReimbursementRequest,	[]
// and Logout					[]

window.onload = () => {
	console.log("Window onload"); //remove later
	getAllEmplInfo();
	getApprReimb();
	getPendReimb();
	document.getElementById("oneReimbRqst").addEventListener("click", getEmpReimb);
	document.getElementById("resolveReimbRqst").addEventListener("click", resolveReimbRqst);
	document.getElementById("logoutClick").addEventListener("click", logoutUser);
}

function getAllEmplInfo() {
	let xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function(){
		
		if ((xhr.readyState == 4) && (xhr.status == 200)){
			console.log("Got employee info");	
			let obj = JSON.parse(xhr.responseText);
			
			for (var i = 0, l = obj.length; i < l; i++) {
                var obj1= obj[i];
                fillAllEmpInfo(obj1);
            }
			
		}
	};
	xhr.open("get","http://localhost:8085/ExpenseReimbursementSystem/manager-all-employee-info");
 	xhr.send();
}

function fillAllEmpInfo(obj1){
	//console.log("Got employee info");			
	let row = document.createElement("tr");
	let idCol = document.createElement("td");
	let fnameCol = document.createElement("td");
	let lnameCol = document.createElement("td");
	let emailCol = document.createElement("td");
	
	idCol.textContent = obj1.jobID;
	fnameCol.textContent = obj1.firName;
	lnameCol.textContent = obj1.lasName;
	emailCol.textContent = obj1.emailAddr;
	
	row.appendChild(idCol);
	row.appendChild(fnameCol);
	row.appendChild(lnameCol);
	row.appendChild(emailCol);
	
	document.getElementById("mpEmpInfo").appendChild(row);
}

function getApprReimb() { 
	let xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function(){
//		console.log("Got reimbursement info");	
		if ((xhr.readyState == 4) && (xhr.status == 200)){
			
			let obj = JSON.parse(xhr.responseText);	
			
			for (var i = 0, l = obj.length; i < l; i++) {
                var obj2 = obj[i];
                fillApprReimbInfo(obj2);
            }
		}
	};//console.log("filled reimbursement info");	
	xhr.open("get","http://localhost:8085/ExpenseReimbursementSystem/manager-approved-reimbursements");
 	xhr.send();
}

function fillApprReimbInfo(obj2){
	let row = document.createElement("tr");
	let eJobCol = document.createElement("td");
	let ridCol = document.createElement("td");
	let rTyoeCol = document.createElement("td");
	let rCostCol = document.createElement("td");
	let rStatusCol = document.createElement("td");
	let mFnameCol = document.createElement("td");
	let mLnameCol = document.createElement("td");

	eJobCol.textContent = obj2.empWorkID;
	ridCol.textContent = obj2.reimbID;
	rTyoeCol.textContent = obj2.reimbType;
	rCostCol.textContent = obj2.reimbCost;
	rStatusCol.textContent = obj2.reimbStatus;
	mFnameCol.textContent = obj2.mngrFname;
	mLnameCol.textContent = obj2.mngrLname;
// add appr manager
	row.appendChild(eJobCol);
	row.appendChild(ridCol);
	row.appendChild(rTyoeCol);
	row.appendChild(rCostCol);
	row.appendChild(rStatusCol);
	row.appendChild(mFnameCol);
	row.appendChild(mLnameCol);

	document.getElementById("tdataAppr").appendChild(row);
}

function getPendReimb() {
	let xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function(){
		console.log("Got reimbursement info");	
		if ((xhr.readyState == 4) && (xhr.status == 200)){
			
			let obj = JSON.parse(xhr.responseText);	
			
			for (var i = 0, l = obj.length; i < l; i++) {
                var obj3 = obj[i];
                fillPendReimbInfo(obj3);
            }
		}
	};console.log("filled reimbursement info");	
	xhr.open("get","http://localhost:8085/ExpenseReimbursementSystem/manager-pending-reimbursements");
 	xhr.send();
}

function fillPendReimbInfo(obj3){
	let row = document.createElement("tr");
	let ridCol = document.createElement("td");
	let rTyoeCol = document.createElement("td");
	let rCostCol = document.createElement("td");
	let rStatusCol = document.createElement("td");

	ridCol.textContent = obj3.reimbID;
	rTyoeCol.textContent = obj3.reimbType;
	rCostCol.textContent = obj3.reimbCost;
	rStatusCol.textContent = obj3.reimbStatus;

	row.appendChild(ridCol);
	row.appendChild(rTyoeCol);
	row.appendChild(rCostCol);
	row.appendChild(rStatusCol);

	document.getElementById("tdataPend").appendChild(row);
}

function resolveReimbRqst(){ 
//	console.log("submitted reimbursement request")
	let rid = document.getElementById("reimbID").value;
	let rStatus = document.getElementById("reimbRslvSelect").value;
	let reimbObj = {
			reimbID: rid, 
			reimbStatus: rStatus
	};
	
	let reimbJSON = JSON.stringify(reimbObj);
	let xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function(){
		if ((xhr.readyState == 4) && (xhr.status == 200)){
			window.location = "http://localhost:8085/ExpenseReimbursementSystem/static/ers_manager.html";
            }
		};
			
	
	xhr.open("post","http://localhost:8085/ExpenseReimbursementSystem/resolve-reimb-rqst");
	xhr.setRequestHeader("Content-Type", "application/json");
 	xhr.send(reimbJSON);
}

function getEmpReimb() {
//	console.log("button clicked***");
	let eWorkID = document.getElementById("findReimbID").value;
	let emplObj = {
		jobID: eWorkID
	};
	
	let reimbRqstJSON = JSON.stringify(emplObj);
	
	let xhr1 = new XMLHttpRequest();
	xhr1.onreadystatechange = function(){
		if ((xhr1.readyState == 4) && (xhr1.status == 200)){
			let obj = JSON.parse(xhr1.responseText);
			
			console.log(obj)
			
			let table = document.getElementById("getEmpReimbInfo");
			
			clearTable(table);
			
			for (var i = 0, l = obj.length; i < l; i++) {
                var obj4 = obj[i];
                
                fillAllReimbInfo(obj4);
            }
		}
	};
	xhr1.open("post","http://localhost:8085/ExpenseReimbursementSystem/one-employee-reimb1");
	xhr1.setRequestHeader("Content-Type", "application/json");
 	xhr1.send(reimbRqstJSON);
}

function clearTable(table) {
    var rows = table.rows;
    var i = rows.length;
    while (--i) {
      rows[i].parentNode.removeChild(rows[i]);
    }
  }

function fillAllReimbInfo(obj4){
	//console.log("Got employee info");			
	let row = document.createElement("tr");
	let idCol = document.createElement("td");
	let rType = document.createElement("td");
	let rCost = document.createElement("td");
	let rStatus = document.createElement("td");

	idCol.textContent = obj4.reimbID;
	rType.textContent = obj4.reimbType;
	rCost.textContent = obj4.reimbCost;
	rStatus.textContent = obj4.reimbStatus;
	
	row.appendChild(idCol);
	row.appendChild(rType);
	row.appendChild(rCost);
	row.appendChild(rStatus);
	
	document.getElementById("tFoundPend").appendChild(row);
}

function logoutUser() {
	let xhr = new XMLHttpRequest();
	xhr.open("post","http://localhost:8085/ExpenseReimbursementSystem/logout");
	xhr.setRequestHeader("Content-Type", "application/json");
	xhr.send();
};






















