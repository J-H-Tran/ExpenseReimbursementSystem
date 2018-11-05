window.onload = () => {
	// let btn = document.getElementById("display");
	// btn.addEventListener("click",()=>{
	// 	//for every readyState, onreadystatechange will be executed
	// 	var xhr = new XMLHttpRequest();
	// 	xhr.onreadystatechange = function(){
	// 		if ((xhr.readyState == 4) && (xhr.status == 200)){
	// 			let rt = document.getElementById("responsetext");
	// 			let hs = document.getElementById("httpstatus");
	// 			let hst = document.getElementById("httpstatustext");
	// 			let rs = document.getElementById("readystate");
	// 			let rh = document.getElementById("responseheaders");
	// 			let rx = document.getElementById("responseXML");
	// 			rt.innerHTML = xhr.responseText;
	// 			hs.innerHTML = xhr.status;
	// 			hst.innerHTML = xhr.statusText;
	// 			rs.innerHTML = xhr.readyState;
	// 			rx.innerHTML = xhr.responseXML;
	// 			rh.innerHTML = xhr.getAllResponseHeaders();
	// 		}
	// 	};
	// 	xhr.open("get","https://api.myjson.com/bins/eq9je");
	// 	xhr.send();
	// });

	let btnGet = document.getElementById("getInfo");
	btnGet.addEventListener("click", () => {
		var xhr = new XMLHttpRequest();
		xhr.onreadystatechange = function(){
			if ((xhr.readyState == 4) && (xhr.status == 200)){
				let original = document.getElementById("origInfo");
				original.innerHTML = xhr.responseText;
			}
		};
		xhr.open("get","https://api.myjson.com/bins/eq9je");
		xhr.send();
	});
}
