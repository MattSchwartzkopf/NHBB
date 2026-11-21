// Get All Inventory Items from database
async function getInventory() {
    const url = "http://localhost:8080/inventory/getInventory";

    try {
        const response = await fetch(url);
        if (response.ok) {
            return await response.json();
        }
    } catch {
        console.error("Fetch API failed.");
    }
}

async function addItem() {
    const item = document.getElementById("itemInput").value.trim();
    const quantity = Number(document.getElementById("quantityInput").value);

    const params = {
        item: item,
        quantity: quantity
    };

    try {
        const url = "http://localhost:8080/inventory/addItem";
        const response = await fetch(url, {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(params)
        });

        if (!response.ok) {
            throw new Error(`HTTP error: ${response.status}`);
        }
        console.log("Successfully added item!");
    } catch (error) {
        console.error("Error adding item:", error);
    }
    displayInventory();
}

document.getElementById("submit").addEventListener("click", addItem);

async function displayInventory() {
    const data = await getInventory();
    let tableHTML = '<table border="1"><tr>';

    // Create header name for each inventory column
    Object.keys(data[0]).forEach(key => {
        tableHTML += `<th>${key}</th>`;
    });

    tableHTML += '</tr>';

    // Loop through inventory id, item, and quantity and build row/column
    for (const [key, value] of Object.entries(data)) {
        tableHTML += "<tr>";
        tableHTML += `<td>${value.id}</td>`;
        tableHTML += `<td>${value.item}</td>`;
        tableHTML += `<td>${value.quantity}</td>`;
        tableHTML += "</tr>";
    }

    // Close table and add to the HTML
    tableHTML += "</table>";
    document.getElementById("inventoryTable").innerHTML = tableHTML;
}
// END of getInventory
