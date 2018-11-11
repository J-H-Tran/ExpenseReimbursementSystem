// *** Employee page behaviors ***
// implements viewEmployeeInfo,	[X]
// updateEmployeeInfo,			[X]
// getAllReimbursementStatus,	[X]
// submitReimbursementRequest,	[X]
// and Logout					[X]

window.onload = () => {
	getEmplInfo();
	getReimbInfo();
}

function getEmplInfo() {
	console.log('Get Employee Info');
	let xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function(){
		
		if ((xhr.readyState == 4) && (xhr.status == 200)){
			console.log("If statement");
			let list = document.createElement("ul");
			let eid = document.createElement("li");
			let fname = document.createElement("li");
			let lname = document.createElement("li");
			let email = document.createElement("li");
			// receives response from java -> parse
			let obj = JSON.parse(xhr.responseText); 
			
			console.log(obj);
			eid.textContent = obj.jobID;
			fname.textContent = obj.firName;
			lname.textContent = obj.lasName;
			email.textContent = obj.emailAddr;

			document.getElementById("empDeets").appendChild(eid);
			document.getElementById("empDeets").appendChild(fname);
			document.getElementById("empDeets").appendChild(lname);
			document.getElementById("empDeets").appendChild(email);
			
			document.getElementById("emplFname").value = obj.firName;
			document.getElementById("emplLname").value = obj.lasName;
			document.getElementById("emplEmail").placeholder = obj.emailAddr;
			document.getElementById("emplUname").placeholder = obj.usrName;
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

			let table = document.getElementById("tdata2");
			clearTable(table);
			
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

function clearTable(table) {    
    while(table.rows.length > 0){
        table.deleteRow(0);
    }
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
	var reimbObj1 = {
			reimbType: rType, 
			reimbCost: rCost
	};
	if(reimbObj1.reimbType != null | reimbObj1.reimbCost != 0 | reimbObj1.reimbCost != 0.00){
		
		let reimbJSON = JSON.stringify(reimbObj1);
		
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
	

}

function updateInfo() {
	console.log("updating user info")
	let eFname = document.getElementById("emplFname").value;
	let eLname = document.getElementById("emplLname").value;
	let eEmail = document.getElementById("emplEmail").value;
	let eUname = document.getElementById("emplUname").value;
	let emplObj = {
			firName: eFname, 
			lasName: eLname, 
			emailAddr: eEmail, 
			usrName: eUname 
	};
	
	let emplJSON = JSON.stringify(emplObj);
	
	let xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function(){
		if ((xhr.readyState == 4) && (xhr.status == 200)){
			//window.location = "http://localhost:8085/ExpenseReimbursementSystem/static/ers_employee.html";
            }
		};
			
	let xhr = new XMLHttpRequest();
	xhr.open("post","http://localhost:8085/ExpenseReimbursementSystem/employee-update-info");
	xhr.setRequestHeader("Content-Type", "application/json");
	console.log('JSON being sent');
 	xhr.send(emplJSON);
}

function myFunction() {
    // Declare variables
    var input, filter, table, tr, td, i;
    input = document.getElementById("filterReimb");
    filter = input.value.toUpperCase();
    table = document.getElementById("tdata2");
    tr = table.getElementsByTagName("tr");

    // Loop through all table rows, and hide those who don't match the search query
    for (i = 0; i < tr.length; i++) {
      td = tr[i].getElementsByTagName("td")[3];
      if (td) {
        if (td.innerHTML.toUpperCase().indexOf(filter) > -1) {
          tr[i].style.display = "";
        } else {
          tr[i].style.display = "none";
        }
      }
    }
  }
