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
    const item = document.getElementById("addItemInput").value.trim();
    const quantity = Number(document.getElementById("addQuantityInput").value);

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
    await displayInventory();
}

async function deleteItem() {
    const item = document.getElementById("deleteItemInput").value.trim();
    const quantity = Number(document.getElementById("deleteQuantityInput").value);

    const params = {
        item: item,
        quantity: quantity
    };

    try {
        const url = `http://localhost:8080/inventory/deleteItem?itemName=${encodeURIComponent(item)}&quantity=${quantity}`;
        const response = await fetch(url, {
            method: "DELETE"
        });

        if (!response.ok) {
            throw new Error(`HTTP error: ${response.status}`);
        }
        console.log("Successfully deleted item!");
    } catch (error) {
        console.error("Error deleting item:", error);
    }
    await displayInventory();
}

async function displayInventory() {
    const data = await getInventory();
    let tableHTML = '<div class="table-responsive">';
    tableHTML += '<table class="table table-striped table-hover table-bordered">';
    tableHTML += '<thead class="table-dark"><tr>';

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
    tableHTML += '</table></div>';
    document.getElementById("inventoryTable").innerHTML = tableHTML;
}

document.getElementById("submitAddItem").addEventListener("click", addItem);
document.getElementById("submitDeleteItem").addEventListener("click", deleteItem);

// Always initially load current inventory
document.addEventListener("DOMContentLoaded", () => {
    displayInventory();
});