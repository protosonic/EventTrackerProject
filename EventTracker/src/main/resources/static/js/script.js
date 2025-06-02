console.log('script.js loaded');

window.addEventListener('load', function(e) {
	console.log('document loaded');
	init();
});

function init() {
	document.newBountyForm.addBountyButton.addEventListener('click', function(evt) {
		evt.preventDefault();
		let newBounty = {
			amount: document.newBountyForm.amount.value,
			description: document.newBountyForm.description.value,
			status: document.newBountyForm.status.value,
			dangerLevel: document.newBountyForm.dangerLevel.value,
			imageUrl: document.newBountyForm.imageUrl.value
		};
		addBounty(newBounty);
	});

	loadBounties(); 
}

function loadBounties() {
	let xhr = new XMLHttpRequest();
	let url = 'api/bounties'; 
	xhr.open('GET', url);
	xhr.onreadystatechange = function() {
		if (xhr.readyState === XMLHttpRequest.DONE) {
			if (xhr.status === 200) {
				let bounties = JSON.parse(xhr.responseText);
				displayBounties(bounties);
				console.log(bounties);
			} else {
				console.error("Failed to load bounties. Status: " + xhr.status);
			}
		}
	};
	xhr.send();
}

function displayBounties(bounties) {
	let tbody = document.getElementById('bountiesTableBody');
	tbody.innerHTML = ''; // Clear table first

	if (!Array.isArray(bounties)) return;

	for (let bounty of bounties) {
		let tr = document.createElement('tr');
		tbody.appendChild(tr);

		tr.addEventListener('click', function() {
			displayBountyInfo(bounty);
		});
		
		let imgTd = document.createElement('td');
		let img = document.createElement('img');
		let defaultImg = 'images/default_target.png';
		img.src = bounty.imageUrl || defaultImg;  
		img.alt = 'Target image for bounty #' + bounty.id;
		imgTd.appendChild(img);
		tr.appendChild(imgTd);

		let td = document.createElement('td');
		td.textContent = bounty.id;
		tr.appendChild(td);

		td = document.createElement('td');
		td.textContent = bounty.amount;
		tr.appendChild(td);

		td = document.createElement('td');
		td.textContent = bounty.status === 1 ? 'Claimed' : 'Unclaimed';
		tr.appendChild(td);
	}
}

function displayBountyInfo(bounty) {
	let dataDiv = document.getElementById('bountyData');
	dataDiv.textContent = '';

	let h3 = document.createElement('h3');
	h3.textContent = 'Bounty #' + bounty.id + ' Details';
	dataDiv.appendChild(h3);
	
	let ul = document.createElement('ul');
	dataDiv.appendChild(ul);
	
	let li = document.createElement('li');
	li.textContent = 'Description: ' + bounty.description;
	ul.appendChild(li);
	
	li = document.createElement('li');
	li.textContent = 'Danger Level: ' + bounty.dangerLevel;
	ul.appendChild(li);
	
	li = document.createElement('li');
	li.textContent = 'Amount: ' + bounty.amount;
	ul.appendChild(li);
	
	li = document.createElement('li');
	li.textContent = 'Status: ' + (bounty.status == 1 ? 'Claimed' : 'Unclaimed');
	ul.appendChild(li);
}

function addBounty(bounty) {
	let xhr = new XMLHttpRequest();
	let url = 'api/bounties';
	xhr.open('POST', url);
	xhr.onreadystatechange = function() {
		if (xhr.readyState === XMLHttpRequest.DONE) {
			if (xhr.status === 201) {
				let newBounty = JSON.parse(xhr.responseText);
				displayBountyInfo(newBounty);
				loadBounties(); 
			} else {
				console.error("Error creating bounty", xhr.status, xhr.responseText);
			}
		}
	};
	xhr.setRequestHeader("Content-type", "application/json");
	let bountyJson = JSON.stringify(bounty);
	xhr.send(bountyJson);
}